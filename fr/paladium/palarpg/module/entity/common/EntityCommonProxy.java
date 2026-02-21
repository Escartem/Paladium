package fr.paladium.palarpg.module.entity.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.entity.common.entity.attack.AttackManager;
import fr.paladium.palarpg.module.entity.common.entity.behavior.BehaviorManager;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile;
import fr.paladium.palarpg.module.entity.common.network.SCPacketUpdateRPGEntitySize;
import java.io.File;

public class EntityCommonProxy extends AModProxy {
  public static File CONFIG_DIRECTORY;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    initNetwork("palarpg_entity");
    PacketUtils.registerPacket(getNetwork(), SCPacketUpdateRPGEntitySize.class);
    CONFIG_DIRECTORY = new File(event.getModConfigurationDirectory(), "rpgentities");
    int entityID = 0;
    EntityRegistry.registerModEntity(RPGMobEntity.class, "RPGMobEntity", entityID++, PalaRPGMod.getInstance(), 100, 1, true);
    EntityRegistry.registerModEntity(RPGProjectile.class, "RPGProjectile", entityID++, PalaRPGMod.getInstance(), 100, 1, true);
    AttackManager.init();
    BehaviorManager.init();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\EntityCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */