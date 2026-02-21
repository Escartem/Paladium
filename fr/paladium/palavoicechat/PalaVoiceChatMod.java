package fr.paladium.palavoicechat;

import cpw.mods.fml.common.FMLCommonHandler;
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
import fr.paladium.palavoicechat.client.ClientProxy;
import fr.paladium.palavoicechat.common.CommonProxy;
import fr.paladium.palavoicechat.server.ServerProxy;

@Mod(modid = "palavoicechat", version = "1.0.0", acceptableRemoteVersions = "*")
public class PalaVoiceChatMod {
  @Instance("palavoicechat")
  private static PalaVoiceChatMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.palavoicechat.client.ClientProxy", serverSide = "fr.paladium.palavoicechat.server.ServerProxy")
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
  
  public static PalaVoiceChatMod getInstance() {
    return instance;
  }
  
  public static ClientProxy getClientProxy() {
    if (Side.CLIENT.equals(FMLCommonHandler.instance().getSide()))
      return (ClientProxy)proxy; 
    return null;
  }
  
  public static ServerProxy getServerProxy() {
    if (Side.SERVER.equals(FMLCommonHandler.instance().getSide()))
      return (ServerProxy)proxy; 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\PalaVoiceChatMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */