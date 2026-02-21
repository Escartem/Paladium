package fr.paladium.palarpg.module.profile;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.atlas.lib.module.dto.Module;
import fr.paladium.atlas.lib.module.dto.Module.Instance;
import fr.paladium.atlas.lib.module.dto.Module.Logger;
import fr.paladium.atlas.lib.module.dto.Module.SidedProxy;
import fr.paladium.atlas.lib.module.logger.ModuleLogger;
import fr.paladium.palarpg.module.profile.common.ProfileCommonProxy;

@Module("profile")
public class ProfileModule {
  @Logger
  public static ModuleLogger logger;
  
  @Instance
  public static ProfileModule instance;
  
  @SidedProxy(clientSide = "fr.paladium.palarpg.module.profile.client.ProfileClientProxy", serverSide = "fr.paladium.palarpg.module.profile.server.ProfileServerProxy")
  public static ProfileCommonProxy proxy;
  
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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\ProfileModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */