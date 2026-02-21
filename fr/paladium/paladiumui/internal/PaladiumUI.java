package fr.paladium.paladiumui.internal;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.paladiumui.internal.proxy.common.CommonProxy;

@Mod(modid = "paladium-ui", version = "1.0.0", acceptableRemoteVersions = "*", dependencies = "required-after:zephyr-ui")
public class PaladiumUI {
  @Instance("paladium-ui")
  private static PaladiumUI instance;
  
  @SidedProxy(clientSide = "fr.paladium.paladiumui.internal.proxy.client.ClientProxy", serverSide = "fr.paladium.paladiumui.internal.proxy.server.ServerProxy")
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
  
  public static PaladiumUI getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\internal\PaladiumUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */