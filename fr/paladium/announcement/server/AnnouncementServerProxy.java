package fr.paladium.announcement.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.announcement.common.AnnouncementCommonProxy;
import fr.paladium.announcement.server.command.AnnouncementCommand;
import fr.paladium.announcement.server.command.PatchnoteCommand;
import fr.paladium.announcement.server.config.PalaAnnouncementConfig;
import fr.paladium.announcement.server.listener.PlayerListener;
import fr.paladium.announcement.server.task.AnnouncementDataRefreshTask;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;

public class AnnouncementServerProxy extends AnnouncementCommonProxy {
  private static AnnouncementServerProxy instance;
  
  private PalaAnnouncementConfig apiConfig;
  
  private AnnouncementDataRefreshTask announcementDataRefreshTask;
  
  public static AnnouncementServerProxy getInstance() {
    return instance;
  }
  
  public PalaAnnouncementConfig getApiConfig() {
    return this.apiConfig;
  }
  
  public AnnouncementDataRefreshTask getAnnouncementDataRefreshTask() {
    return this.announcementDataRefreshTask;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    instance = this;
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    registerCommands();
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    registerConfigs();
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
    registerTasks();
    registerListeners();
  }
  
  private void registerListeners() {
    addListener(new Class[] { PlayerListener.class });
  }
  
  private void registerCommands() {
    SubCommandBuilder.create(AnnouncementCommand.class).register();
    SubCommandBuilder.create("patchnote", "Ouvrir la dernière annonce")
      .string()
      .executable()
      .register(PatchnoteCommand.class);
  }
  
  private void registerConfigs() {
    this.apiConfig = (PalaAnnouncementConfig)ConfigurationManager.getInstance().register(PalaAnnouncementConfig.class);
  }
  
  private void registerTasks() {
    this.announcementDataRefreshTask = new AnnouncementDataRefreshTask();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\AnnouncementServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */