package fr.paladium.palaboss.common.entity.ai.attack;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class AttackMelee<T extends EntityBossMob> extends ABaseAttack<T> {
  private final double minDist;
  
  private final double maxDist;
  
  public AttackMelee(T entity, int probability, int duration, String animation, long animationDuration, float damage, double minDist, double maxDist) {
    super(entity, probability, duration, animation, animationDuration, damage);
    this.minDist = minDist;
    this.maxDist = maxDist;
  }
  
  public boolean canPerform() {
    return (getEntity().func_70638_az() != null && getEntity().func_70638_az().func_70089_S() && getEntity().func_70032_d((Entity)getEntity().func_70638_az()) >= this.minDist && getEntity().func_70032_d((Entity)getEntity().func_70638_az()) <= this.maxDist);
  }
  
  public void perform() {
    EntityLivingBase target = getEntity().func_70638_az();
    double d0 = target.field_70165_t - ((EntityBossMob)getEntity()).field_70165_t;
    double d1 = target.field_70163_u - ((EntityBossMob)getEntity()).field_70163_u + getEntity().func_70047_e();
    double d2 = target.field_70161_v - ((EntityBossMob)getEntity()).field_70161_v;
    double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
    float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
    float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
    ((EntityBossMob)getEntity()).field_70125_A = f1;
    ((EntityBossMob)getEntity()).field_70177_z = f;
  }
  
  public void onEnd() {}
  
  public void damage(Entity target) {
    double distance = getEntity().func_70032_d(target);
    if (distance < this.minDist || distance > this.maxDist)
      return; 
    super.damage(target);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\attack\AttackMelee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */