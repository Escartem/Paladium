package fr.paladium.palawarzoneevent.module.warzone.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitNetwork;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palawarzoneevent.module.warzone.common.WarzoneCommonProxy;
import fr.paladium.palawarzoneevent.module.warzone.common.network.RBPacketGetCaptureHolder;
import fr.paladium.palawarzoneevent.module.warzone.common.network.RBPacketRequestCaptureClassementPage;
import fr.paladium.palawarzoneevent.module.warzone.common.network.RBPacketRequestCurrentWarzonePhase;
import fr.paladium.palawarzoneevent.module.warzone.common.network.RBPacketRequestPlayerCount;
import fr.paladium.palawarzoneevent.module.warzone.server.command.WarzoneCommand;
import fr.paladium.palawarzoneevent.module.warzone.server.config.WarzoneConfig;
import fr.paladium.palawarzoneevent.module.warzone.server.listener.WarzoneCaptureListener;
import fr.paladium.palawarzoneevent.module.warzone.server.listener.WarzoneLaunchpadListener;
import fr.paladium.palawarzoneevent.module.warzone.server.phase.WarzonePhaseManager;
import java.io.File;

public class WarzoneServerProxy extends WarzoneCommonProxy {
  private WarzoneConfig config;
  
  private File configFilePath;
  
  public WarzoneConfig getConfig() {
    return this.config;
  }
  
  public File getConfigFilePath() {
    return this.configFilePath;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { WarzoneCaptureListener.class });
    addListener(new Class[] { WarzoneLaunchpadListener.class });
    CommandRegistry.register(new Class[] { WarzoneCommand.class });
    this.configFilePath = new File(event.getModConfigurationDirectory() + File.separator + "palawarzoneevent/warzone.json");
    try {
      this.config = (WarzoneConfig)JsonConfigLoader.loadConfig(this.configFilePath, WarzoneConfig.class);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load LargageConfig", e);
    } 
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    RabbitNetwork.createNetwork("warzone")
      .registerPacket(RBPacketGetCaptureHolder.class)
      .registerPacket(RBPacketRequestPlayerCount.class)
      .registerPacket(RBPacketRequestCurrentWarzonePhase.class)
      .registerPacket(RBPacketRequestCaptureClassementPage.class);
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> WarzonePhaseManager.inst().executeDefaultCommands() });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\WarzoneServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */