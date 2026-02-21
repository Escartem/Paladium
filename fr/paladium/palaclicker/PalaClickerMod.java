package fr.paladium.palaclicker;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaclicker.client.ClientProxy;
import fr.paladium.palaclicker.common.CommonProxy;
import fr.paladium.palaclicker.server.ServerProxy;

@Mod(modid = "palaclicker", version = "1.0.0", acceptableRemoteVersions = "*", dependencies = "required-after:palaconfiguration")
public class PalaClickerMod {
  @Instance("palaclicker")
  private static PalaClickerMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.palaclicker.client.ClientProxy", serverSide = "fr.paladium.palaclicker.server.ServerProxy")
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
  
  public static PalaClickerMod getInstance() {
    return instance;
  }
  
  @SideOnly(Side.SERVER)
  public static ServerProxy getServer() {
    return (ServerProxy)proxy;
  }
  
  @SideOnly(Side.CLIENT)
  public static ClientProxy getClient() {
    return (ClientProxy)proxy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\PalaClickerMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */