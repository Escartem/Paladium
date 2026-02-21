package fr.paladium.palajobs.core.entity.boss.attack;

import java.util.function.BiFunction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class AttackMelee<T extends Entity> extends BaseAttack<T> {
  private final double minDist;
  
  private final double maxDist;
  
  private final int probability;
  
  private final int duration;
  
  private BiFunction<T, EntityLivingBase, Boolean> callback;
  
  public AttackMelee(double minDist, double maxDist, int probability, int duration) {
    this.minDist = minDist;
    this.maxDist = maxDist;
    this.probability = probability;
    this.duration = duration;
  }
  
  public AttackMelee<T> setCallback(BiFunction<T, EntityLivingBase, Boolean> callback) {
    this.callback = callback;
    return this;
  }
  
  public boolean perform(T entity) {
    if (this.callback == null)
      return false; 
    if (!(entity instanceof EntityLiving))
      return false; 
    EntityLiving living = (EntityLiving)entity;
    if (living.func_70638_az() == null)
      return false; 
    EntityLivingBase target = living.func_70638_az();
    if (target.field_70128_L)
      return false; 
    double dist = entity.func_70032_d((Entity)target);
    if (dist < this.minDist || dist > this.maxDist)
      return false; 
    boolean success = ((Boolean)this.callback.apply(entity, target)).booleanValue();
    if (success) {
      double d0 = target.field_70165_t - ((Entity)entity).field_70165_t;
      double d1 = target.field_70163_u - ((Entity)entity).field_70163_u + entity.func_70047_e();
      double d2 = target.field_70161_v - ((Entity)entity).field_70161_v;
      double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
      float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
      float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
      ((Entity)entity).field_70125_A = f1;
      ((Entity)entity).field_70177_z = f;
    } 
    return success;
  }
  
  public int getProbability() {
    return this.probability;
  }
  
  public int getDuration() {
    return this.duration;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\attack\AttackMelee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */