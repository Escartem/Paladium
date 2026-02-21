package fr.paladium.palavote;

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
import fr.paladium.palavote.client.ClientProxy;
import fr.paladium.palavote.common.CommonProxy;
import fr.paladium.palavote.server.ServerProxy;

@Mod(modid = "palavote", version = "1.0.0", acceptableRemoteVersions = "*")
public class PalaVoteMod {
  @Instance("palavote")
  private static PalaVoteMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.palavote.client.ClientProxy", serverSide = "fr.paladium.palavote.server.ServerProxy")
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
  
  public static PalaVoteMod getInstance() {
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


/* Location:              E:\Paladium\!\fr\paladium\palavote\PalaVoteMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */