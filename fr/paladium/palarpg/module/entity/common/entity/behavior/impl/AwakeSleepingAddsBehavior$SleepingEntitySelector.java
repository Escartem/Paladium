package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.Optional;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

class SleepingEntitySelector implements IEntitySelector {
  public boolean func_82704_a(Entity entity) {
    if (!(entity instanceof RPGMobEntity))
      return false; 
    RPGMobEntity rpgMob = (RPGMobEntity)entity;
    if (!rpgMob.func_70089_S() || !rpgMob.hasBehavior("SLEEP"))
      return false; 
    Optional<SleepBehavior> optSleepBehavior = rpgMob.getBehavior("SLEEP");
    if (!optSleepBehavior.isPresent())
      return false; 
    return ((SleepBehavior)optSleepBehavior.get()).isSleeping();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\AwakeSleepingAddsBehavior$SleepingEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */