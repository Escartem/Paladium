package fr.paladium.palajobs.server.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palajobs.core.CommonProxy;
import fr.paladium.palajobs.core.utils.JsonUtils;
import fr.paladium.palajobs.server.config.ConfigManager;
import java.io.File;

public class ServerProxy extends CommonProxy {
  private File configDirectory;
  
  public File getConfigDirectory() {
    return this.configDirectory;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    this.configDirectory = event.getModConfigurationDirectory();
    initConfig();
  }
  
  private void initConfig() {
    try {
      ConfigManager configManager = (ConfigManager)JsonUtils.getFileObject("palajobs.json", ConfigManager.class);
      configManager.setInstance();
    } catch (Exception error) {
      ConfigManager configManager = new ConfigManager();
      configManager.setInstance();
      configManager.save();
      System.out.println("[PalaJobs] Configuration file palajobs.json not found, using default configuration.");
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\proxy\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */