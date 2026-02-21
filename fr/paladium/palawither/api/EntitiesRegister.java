package fr.paladium.palawither.api;

import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palawither.PalaWitherMod;
import fr.paladium.palawither.common.entity.EntityBabyWither;
import fr.paladium.palawither.common.entity.EntityPassiveWither;
import fr.paladium.palawither.common.entity.EntityThirstyWither;
import fr.paladium.palawither.common.entity.projectile.EntityBabyWitherProjectile;
import fr.paladium.palawither.common.entity.projectile.EntityPredatorWitherProjectile;
import fr.paladium.palawither.common.entity.projectile.EntitySupremeWitherProjectile;
import fr.paladium.palawither.common.entity.targetable.EntityPredatorWither;
import fr.paladium.palawither.common.entity.targetable.EntitySupremeWither;

public class EntitiesRegister {
  public static void register() {
    RegistryUtils.entity(EntityBabyWither.class, null, 100, PalaWitherMod.getInstance());
    RegistryUtils.entity(EntityPassiveWither.class, null, 100, PalaWitherMod.getInstance());
    RegistryUtils.entity(EntityThirstyWither.class, null, 100, PalaWitherMod.getInstance());
    RegistryUtils.entity(EntityPredatorWither.class, null, 100, PalaWitherMod.getInstance());
    RegistryUtils.entity(EntitySupremeWither.class, null, 100, PalaWitherMod.getInstance());
    RegistryUtils.entity(EntityBabyWitherProjectile.class, null, 100, PalaWitherMod.getInstance());
    RegistryUtils.entity(EntityPredatorWitherProjectile.class, null, 100, PalaWitherMod.getInstance());
    RegistryUtils.entity(EntitySupremeWitherProjectile.class, null, 100, PalaWitherMod.getInstance());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\api\EntitiesRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */