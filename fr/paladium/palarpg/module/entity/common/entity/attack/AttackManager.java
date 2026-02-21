package fr.paladium.palarpg.module.entity.common.entity.attack;

import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGAttackMelee;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGAttackProjectile;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGAttackZone;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGAttractPlayerAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGAwakeAllSleepingAddsAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGChargeAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGDashThroughAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGFrigoriaBoost;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGJumpAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGLazerForwardAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGNimbriaChargeAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGShootMultipleProjectileAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGSpreadProjectileAroundAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGSummonAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGSummonFallingProjectile;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGThrowEntityAttack;
import fr.paladium.palarpg.module.entity.common.entity.attack.impl.RPGTransformBlockIntoLavaAroundAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

public final class AttackManager {
  private static final Map<String, ARPGBaseAttack<RPGMobEntity>> ATTACKS = new HashMap<>();
  
  public static void init() {
    register((Class<? extends ARPGBaseAttack>[])new Class[] { 
          RPGAttackMelee.class, RPGAttackZone.class, RPGAttackProjectile.class, RPGChargeAttack.class, RPGAttractPlayerAttack.class, RPGSummonAttack.class, RPGSummonFallingProjectile.class, RPGLazerForwardAttack.class, RPGThrowEntityAttack.class, RPGJumpAttack.class, 
          RPGDashThroughAttack.class, RPGTransformBlockIntoLavaAroundAttack.class, RPGNimbriaChargeAttack.class, RPGAwakeAllSleepingAddsAttack.class, RPGSpreadProjectileAroundAttack.class, RPGShootMultipleProjectileAttack.class, RPGFrigoriaBoost.class });
  }
  
  public static void register(Class<? extends ARPGBaseAttack>... aRpgAttack) {
    for (Class<? extends ARPGBaseAttack> attack : aRpgAttack) {
      ARPGBaseAttack<RPGMobEntity> instance = null;
      try {
        instance = attack.newInstance();
      } catch (Exception e) {
        e.printStackTrace();
      } 
      if (ATTACKS.containsKey(instance.getID()))
        throw new RuntimeException("Another attack with the ID '" + instance.getID() + "' is already registered"); 
      ATTACKS.put(instance.getID(), instance);
    } 
  }
  
  @NonNull
  public static ARPGBaseAttack<RPGMobEntity> getAttack(String id, RPGAttack rpgAttack, RPGMobEntity entity) {
    ARPGBaseAttack<RPGMobEntity> attack = ATTACKS.get(id);
    if (attack == null)
      throw new RuntimeException("The attack with ID '" + id + "' doesn't exist"); 
    return attack.copy(rpgAttack, entity);
  }
  
  public static Map<String, ARPGBaseAttack<RPGMobEntity>> getAttacks() {
    return ATTACKS;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\AttackManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */