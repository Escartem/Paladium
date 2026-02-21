package fr.paladium.palaconfiguration.server.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.paladium.palaconfiguration.ConfigurationLogger;
import fr.paladium.palaconfiguration.server.ServerProxy;
import fr.paladium.palaconfiguration.server.command.IReloadCallback;
import fr.paladium.palaconfiguration.server.dto.config.Config;
import fr.paladium.palaconfiguration.server.dto.config.request.ConfigReloadRequest;
import fr.paladium.palaconfiguration.server.dto.config.response.ConfigRefreshResponse;
import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaconfiguration.server.rabbitmq.RefreshConfigRabbitPacket;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palaconfiguration.server.utils.ConfigurationModUtils;
import fr.paladium.palaconfiguration.server.utils.json.adapter.ItemStackAdapter;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import fr.paladium.palaforgeutils.lib.api.callback.RetrofitCallback;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigurationManager {
  private static ConfigurationManager instance;
  
  public static ConfigurationManager getInstance() {
    return instance;
  }
  
  private static final Gson GSON = (new GsonBuilder()).serializeNulls().setPrettyPrinting().setLenient().registerTypeAdapter(ItemStack.class, new ItemStackAdapter()).create();
  
  private static final String RELOAD_SUCCESS_MESSAGE = "§aLa configuration §e%s§a a été rechargée avec succès ! §7(§d%s§7)";
  
  private static final String RELOAD_FAIL_MESSAGE = "§cImpossible de recharger la configuration !";
  
  private static final String NOT_RELOADABLE_MESSAGE = "§cImpossible de recharger la configuration %s ! Elle n'est pas rechargable !";
  
  private static final String SHOW_PERMISSION = "paladium.configuration.show";
  
  public static final String LOCAL_NAME = "LOCAL";
  
  public static final String API_NAME = "API";
  
  private final Map<String, IConfig> configs;
  
  public Map<String, IConfig> getConfigs() {
    return this.configs;
  }
  
  public ConfigurationManager() {
    instance = this;
    this.configs = new HashMap<>();
  }
  
  public void loadAllConfigs() {
    this.configs.values().forEach(config -> {
          ConfigFile configFile = config.getClass().<ConfigFile>getAnnotation(ConfigFile.class);
          if (configFile.blocking() || configFile.local())
            return; 
          final String path = config.getConfigFile().path();
          getConfig(config, new IRetrofitCallback<Config>() {
                public void onSuccess(Config configDTO) {
                  try {
                    ConfigurationManager.this.loadConfig(configDTO, config, false);
                  } catch (Exception e) {
                    throw new RuntimeException("Failed to load configuration " + path + "!", e);
                  } 
                  ConfigurationLogger.info("Loaded configuration " + path + "!");
                }
                
                public void onFail(Config configDTO, Throwable throwable) {
                  config.onFailed();
                  throw new RuntimeException("Failed to load configuration " + path + "!", throwable);
                }
              });
        });
  }
  
  @NonNull
  private IConfig loadConfig(@NonNull Config dto, @NonNull IConfig config, boolean reload) {
    if (dto == null)
      throw new NullPointerException("dto is marked non-null but is null"); 
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    IConfig newConfig = null;
    try {
      Gson gson = (new GsonBuilder()).serializeNulls().setLenient().registerTypeAdapter(config.getClass(), t -> config).registerTypeAdapter(ItemStack.class, new ItemStackAdapter()).create();
      newConfig = (IConfig)gson.fromJson(dto.getContent(), config.getClass());
    } catch (Exception error) {
      throw new RuntimeException("Failed to map configuration " + dto.getContent() + " with type " + config.getClass().getTypeName() + "!", error);
    } 
    this.configs.put(dto.getName(), newConfig);
    if (reload) {
      newConfig.onReloaded();
    } else {
      newConfig.onLoaded();
    } 
    if (!newConfig.isValid())
      throw new RuntimeException("Configuration " + dto.getName() + " is not valid! Please check the configuration file."); 
    ConfigurationLogger.info("Successfully " + (reload ? "reloaded" : "loaded") + " config " + dto.getName() + ". (" + dto.getAbsolutePath() + ")");
    return newConfig;
  }
  
  private void getConfig(@NonNull IConfig configuration, @NonNull IRetrofitCallback<Config> callback) {
    if (configuration == null)
      throw new NullPointerException("configuration is marked non-null but is null"); 
    if (callback == null)
      throw new NullPointerException("callback is marked non-null but is null"); 
    ServerProxy.getInstance().getConfigApi()
      .findOne(configuration.getConfigFile().path(), ServerProxy.getInstance().getConfig().getEnvironment())
      .enqueue((Callback)new RetrofitCallback(callback, response -> 
          (response.code() != 200 || response.body() == null) ? null : (Config)response.body()));
  }
  
  @NonNull
  private IConfig loadLocalConfig(@NonNull IConfig config, @NonNull ConfigFile configFile, boolean reload) {
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    if (configFile == null)
      throw new NullPointerException("configFile is marked non-null but is null"); 
    File file = new File(ServerProxy.getInstance().getConfigDirectory(), configFile.path());
    try {
      IConfig loaded = (IConfig)JsonConfigLoader.loadConfig(file, config.getClass());
      String content = GSON.toJson(loaded);
      return loadConfig(new Config(configFile.path(), file.getAbsolutePath(), content), config, reload);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load configuration " + configFile.path() + "! ", e);
    } 
  }
  
  @NonNull
  public IConfig loadSynchronouslyConfig(String path, IConfig config) {
    IConfig newConfig = null;
    try {
      Response<Config> response = ServerProxy.getInstance().getConfigApi().findOne(path, ServerProxy.getInstance().getConfig().getEnvironment()).execute();
      Config dto = (Config)response.body();
      if (response.code() != 200 || dto == null)
        throw new RuntimeException("Failed to load configuration " + path + "! " + response.message()); 
      newConfig = loadConfig(dto, config, false);
    } catch (Exception error) {
      throw new RuntimeException("Failed to load configuration " + path + "! ", error);
    } 
    if (newConfig == null)
      throw new RuntimeException("Failed to load configuration " + path + "! The configuration is null!"); 
    ConfigurationLogger.info("Loaded configuration " + path + "!");
    return newConfig;
  }
  
  public void reloadLocalConfig(@NonNull IConfig config, @NonNull ConfigFile configFile, boolean debug, IReloadCallback callback) {
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    if (configFile == null)
      throw new NullPointerException("configFile is marked non-null but is null"); 
    List<ICommandSender> admins = ConfigurationModUtils.getPlayersWithPermission("paladium.configuration.show");
    if (!configFile.reloadable()) {
      admins.forEach(admin -> ChatUtils.sendColoredMessage(admin, new String[] { String.format("§cImpossible de recharger la configuration %s ! Elle n'est pas rechargable !", new Object[] { configFile.path() }) }));
      return;
    } 
    if (loadLocalConfig(config, configFile, true) == null)
      return; 
    admins.forEach(admin -> {
          ChatUtils.sendColoredMessage(admin, new String[] { String.format("§aLa configuration §e%s§a a été rechargée avec succès ! §7(§d%s§7)", new Object[] { config.getClass().getSimpleName(), "LOCAL" }) });
          if (debug)
            ConfigurationModUtils.sendJsonObject(admin, configFile.path(), config); 
        });
    if (callback != null)
      callback.success(); 
  }
  
  public void reloadApiConfig(@NonNull final IConfig config, @NonNull final ConfigFile configFile, final boolean debug, boolean refreshOnApi, final IReloadCallback callback) {
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    if (configFile == null)
      throw new NullPointerException("configFile is marked non-null but is null"); 
    final List<ICommandSender> admins = ConfigurationModUtils.getPlayersWithPermission("paladium.configuration.show");
    if (!configFile.reloadable()) {
      admins.forEach(admin -> ChatUtils.sendColoredMessage(admin, new String[] { String.format("§cImpossible de recharger la configuration %s ! Elle n'est pas rechargable !", new Object[0]) }));
      return;
    } 
    ConfigReloadRequest dto = new ConfigReloadRequest(configFile.path(), ServerProxy.getInstance().getConfig().getEnvironment(), refreshOnApi);
    reloadConfig(config, configFile, dto, new IRetrofitCallback<IConfig>() {
          public void onSuccess(IConfig c) {
            if (c == null)
              return; 
            if (callback != null)
              callback.success(); 
            admins.forEach(admin -> {
                  ChatUtils.sendColoredMessage(admin, new String[] { String.format("§aLa configuration §e%s§a a été rechargée avec succès ! §7(§d%s§7)", new Object[] { config.getClass().getSimpleName(), "API" }) });
                  if (debug)
                    ConfigurationModUtils.sendJsonObject(admin, configFile.path(), c); 
                });
          }
          
          public void onFail(IConfig c, Throwable throwable) {
            admins.forEach(admin -> ChatUtils.sendColoredMessage(admin, new String[] { "§cImpossible de recharger la configuration !" }));
          }
        });
  }
  
  public void reloadConfig(IConfig config, ConfigFile configFile, ConfigReloadRequest reloadDto, IRetrofitCallback<IConfig> callback) {
    ServerProxy.getInstance().getConfigApi().reloadConfig(reloadDto).enqueue((Callback)new RetrofitCallback(callback, response -> {
            if (response.code() != 200 || response.body() == null)
              return null; 
            try {
              return loadConfig((Config)response.body(), config, true);
            } catch (Exception e) {
              throw new RuntimeException("Failed to load configuration " + configFile.path() + "! ", e);
            } 
          }));
  }
  
  public void refreshAll(@NonNull IRetrofitCallback<List<ConfigRefreshResponse>> callback) {
    if (callback == null)
      throw new NullPointerException("callback is marked non-null but is null"); 
    ServerProxy.getInstance().getConfigApi().refreshAll(ServerProxy.getInstance().getConfig().getEnvironment()).enqueue((Callback)new RetrofitCallback(callback, response -> {
            if (response.code() != 200 || response.body() == null)
              return null; 
            ConfigurationLogger.info("Successfully refreshed " + this.configs.size() + " configs.");
            return (List)response.body();
          }));
  }
  
  public void reloadAll(boolean refreshOnApi) {
    getConfigs().forEach((name, config) -> {
          ConfigFile configFile = config.getConfigFile();
          IReloadCallback callback = ();
          if (configFile.local()) {
            reloadLocalConfig(config, configFile, false, callback);
          } else {
            reloadApiConfig(config, configFile, false, refreshOnApi, callback);
          } 
        });
  }
  
  public <T extends IConfig> T register(@NonNull Class<T> clazz) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    try {
      IConfig iConfig = (IConfig)clazz.newInstance();
      ConfigFile configFile = iConfig.getClass().<ConfigFile>getAnnotation(ConfigFile.class);
      if (configFile == null)
        throw new RuntimeException("Failed to register config " + clazz.getSimpleName() + "! The class is not annotated with @ConfigFile!"); 
      this.configs.put(configFile.path(), iConfig);
      if (configFile.local())
        return (T)loadLocalConfig(iConfig, configFile, false); 
      if (configFile.blocking())
        return (T)loadSynchronouslyConfig(configFile.path(), iConfig); 
      return (T)iConfig;
    } catch (Exception error) {
      throw new RuntimeException("Failed to register config " + clazz.getSimpleName() + "!", error);
    } 
  }
  
  public void sendRefreshConfigPacket(@NonNull String configName) {
    if (configName == null)
      throw new NullPointerException("configName is marked non-null but is null"); 
    if (ForgeEnv.isDev()) {
      ConfigurationLogger.logger.info("Dev environment detected, we don't send RabbitMQ packet.");
      return;
    } 
    RefreshConfigRabbitPacket packet = new RefreshConfigRabbitPacket(configName);
    packet.send(ServerProxy.getInstance().getRabbitService());
  }
  
  @NonNull
  public CompletableFuture<Map<String, RemoteObject>> getFiles() {
    final CompletableFuture<Map<String, RemoteObject>> future = new CompletableFuture<>();
    ServerProxy.getInstance().getFileApi().getFiles(ServerProxy.getInstance().getConfig().getEnvironment()).enqueue((Callback)new RetrofitCallback(new IRetrofitCallback<Map<String, RemoteObject>>() {
            public void onSuccess(Map<String, RemoteObject> files) {
              future.complete(files);
            }
            
            public void onFail(Map<String, RemoteObject> files, Throwable throwable) {
              future.completeExceptionally(throwable);
            }
          },  response -> (response.code() != 200 || response.body() == null) ? null : (Map)response.body()));
    return future;
  }
  
  @NonNull
  public CompletableFuture<RemoteObject> getFile(@NonNull String path) {
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    final CompletableFuture<RemoteObject> future = new CompletableFuture<>();
    ServerProxy.getInstance().getFileApi().getFile(ServerProxy.getInstance().getConfig().getEnvironment(), path).enqueue((Callback)new RetrofitCallback(new IRetrofitCallback<RemoteObject>() {
            public void onSuccess(RemoteObject file) {
              future.complete(file);
            }
            
            public void onFail(RemoteObject file, Throwable throwable) {
              future.completeExceptionally(throwable);
            }
          },  response -> (response.code() != 200 || response.body() == null) ? null : (RemoteObject)response.body()));
    return future;
  }
  
  @NonNull
  public CompletableFuture<RemoteObject> findFile(@NonNull String path) {
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    final CompletableFuture<RemoteObject> future = new CompletableFuture<>();
    ServerProxy.getInstance().getFileApi().findFile(ServerProxy.getInstance().getConfig().getEnvironment(), path).enqueue((Callback)new RetrofitCallback(new IRetrofitCallback<RemoteObject>() {
            public void onSuccess(RemoteObject file) {
              future.complete(file);
            }
            
            public void onFail(RemoteObject file, Throwable throwable) {
              future.completeExceptionally(throwable);
            }
          },  response -> (response.code() != 200 || response.body() == null) ? null : (RemoteObject)response.body()));
    return future;
  }
  
  public CompletableFuture<Void> reloadFiles() {
    final CompletableFuture<Void> future = new CompletableFuture<>();
    ServerProxy.getInstance().getFileApi().reload().enqueue((Callback)new RetrofitCallback(new IRetrofitCallback<Void>() {
            public void onSuccess(Void file) {
              future.complete(file);
            }
            
            public void onFail(Void file, Throwable throwable) {
              future.completeExceptionally(throwable);
            }
          },  response -> (response.code() != 200 || response.body() == null) ? null : (Void)response.body()));
    return future;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\manager\ConfigurationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */