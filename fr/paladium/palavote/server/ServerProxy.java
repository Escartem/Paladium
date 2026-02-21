package fr.paladium.palavote.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palavote.common.CommonProxy;
import fr.paladium.palavote.server.config.PalaVoteConfig;
import fr.paladium.palavote.server.listener.PalaVoteInteractServerListener;
import fr.paladium.palavote.server.manager.PalaVoteManager;

public class ServerProxy extends CommonProxy {
  private PalaVoteConfig config;
  
  public PalaVoteConfig getConfig() {
    return this.config;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { PalaVoteInteractServerListener.class });
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    this.config = (PalaVoteConfig)ConfigurationManager.getInstance().register(PalaVoteConfig.class);
    if (this.config == null)
      throw new RuntimeException("[PalaVote] Failed to load the configuration file"); 
    PalaVoteManager.init(this.config);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */