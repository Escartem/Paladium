package fr.paladium.palamod.modules.palaboss.common.init;

import cpw.mods.fml.common.registry.EntityRegistry;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.palaboss.common.boss.BossAttributes;
import fr.paladium.palamod.modules.palaboss.common.boss.BossRegistry;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityArachnaWeb;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityGear;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityIceProjectile;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityMagma;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntitySandstorm;
import java.util.Map;

public class EntityInit {
  public static void registerBossEntities() {
    BossRegistry bossRegistry = BossRegistry.INSTANCE;
    for (Map.Entry<Class<? extends EntityBossBase>, BossAttributes> boss : (Iterable<Map.Entry<Class<? extends EntityBossBase>, BossAttributes>>)bossRegistry
      .getRegisteredBosses().entrySet()) {
      BossAttributes bossAttributes = boss.getValue();
      EntityRegistry.registerGlobalEntityID(boss.getKey(), bossAttributes.getName(), 
          EntityRegistry.findGlobalUniqueEntityId(), bossAttributes
          .getAttributeInteger("backgroundEggColor", 1), bossAttributes
          .getAttributeInteger("foregroundEggColor", 1));
      EntityRegistry.registerModEntity(boss.getKey(), bossAttributes.getName(), bossAttributes
          .getEntityID(), PalaMod.instance, bossAttributes
          .getAttributeInteger("trackingRange", 40), 1, true);
    } 
  }
  
  public static void registerProjectiles() {
    EntityRegistry.registerGlobalEntityID(EntityGear.class, "entityGear", 
        EntityRegistry.findGlobalUniqueEntityId(), 1, 1);
    EntityRegistry.registerModEntity(EntityGear.class, "entityGear", 460, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityMagma.class, "entityMagma", 
        EntityRegistry.findGlobalUniqueEntityId());
    EntityRegistry.registerModEntity(EntityMagma.class, "entityMagma", 461, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntitySandstorm.class, "entitySandstorm", 
        EntityRegistry.findGlobalUniqueEntityId());
    EntityRegistry.registerModEntity(EntitySandstorm.class, "entitySandstorm", 462, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityArachnaWeb.class, "entityArachnaWeb", 
        EntityRegistry.findGlobalUniqueEntityId());
    EntityRegistry.registerModEntity(EntityArachnaWeb.class, "entityArachnaWeb", 463, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityIceProjectile.class, "entityIceProjectile", 
        EntityRegistry.findGlobalUniqueEntityId());
    EntityRegistry.registerModEntity(EntityIceProjectile.class, "entityIceProjectile", 464, PalaMod.instance, 40, 1, true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\init\EntityInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */