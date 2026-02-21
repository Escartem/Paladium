package fr.paladium;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.common.CommonModule;

@Mod(modid = "common", name = "Paladium Common", dependencies = "required-after:Forge;required-after:apollon;required-after:helios;required-after:palaforge-utils;after:palakeybind", acceptedMinecraftVersions = "[1.7.10]")
public class CommonMod {
  @SidedProxy(clientSide = "fr.paladium.client.CommonClientProxy", serverSide = "fr.paladium.server.CommonServerProxy")
  public static CommonModule proxy;
  
  @Instance("common")
  private static CommonMod instance;
  
  public static CommonMod getInstance() {
    return instance;
  }
  
  static {
    try {
      CommonMod.class.getClassLoader().loadClass("fr.paladium.common.utils.ITooltipWiki");
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    } 
    try {
      Class.forName("fr.paladium.common.utils.ITooltipWiki");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } 
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
}


/* Location:              E:\Paladium\!\fr\paladium\CommonMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */