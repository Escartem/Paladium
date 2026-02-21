package fr.paladium.palarpg.module.entity.common.network;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palarpg.module.entity.client.loader.RPGEntityModelLoader;
import fr.paladium.palarpg.module.entity.client.loader.cache.RPGEntityModelCache;
import fr.paladium.palarpg.module.entity.common.EntityCommonProxy;

public class SCPacketEntityRPGReloadModel extends ForgePacket {
  public void processClient() {
    RPGEntityModelCache.clear();
    RPGEntityModelLoader.load(EntityCommonProxy.CONFIG_DIRECTORY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\network\SCPacketEntityRPGReloadModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */