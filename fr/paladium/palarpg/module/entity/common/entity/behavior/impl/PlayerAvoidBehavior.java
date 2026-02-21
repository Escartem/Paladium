package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.Vec3;

public class PlayerAvoidBehavior extends ABehavior {
  private static final String ID = "PLAYER_AVOID";
  
  private double distanceFromPlayer;
  
  private int updateTick;
  
  private Entity closestLivingEntity;
  
  private PathEntity entityPathEntity;
  
  public PlayerAvoidBehavior() {}
  
  public PlayerAvoidBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.distanceFromPlayer = ((Double)getData("distanceFromPlayer", Double.valueOf(1.0D))).doubleValue();
  }
  
  public boolean canExecute() {
    RPGMobEntity entity = getBehaviorOwner();
    if ((getBehaviorOwner()).field_70170_p.field_72995_K || !super.canExecute() || (entity.func_70638_az() != null && entity.func_70638_az().func_70089_S())) {
      this.closestLivingEntity = null;
      this.entityPathEntity = null;
      return false;
    } 
    this.closestLivingEntity = (Entity)entity.field_70170_p.func_72890_a((Entity)entity, this.distanceFromPlayer);
    if (this.closestLivingEntity == null) {
      this.closestLivingEntity = null;
      this.entityPathEntity = null;
      return false;
    } 
    if (--this.updateTick <= 0) {
      Vec3 vec3 = RandomPositionGenerator.func_75461_b((EntityCreature)entity, 32, 3, Vec3.func_72443_a(this.closestLivingEntity.field_70165_t, this.closestLivingEntity.field_70163_u, this.closestLivingEntity.field_70161_v));
      if (vec3 == null)
        return false; 
      if (this.closestLivingEntity.func_70092_e(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c) >= this.closestLivingEntity.func_70068_e((Entity)entity)) {
        this.updateTick = 4 + entity.func_70681_au().nextInt(7);
        this.entityPathEntity = entity.func_70661_as().func_75488_a(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c);
        return (this.entityPathEntity == null) ? false : this.entityPathEntity.func_75880_b(vec3);
      } 
    } 
    return false;
  }
  
  public void execute() {
    RPGMobEntity entity = getBehaviorOwner();
    if (entity.func_70638_az() != null || this.entityPathEntity == null)
      return; 
    entity.func_70661_as().func_75484_a(this.entityPathEntity, 1.0D);
  }
  
  public String getID() {
    return "PLAYER_AVOID";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new PlayerAvoidBehavior(behaviorData, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\PlayerAvoidBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */