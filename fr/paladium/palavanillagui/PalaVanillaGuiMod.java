package fr.paladium.palavanillagui;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palavanillagui.common.CommonProxy;

@Mod(modid = "palavanillagui", version = "1.0.0", acceptableRemoteVersions = "*", dependencies = "required-after:zephyr-ui")
public class PalaVanillaGuiMod {
  @Instance("palavanillagui")
  private static PalaVanillaGuiMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.palavanillagui.client.ClientProxy", serverSide = "fr.paladium.palavanillagui.server.ServerProxy")
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
  
  public static PalaVanillaGuiMod getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\PalaVanillaGuiMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */