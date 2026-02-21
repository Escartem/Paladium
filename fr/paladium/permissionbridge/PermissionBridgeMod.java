package fr.paladium.permissionbridge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.permissionbridge.common.CommonProxy;

@Mod(modid = "permissionbridge", version = "1.0.0", acceptableRemoteVersions = "*")
public class PermissionBridgeMod {
  @Instance("permissionbridge")
  private static PermissionBridgeMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.permissionbridge.client.ClientProxy", serverSide = "fr.paladium.permissionbridge.server.ServerProxy")
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
  
  public static PermissionBridgeMod getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\PermissionBridgeMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */