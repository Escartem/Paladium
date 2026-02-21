package fr.paladium.palaoracle.server.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import fr.paladium.palaforgeutils.lib.api.gateway.GatewayUtils;
import fr.paladium.palaoracle.core.proxy.CommonProxy;
import fr.paladium.palaoracle.server.api.IOracleAPI;
import fr.paladium.palaoracle.server.managers.PalaOracleManager;
import java.io.File;

public class ServerProxy extends CommonProxy {
  private static ServerProxy instance;
  
  private IOracleAPI api;
  
  private File configDirectory;
  
  public IOracleAPI getApi() {
    return this.api;
  }
  
  public File getConfigDirectory() {
    return this.configDirectory;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    instance = this;
    initManager();
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    this.api = (IOracleAPI)GatewayUtils.registerAPI(IOracleAPI.class);
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    super.onServerStarted(event);
  }
  
  private void initManager() {
    new PalaOracleManager();
  }
  
  public static ServerProxy getInstance() {
    if (instance == null)
      instance = new ServerProxy(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaoracle\server\proxy\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */