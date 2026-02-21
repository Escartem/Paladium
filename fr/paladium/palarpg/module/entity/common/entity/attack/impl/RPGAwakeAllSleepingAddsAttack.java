package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.SleepBehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.List;
import java.util.Optional;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class RPGAwakeAllSleepingAddsAttack extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "AWAKE_ALL_SLEEPING_ADDS";
  
  private static final SleepingEntitySelector SELECTOR = new SleepingEntitySelector();
  
  private String animationIdle;
  
  private long animationIdleDuration;
  
  private String animationEnd;
  
  private long animationEndDuration;
  
  private boolean active = false;
  
  public RPGAwakeAllSleepingAddsAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.animationIdle = (String)getData("animationLoop", "animation_loop");
    this.animationIdleDuration = ((Double)getData("animationLoopDuration", Double.valueOf(1000.0D))).longValue();
    this.animationEnd = (String)getData("animationEnd", "animation_end");
    this.animationEndDuration = ((Double)getData("animationEndDuration", Double.valueOf(1000.0D))).longValue();
  }
  
  public boolean canPerform() {
    if (!super.canPerform())
      return false; 
    AxisAlignedBB aabb = ((RPGMobEntity)getEntity()).field_70121_D.func_72329_c().func_72314_b(getAttack().getRange(), 4.0D, getAttack().getRange());
    List<RPGMobEntity> mobs = ((RPGMobEntity)getEntity()).field_70170_p.func_82733_a(RPGMobEntity.class, aabb, SELECTOR);
    if (mobs.isEmpty())
      return false; 
    return true;
  }
  
  public void perform() {
    super.perform();
    if (this.active && !((RPGMobEntity)getEntity()).field_70170_p.field_72995_K) {
      if (((RPGMobEntity)getEntity()).getAnimated().getCurrentAnimation() == null || !((RPGMobEntity)getEntity()).getAnimated().getCurrentAnimation().getName().equalsIgnoreCase(this.animationIdle))
        ((RPGMobEntity)getEntity()).playAnimation(this.animationIdle, this.animationIdleDuration, true); 
      return;
    } 
    AxisAlignedBB aabb = ((RPGMobEntity)getEntity()).field_70121_D.func_72329_c().func_72314_b(getAttack().getRange(), 4.0D, getAttack().getRange());
    List<RPGMobEntity> mobs = ((RPGMobEntity)getEntity()).field_70170_p.func_82733_a(RPGMobEntity.class, aabb, SELECTOR);
    if (mobs.isEmpty())
      return; 
    int iter = 5;
    for (RPGMobEntity mob : mobs) {
      if (iter <= 0)
        break; 
      mob.getBehavior("SLEEP").ifPresent(SleepBehavior::awake);
      iter--;
    } 
  }
  
  public void onEnd() {
    super.onEnd();
    if (!((RPGMobEntity)getEntity()).field_70170_p.field_72995_K)
      ((RPGMobEntity)getEntity()).playAnimation(this.animationEnd, this.animationEndDuration, true); 
  }
  
  public void onAnimationEnded(Entity entity) {
    this.active = true;
    if (!((RPGMobEntity)getEntity()).field_70170_p.field_72995_K)
      ((RPGMobEntity)getEntity()).playAnimation(this.animationIdle, this.animationIdleDuration, true); 
  }
  
  public String getID() {
    return "AWAKE_ALL_SLEEPING_ADDS";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGAwakeAllSleepingAddsAttack(attack, entity);
  }
  
  public RPGAwakeAllSleepingAddsAttack() {}
  
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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGAwakeAllSleepingAddsAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */