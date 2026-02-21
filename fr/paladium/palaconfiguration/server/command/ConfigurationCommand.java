package fr.paladium.palaconfiguration.server.command;

import fr.paladium.palaconfiguration.server.ServerProxy;
import fr.paladium.palaconfiguration.server.dto.config.response.ConfigRefreshResponse;
import fr.paladium.palaconfiguration.server.dto.file.RemoteDirectory;
import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palaconfiguration.server.utils.ConfigurationModUtils;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.io.FileUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Command(command = {"/config"}, description = "Gestion de la config-api", permission = "paladium.configuration.base")
public class ConfigurationCommand {
  @SubCommand(command = "/config environment", description = "Voir l'environnement", permission = "paladium.configuration.environment")
  public void environment(CommandContext context) {
    context.send("§7L'environnement de la configuration est: §e" + ServerProxy.getInstance().getConfig().getEnvironment().name());
  }
  
  @SubCommand(command = "/config reload-all", description = "Recharger toutes les configurations", permission = "paladium.configuration.reload-all")
  public void reloadAllConfig(CommandContext context) {
    ConfigurationManager.getInstance().reloadAll(true);
    context.send("§aLes configurations ont été rechargées avec succès");
  }
  
  @SubCommand(command = "/config refresh-all", description = "Refresh toutes les configurations", permission = "paladium.configuration.refresh-all")
  public void refreshAllConfig(final CommandContext context) {
    IRetrofitCallback<List<ConfigRefreshResponse>> callback = new IRetrofitCallback<List<ConfigRefreshResponse>>() {
        public void onSuccess(List<ConfigRefreshResponse> result) {
          context.send("§aLes configurations ont été refresh avec succès");
        }
        
        public void onFail(List<ConfigRefreshResponse> refreshDTOS, Throwable throwable) {
          context.send("§cErreur lors du refresh des configurations");
          throwable.printStackTrace();
        }
      };
    ConfigurationManager.getInstance().refreshAll(callback);
  }
  
  @SubCommand(command = "/config list", description = "Lister toutes les configurations", permission = "paladium.configuration.list")
  public void listConfig(CommandContext context) {
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7Liste des configurations disponibles :" });
    ConfigurationManager.getInstance().getConfigs().forEach((name, config) -> {
          ConfigFile configFile = config.getClass().<ConfigFile>getAnnotation(ConfigFile.class);
          ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §e" + configFile.path() + " §7(§d" + (configFile.local() ? "LOCAL" : "API") + "§7)" });
        });
    ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
  }
  
  @SubCommand(command = "/config reload <name>", description = "Recharger une configuration", permission = "paladium.configuration.reload")
  public void reloadConfig(CommandContext context, String name) {
    ConfigurationManager manager = ConfigurationManager.getInstance();
    if (!manager.getConfigs().containsKey(name)) {
      ChatUtils.sendColoredMessage(context.getSender(), new String[] { String.format("§cLa configuration §e%s§c n'existe pas !", new Object[] { name }) });
      return;
    } 
    IConfig config = (IConfig)manager.getConfigs().get(name);
    ConfigFile configFile = config.getClass().<ConfigFile>getAnnotation(ConfigFile.class);
    IReloadCallback callback = () -> manager.sendRefreshConfigPacket(configFile.path());
    if (configFile.local()) {
      manager.reloadLocalConfig(config, configFile, false, callback);
      return;
    } 
    manager.reloadApiConfig(config, configFile, false, true, callback);
  }
  
  @SubCommand(command = "/config info <name>", description = "Informations sur une configuration", permission = "paladium.configuration.info")
  public void infoConfig(CommandContext context, String name) {
    ConfigurationManager manager = ConfigurationManager.getInstance();
    if (!manager.getConfigs().containsKey(name)) {
      ChatUtils.sendColoredMessage(context.getSender(), new String[] { String.format("§cLa configuration §e%s§c n'existe pas !", new Object[] { name }) });
      return;
    } 
    IConfig config = (IConfig)manager.getConfigs().get(name);
    ConfigurationModUtils.sendJsonObject(context.getSender(), name, config);
  }
  
  @SubCommand(command = "/config file reload", description = "Recharger les fichiers", permission = "paladium.configuration.file.reload")
  public void reloadFiles(final CommandContext context) {
    ServerProxy.getInstance().getFileApi()
      .reload().enqueue(new Callback<Void>() {
          public void onResponse(Call<Void> call, Response<Void> response) {
            if (!response.isSuccessful()) {
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors du rechargement des fichiers" });
              return;
            } 
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§aLes fichiers ont été rechargés avec succès" });
          }
          
          public void onFailure(Call<Void> call, Throwable t) {
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors du rechargement des fichiers" });
            t.printStackTrace();
          }
        });
  }
  
  @SubCommand(command = "/config file list [<path>]", description = "Lister tous les fichiers", permission = "paladium.configuration.file.list")
  public void listFiles(final CommandContext context, Optional<String> optionalPath) {
    if (optionalPath.isPresent()) {
      ServerProxy.getInstance().getFileApi()
        .getFile(ServerProxy.getInstance().getConfig().getEnvironment(), optionalPath.get()).enqueue(new Callback<RemoteObject>() {
            public void onResponse(Call<RemoteObject> call, Response<RemoteObject> response) {
              if (!response.isSuccessful() || response.body() == null) {
                ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération des fichiers" });
                return;
              } 
              RemoteObject object = (RemoteObject)response.body();
              if (!object.isDirectory()) {
                ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cLe fichier spécifié n'est pas un dossier" });
                return;
              } 
              RemoteDirectory directory = object.asDirectory();
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7Liste des fichiers disponibles :" });
              directory.listFiles().forEach(file -> ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §e" + file.getPath() + " §7(§d" + (file.isDirectory() ? "Dossier" : "Fichier") + "§7)" }));
            }
            
            public void onFailure(Call<RemoteObject> call, Throwable t) {
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération des fichiers" });
              t.printStackTrace();
            }
          });
    } else {
      ServerProxy.getInstance().getFileApi()
        .getFiles(ServerProxy.getInstance().getConfig().getEnvironment()).enqueue(new Callback<Map<String, RemoteObject>>() {
            public void onResponse(Call<Map<String, RemoteObject>> call, Response<Map<String, RemoteObject>> response) {
              if (!response.isSuccessful() || response.body() == null) {
                ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération des fichiers" });
                return;
              } 
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7Liste des fichiers disponibles :" });
              ((Map)response.body()).values().forEach(file -> ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §e" + file.getPath() + " §7(§d" + (file.isDirectory() ? "Dossier" : "Fichier") + "§7)" }));
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
            }
            
            public void onFailure(Call<Map<String, RemoteObject>> call, Throwable t) {
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération des fichiers" });
              t.printStackTrace();
            }
          });
    } 
  }
  
  @SubCommand(command = "/config file info <path>", description = "Afficher les informations d'un fichier", permission = "paladium.configuration.file.info")
  public void listFiles(final CommandContext context, String path) {
    ServerProxy.getInstance().getFileApi()
      .getFile(ServerProxy.getInstance().getConfig().getEnvironment(), path).enqueue(new Callback<RemoteObject>() {
          public void onResponse(Call<RemoteObject> call, Response<RemoteObject> response) {
            if (!response.isSuccessful() || response.body() == null) {
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération du fichier" });
              return;
            } 
            RemoteObject file = (RemoteObject)response.body();
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7Informations sur le fichier :" });
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eNom : §7" + file.getName() });
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eChemin : §7" + file.getPath() });
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eTaille : §7" + FileUtils.byteCountToDisplaySize(file.getSize()) });
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eType : §7" + (file.isDirectory() ? "Dossier" : "Fichier") });
            if (file.isDirectory()) {
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eFichiers : §7" + file.asDirectory().listFiles().size() });
            } else {
              ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§7- §eSHA1 : §7" + file.getSha1() });
            } 
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§8§m---------------------------------" });
          }
          
          public void onFailure(Call<RemoteObject> call, Throwable t) {
            ChatUtils.sendColoredMessage(context.getSender(), new String[] { "§cErreur lors de la récupération du fichier" });
            t.printStackTrace();
          }
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\command\ConfigurationCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */