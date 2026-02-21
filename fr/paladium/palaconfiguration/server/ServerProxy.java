package fr.paladium.palaconfiguration.server;

import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palaconfiguration.ConfigurationLogger;
import fr.paladium.palaconfiguration.common.CommonProxy;
import fr.paladium.palaconfiguration.server.api.IConfigurationAPI;
import fr.paladium.palaconfiguration.server.api.IFileAPI;
import fr.paladium.palaconfiguration.server.command.ConfigurationCommand;
import fr.paladium.palaconfiguration.server.config.LocalConfiguration;
import fr.paladium.palaconfiguration.server.config.TestConfiguration;
import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaconfiguration.server.rabbitmq.RefreshConfigRabbitListener;
import fr.paladium.palaconfiguration.server.utils.json.adapter.RemoteObjectGsonAdapter;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.rabbitmq.listener.RabbitListener;
import fr.paladium.palaforgeutils.lib.rabbitmq.service.RabbitService;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServerProxy extends CommonProxy {
  private static ServerProxy instance;
  
  private final ConfigurationManager manager;
  
  private RabbitService rabbitService;
  
  private File configDirectory;
  
  private LocalConfiguration config;
  
  private IFileAPI fileApi;
  
  private IConfigurationAPI configApi;
  
  public static ServerProxy getInstance() {
    return instance;
  }
  
  public ConfigurationManager getManager() {
    return this.manager;
  }
  
  public RabbitService getRabbitService() {
    return this.rabbitService;
  }
  
  public File getConfigDirectory() {
    return this.configDirectory;
  }
  
  public LocalConfiguration getConfig() {
    return this.config;
  }
  
  public IFileAPI getFileApi() {
    return this.fileApi;
  }
  
  public IConfigurationAPI getConfigApi() {
    return this.configApi;
  }
  
  public ServerProxy() {
    instance = this;
    this.manager = new ConfigurationManager();
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    this.configDirectory = event.getModConfigurationDirectory();
    initAPI();
    CommandRegistry.register(new Class[] { ConfigurationCommand.class });
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
    if (ForgeEnv.isIDE()) {
      TestConfiguration testConfig = (TestConfiguration)this.manager.register(TestConfiguration.class);
      if (!testConfig.isValid())
        throw new RuntimeException("Test configuration is invalid, stopping server"); 
      System.out.println("Test configuration loaded: " + testConfig.getText());
    } 
    this.manager.loadAllConfigs();
    if (ForgeEnv.isDev()) {
      ConfigurationLogger.info("Dev environment detected, we load RabbitMQ DISABLED");
      return;
    } 
    this.rabbitService = new RabbitService(this.config.getRabbitCredentials(), this.config.getRabbitQueueName());
    this.rabbitService.registerListener((RabbitListener)new RefreshConfigRabbitListener(this.rabbitService));
  }
  
  private void initAPI() {
    this.config = (LocalConfiguration)this.manager.register(LocalConfiguration.class);
    long timeout = 20L;
    OkHttpClient client = (new OkHttpClient.Builder()).readTimeout(20L, TimeUnit.SECONDS).connectTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
    Retrofit.Builder retrofitBuilder = (new Retrofit.Builder()).client(client).addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create((new GsonBuilder()).registerTypeAdapter(RemoteObject.class, new RemoteObjectGsonAdapter()).create()));
    String apiUrl = this.config.getApiUrl();
    this.fileApi = (IFileAPI)retrofitBuilder.baseUrl(apiUrl).build().create(IFileAPI.class);
    this.configApi = (IConfigurationAPI)retrofitBuilder.baseUrl(apiUrl).build().create(IConfigurationAPI.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */