package fr.paladium.palawarzoneevent.module.largage.server;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palawarzoneevent.module.largage.common.LargageCommonProxy;
import fr.paladium.palawarzoneevent.module.largage.server.command.LargageAdminCommand;
import fr.paladium.palawarzoneevent.module.largage.server.config.LargageConfig;
import fr.paladium.palawarzoneevent.module.largage.server.listener.LargagePlayerLoggedInListener;
import java.io.File;

public class LargageServerProxy extends LargageCommonProxy {
  private File configFilePath;
  
  private LargageConfig config;
  
  public File getConfigFilePath() {
    return this.configFilePath;
  }
  
  public LargageConfig getConfig() {
    return this.config;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { LargagePlayerLoggedInListener.class });
    CommandRegistry.register(new Class[] { LargageAdminCommand.class });
    this.configFilePath = new File(event.getModConfigurationDirectory() + File.separator + "palawarzoneevent/largage.json");
    try {
      this.config = (LargageConfig)JsonConfigLoader.loadConfig(this.configFilePath, LargageConfig.class);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load LargageConfig", e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\server\LargageServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */