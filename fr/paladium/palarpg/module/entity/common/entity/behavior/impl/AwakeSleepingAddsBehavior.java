package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class AwakeSleepingAddsBehavior extends ABehavior {
  public static final String ID = "AWAKE_SLEEPING_ADDS";
  
  public AwakeSleepingAddsBehavior() {}
  
  private static final SleepingEntitySelector SLEEP_SELECTOR = new SleepingEntitySelector();
  
  public AwakeSleepingAddsBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
  }
  
  public boolean canExecute() {
    return (super.canExecute() && !(getBehaviorOwner()).field_70170_p.field_72995_K);
  }
  
  public void execute() {
    AxisAlignedBB aabb = (getBehaviorOwner()).field_70121_D;
    List<RPGMobEntity> sleepingMobs = (getBehaviorOwner()).field_70170_p.func_82733_a(RPGMobEntity.class, aabb, SLEEP_SELECTOR);
    if (sleepingMobs.isEmpty())
      return; 
    sleepingMobs.forEach(mob -> mob.getBehavior("SLEEP").ifPresent(SleepBehavior::awake));
  }
  
  public String getID() {
    return "AWAKE_SLEEPING_ADDS";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new AwakeSleepingAddsBehavior(behaviorData, entity);
  }
  
  private static class SleepingEntitySelector implements IEntitySelector {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\AwakeSleepingAddsBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */