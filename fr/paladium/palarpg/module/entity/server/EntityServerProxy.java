package fr.paladium.palarpg.module.entity.server;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palarpg.module.entity.common.EntityCommonProxy;
import fr.paladium.palarpg.module.entity.server.loader.RPGEntityLoader;

public class EntityServerProxy extends EntityCommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    RPGEntityLoader.load(EntityCommonProxy.CONFIG_DIRECTORY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\EntityServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */