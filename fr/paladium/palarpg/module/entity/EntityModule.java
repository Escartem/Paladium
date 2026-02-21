package fr.paladium.palarpg.module.entity;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.atlas.lib.module.dto.Module;
import fr.paladium.atlas.lib.module.dto.Module.Instance;
import fr.paladium.atlas.lib.module.dto.Module.Logger;
import fr.paladium.atlas.lib.module.dto.Module.SidedProxy;
import fr.paladium.atlas.lib.module.logger.ModuleLogger;
import fr.paladium.palarpg.module.entity.common.EntityCommonProxy;

@Module("entity")
public class EntityModule {
  @Logger
  public static ModuleLogger logger;
  
  @Instance
  public static EntityModule instance;
  
  @SidedProxy(clientSide = "fr.paladium.palarpg.module.entity.client.EntityClientProxy", serverSide = "fr.paladium.palarpg.module.entity.server.EntityServerProxy")
  public static EntityCommonProxy proxy;
  
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
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\EntityModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */