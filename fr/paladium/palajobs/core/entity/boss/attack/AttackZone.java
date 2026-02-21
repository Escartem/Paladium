package fr.paladium.palajobs.core.entity.boss.attack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;

public class AttackZone<T extends Entity> extends BaseAttack<T> {
  private final double minDist;
  
  private final double maxDist;
  
  private final int probability;
  
  private final int duration;
  
  private IEntitySelector selector;
  
  private BiFunction<T, List<EntityLivingBase>, Boolean> callback;
  
  public AttackZone(double minDist, double maxDist, int probability, int duration) {
    this.minDist = minDist;
    this.maxDist = maxDist;
    this.probability = probability;
    this.duration = duration;
  }
  
  public AttackZone<T> setCallback(BiFunction<T, List<EntityLivingBase>, Boolean> callback) {
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
    List<EntityLivingBase> entities = new ArrayList<>();
    for (Object to : list) {
      if (!(to instanceof EntityLivingBase))
        continue; 
      EntityLivingBase t = (EntityLivingBase)to;
      if (t.field_70128_L)
        continue; 
      double dist = entity.func_70032_d((Entity)t);
      if (dist < this.minDist || dist > this.maxDist)
        continue; 
      entities.add(t);
    } 
    return ((Boolean)this.callback.apply(entity, entities)).booleanValue();
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


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\attack\AttackZone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */