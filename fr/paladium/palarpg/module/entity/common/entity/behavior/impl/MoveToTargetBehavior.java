package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class MoveToTargetBehavior extends ABehavior {
  private static final String ID = "MOVE_TO_TARGET";
  
  private int updateTick = 0;
  
  private double minDist;
  
  private PathEntity entityPath;
  
  public MoveToTargetBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.minDist = ((Double)getData("minDistToPlayer", Double.valueOf(1.0D))).doubleValue();
  }
  
  public boolean canExecute() {
    if (!super.canExecute() || (getBehaviorOwner()).field_70170_p.field_72995_K || getBehaviorOwner().func_70638_az() == null || !getBehaviorOwner().func_70638_az().func_70089_S())
      return false; 
    RPGMobEntity attacker = getBehaviorOwner();
    EntityLivingBase target = getBehaviorOwner().func_70638_az();
    if (--this.updateTick <= 0) {
      double distance = attacker.func_70032_d((Entity)target);
      double distanceToPath = (this.entityPath != null) ? this.entityPath.func_75870_c().func_75829_a(new PathPoint(MathHelper.func_76128_c(target.field_70165_t), MathHelper.func_76128_c(target.field_70163_u), MathHelper.func_76128_c(target.field_70161_v))) : 0.0D;
      if (distance < this.minDist && distanceToPath < this.minDist && attacker.func_70635_at().func_75522_a((Entity)target)) {
        Vec3 vec = RandomPositionGenerator.func_75461_b((EntityCreature)attacker, 16, 7, Vec3.func_72443_a(target.field_70165_t, target.field_70163_u, target.field_70161_v));
        if (vec != null) {
          this.entityPath = attacker.func_70661_as().func_75488_a(vec.field_72450_a, vec.field_72448_b, vec.field_72449_c);
        } else {
          this.entityPath = null;
        } 
        this.updateTick = 4 + attacker.func_70681_au().nextInt(7);
        return (this.entityPath != null);
      } 
      if (this.entityPath == null || !attacker.func_70635_at().func_75522_a((Entity)target) || (distance > this.minDist && distanceToPath > this.minDist)) {
        this.entityPath = attacker.func_70661_as().func_75494_a((Entity)target);
        this.updateTick = 4 + attacker.func_70681_au().nextInt(7);
        return (this.entityPath != null);
      } 
    } 
    return true;
  }
  
  public void execute() {
    RPGMobEntity attacker = getBehaviorOwner();
    EntityLivingBase target = attacker.func_70638_az();
    if (this.updateTick > 0) {
      attacker.func_70661_as().func_75484_a(this.entityPath, 1.0D);
      this.updateTick = 0;
    } 
    attacker.func_70671_ap().func_75651_a((Entity)target, 30.0F, 30.0F);
    this.updateTick--;
  }
  
  public String getID() {
    return "MOVE_TO_TARGET";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new MoveToTargetBehavior(behaviorData, entity);
  }
  
  public MoveToTargetBehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\MoveToTargetBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */