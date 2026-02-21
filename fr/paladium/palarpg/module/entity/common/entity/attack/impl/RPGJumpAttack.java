package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class RPGJumpAttack extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "JUMP_ATTACK";
  
  private int executionTick = 0;
  
  public RPGJumpAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || ((RPGMobEntity)getEntity()).func_70638_az() == null)
      return false; 
    return true;
  }
  
  public void perform() {
    this.executionTick++;
    if (((RPGMobEntity)getEntity()).func_70638_az() == null)
      return; 
    if (this.executionTick == 20) {
      EntityLivingBase living = ((RPGMobEntity)getEntity()).func_70638_az();
      double dx = living.field_70165_t - ((RPGMobEntity)getEntity()).field_70165_t;
      double dz = living.field_70161_v - ((RPGMobEntity)getEntity()).field_70161_v;
      float dist = MathHelper.func_76133_a(dx * dx + dz * dz);
      if (dist >= 1.0D) {
        dx /= dist;
        dz /= dist;
        float adjustedStrength = 0.5F * dist / 4.0F;
        ((RPGMobEntity)getEntity()).field_70159_w = dx * adjustedStrength;
        ((RPGMobEntity)getEntity()).field_70179_y = dz * adjustedStrength;
      } 
      ((RPGMobEntity)getEntity()).field_70181_x = 1.0D;
    } 
  }
  
  public void onEnd() {
    super.onEnd();
    this.executionTick = 0;
    if (((RPGMobEntity)getEntity()).func_70638_az() == null)
      return; 
    EntityLivingBase target = ((RPGMobEntity)getEntity()).func_70638_az();
    double distance = ((RPGMobEntity)getEntity()).func_70032_d((Entity)target);
    if (distance < 2.0D)
      damage((Entity)target); 
  }
  
  public String getID() {
    return "JUMP_ATTACK";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGJumpAttack(attack, entity);
  }
  
  public RPGJumpAttack() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGJumpAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */