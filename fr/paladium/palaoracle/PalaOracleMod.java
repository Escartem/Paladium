package fr.paladium.palaoracle;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palaoracle.client.proxy.ClientProxy;
import fr.paladium.palaoracle.core.proxy.CommonProxy;
import fr.paladium.palaoracle.server.PalaOracleServerInit;
import fr.paladium.palaoracle.server.proxy.ServerProxy;

@Mod(modid = "palaoracle", version = "0.1", acceptableRemoteVersions = "*", dependencies = "required-after:apollon")
public class PalaOracleMod {
  @Instance("palaoracle")
  public static PalaOracleMod instance;
  
  public static final String MODID = "palaoracle";
  
  public static final String VERSION = "0.1";
  
  @SidedProxy(clientSide = "fr.paladium.palaoracle.client.proxy.ClientProxy", serverSide = "fr.paladium.palaoracle.server.proxy.ServerProxy")
  public static CommonProxy proxy;
  
  public static CommonProxy getProxy() {
    return proxy;
  }
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    instance = this;
    proxy.onPreInit(e);
  }
  
  @EventHandler
  public void preInit(FMLPostInitializationEvent e) {
    instance = this;
    proxy.onPostInit(e);
  }
  
  @EventHandler
  public void init(FMLInitializationEvent e) {
    proxy.onInit(e);
  }
  
  @EventHandler
  public void serverStarting(FMLServerStartingEvent event) {
    new PalaOracleServerInit(event);
  }
  
  public static PalaOracleMod getInstance() {
    if (instance == null)
      instance = new PalaOracleMod(); 
    return instance;
  }
  
  public static void setInstance(PalaOracleMod instance) {
    PalaOracleMod.instance = instance;
  }
  
  public static ServerProxy getServer() {
    return (ServerProxy)proxy;
  }
  
  public static ClientProxy getClient() {
    return (ClientProxy)proxy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaoracle\PalaOracleMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */