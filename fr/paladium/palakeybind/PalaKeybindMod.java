package fr.paladium.palakeybind;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palakeybind.common.CommonProxy;

@Mod(modid = "palakeybind", version = "1.0.0", acceptableRemoteVersions = "*")
public class PalaKeybindMod {
  @Instance("palakeybind")
  private static PalaKeybindMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.palakeybind.client.ClientProxy", serverSide = "fr.paladium.palakeybind.server.ServerProxy")
  public static CommonProxy proxy;
  
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
  
  public static PalaKeybindMod getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\PalaKeybindMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */