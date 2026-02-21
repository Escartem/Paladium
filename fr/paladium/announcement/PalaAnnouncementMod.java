package fr.paladium.announcement;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.announcement.common.AnnouncementCommonProxy;

@Mod(modid = "announcement", version = "1.0.0", acceptableRemoteVersions = "*")
public class PalaAnnouncementMod {
  @Instance("announcement")
  private static PalaAnnouncementMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.announcement.client.AnnouncementClientProxy", serverSide = "fr.paladium.announcement.server.AnnouncementServerProxy")
  public static AnnouncementCommonProxy proxy;
  
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
  
  public static PalaAnnouncementMod getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\PalaAnnouncementMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */