package fr.paladium.palawarzoneevent;

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
import fr.paladium.palawarzoneevent.common.CommonProxy;

@Mod(modid = "palawarzoneevent", version = "1.0.0", acceptableRemoteVersions = "*", dependencies = "required-after:atlas")
public class PalaWarzoneEventMod {
  @Instance("palawarzoneevent")
  private static PalaWarzoneEventMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.palawarzoneevent.client.ClientProxy", serverSide = "fr.paladium.palawarzoneevent.server.ServerProxy")
  public static CommonProxy proxy;
  
  public PalaWarzoneEventMod() {
    ModuleController.create("fr.paladium.palawarzoneevent.module");
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
  
  public static PalaWarzoneEventMod getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\PalaWarzoneEventMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */