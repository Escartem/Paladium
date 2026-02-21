package fr.paladium.palajobs.core.entity.boss.attack;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class AttackProjectile<T extends Entity> extends BaseAttack<T> {
  private final double minDist;
  
  private final double maxDist;
  
  private final int probability;
  
  private final int duration;
  
  private IEntitySelector selector;
  
  private BiFunction<T, EntityLivingBase, Boolean> callback;
  
  public AttackProjectile(double minDist, double maxDist, int probability, int duration) {
    this.minDist = minDist;
    this.maxDist = maxDist;
    this.probability = probability;
    this.duration = duration;
  }
  
  public AttackProjectile<T> setCallback(BiFunction<T, EntityLivingBase, Boolean> callback) {
    this.callback = callback;
    return this;
  }
  
  public boolean perform(T entity) {
    if (this.selector == null)
      this.selector = new IEntitySelector() {
          public boolean func_82704_a(Entity entity) {
            return (entity instanceof EntityPlayer && !((EntityPlayer)entity).field_71075_bZ.field_75102_a);
          }
        }; 
    if (this.callback == null)
      return false; 
    if (!(entity instanceof EntityLiving))
      return false; 
    EntityLiving living = (EntityLiving)entity;
    double targetRange = getTargetDistance(living);
    List<?> list = ((Entity)entity).field_70170_p.func_82733_a(EntityPlayer.class, ((Entity)entity).field_70121_D.func_72314_b(targetRange, 4.0D, targetRange), this.selector);
    Collections.shuffle(list);
    EntityLivingBase target = null;
    for (Object to : list) {
      if (!(to instanceof EntityLivingBase))
        continue; 
      EntityLivingBase t = (EntityLivingBase)to;
      if (t.field_70128_L)
        continue; 
      if (!living.func_70635_at().func_75522_a((Entity)t))
        continue; 
      double dist = entity.func_70032_d((Entity)t);
      if (dist < this.minDist || dist > this.maxDist)
        continue; 
      target = t;
    } 
    if (target == null)
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
  
  private double getTargetDistance(EntityLiving entity) {
    IAttributeInstance iattributeinstance = entity.func_110148_a(SharedMonsterAttributes.field_111265_b);
    return (iattributeinstance == null) ? 16.0D : iattributeinstance.func_111126_e();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\attack\AttackProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */