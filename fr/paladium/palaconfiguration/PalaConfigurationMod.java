package fr.paladium.palaconfiguration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palaconfiguration.common.CommonProxy;

@Mod(modid = "palaconfiguration", version = "1.0.0", acceptableRemoteVersions = "*", dependencies = "required-after:palaforge-utils")
public class PalaConfigurationMod {
  @SidedProxy(clientSide = "fr.paladium.palaconfiguration.client.ClientProxy", serverSide = "fr.paladium.palaconfiguration.server.ServerProxy")
  public static CommonProxy proxy;
  
  @Instance("palaconfiguration")
  private static PalaConfigurationMod instance;
  
  public static PalaConfigurationMod getInstance() {
    return instance;
  }
  
  @EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
    proxy.onPreInit(event);
  }
  
  @EventHandler
  public void onInit(FMLInitializationEvent event) {
    proxy.onInit(event);
  }
  
  @EventHandler
  public void onPostInit(FMLPostInitializationEvent event) {
    proxy.onPostInit(event);
  }
  
  @EventHandler
  public void onServerStarting(FMLServerStartingEvent event) {
    proxy.onServerStarting(event);
  }
  
  @EventHandler
  public void onServerStarted(FMLServerStartedEvent event) {
    proxy.onServerStarted(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\PalaConfigurationMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */