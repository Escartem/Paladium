package fr.paladium.palarpg;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.atlas.lib.module.ModuleController;
import fr.paladium.palarpg.common.CommonProxy;

@Mod(modid = "palarpg", version = "1.0.0", acceptableRemoteVersions = "*", dependencies = "required-after:palaforge-utils;required-after:atlas")
public class PalaRPGMod {
  @Instance("palarpg")
  private static PalaRPGMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.palarpg.client.ClientProxy", serverSide = "fr.paladium.palarpg.server.ServerProxy")
  public static CommonProxy proxy;
  
  public PalaRPGMod() {
    ModuleController.create("fr.paladium.palarpg.module");
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
  
  public static PalaRPGMod getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\PalaRPGMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */