package fr.paladium.palawarzoneevent.module.warzone;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.atlas.lib.module.dto.Module;
import fr.paladium.atlas.lib.module.dto.Module.Instance;
import fr.paladium.atlas.lib.module.dto.Module.Logger;
import fr.paladium.atlas.lib.module.dto.Module.SidedProxy;
import fr.paladium.atlas.lib.module.logger.ModuleLogger;
import fr.paladium.palawarzoneevent.module.warzone.common.WarzoneCommonProxy;
import fr.paladium.palawarzoneevent.module.warzone.server.WarzoneServerProxy;
import lombok.NonNull;

@Module("warzone")
public class WarzoneModule {
  @Logger
  public static ModuleLogger logger;
  
  @Instance
  public static WarzoneModule instance;
  
  @SidedProxy(clientSide = "fr.paladium.palawarzoneevent.module.warzone.client.WarzoneClientProxy", serverSide = "fr.paladium.palawarzoneevent.module.warzone.server.WarzoneServerProxy")
  public static WarzoneCommonProxy proxy;
  
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
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static WarzoneServerProxy getServer() {
    return (WarzoneServerProxy)proxy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\WarzoneModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */