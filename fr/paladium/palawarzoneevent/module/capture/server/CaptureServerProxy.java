package fr.paladium.palawarzoneevent.module.capture.server;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palawarzoneevent.module.capture.common.CaptureCommonProxy;
import fr.paladium.palawarzoneevent.module.capture.server.command.CaptureAdminCommand;
import fr.paladium.palawarzoneevent.module.capture.server.config.CaptureConfig;
import fr.paladium.palawarzoneevent.module.capture.server.listener.CaptureServerTickListener;
import java.io.File;

public class CaptureServerProxy extends CaptureCommonProxy {
  private File configFilePath;
  
  private CaptureConfig config;
  
  public File getConfigFilePath() {
    return this.configFilePath;
  }
  
  public CaptureConfig getConfig() {
    return this.config;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { CaptureServerTickListener.class });
    CommandRegistry.register(new Class[] { CaptureAdminCommand.class });
    this.configFilePath = new File(event.getModConfigurationDirectory() + File.separator + "palawarzoneevent/capture.json");
    try {
      this.config = (CaptureConfig)JsonConfigLoader.loadConfig(this.configFilePath, CaptureConfig.class);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load LargageConfig", e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\server\CaptureServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */