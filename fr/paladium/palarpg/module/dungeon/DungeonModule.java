package fr.paladium.palarpg.module.dungeon;

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
import fr.paladium.palarpg.module.dungeon.client.DungeonClientProxy;
import fr.paladium.palarpg.module.dungeon.common.DungeonCommonProxy;
import fr.paladium.palarpg.module.dungeon.server.DungeonServerProxy;
import lombok.NonNull;

@Module("dungeon")
public class DungeonModule {
  @Logger
  public static ModuleLogger logger;
  
  @Instance
  public static DungeonModule instance;
  
  @SidedProxy(clientSide = "fr.paladium.palarpg.module.dungeon.client.DungeonClientProxy", serverSide = "fr.paladium.palarpg.module.dungeon.server.DungeonServerProxy")
  public static DungeonCommonProxy proxy;
  
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
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public static DungeonClientProxy getClient() {
    return (DungeonClientProxy)proxy;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static DungeonServerProxy getServer() {
    return (DungeonServerProxy)proxy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\DungeonModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */