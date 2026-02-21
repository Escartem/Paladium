package fr.paladium.palashop;

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
import fr.paladium.palashop.client.ClientProxy;
import fr.paladium.palashop.common.CommonProxy;
import fr.paladium.palashop.server.ServerProxy;

@Mod(modid = "palashop", version = "2.0.0", acceptableRemoteVersions = "*")
public class PalaShopMod {
  public static final String MODID = "palashop";
  
  @Instance("palashop")
  private static PalaShopMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.palashop.client.ClientProxy", serverSide = "fr.paladium.palashop.server.ServerProxy")
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
  
  public static PalaShopMod getInstance() {
    return instance;
  }
  
  @SideOnly(Side.CLIENT)
  public static ClientProxy getClient() {
    return (ClientProxy)proxy;
  }
  
  @SideOnly(Side.SERVER)
  public static ServerProxy getServer() {
    return (ServerProxy)proxy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\PalaShopMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */