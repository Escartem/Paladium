package fr.paladium.palajobs.core.entity.boss.attack;

import java.util.List;
import java.util.function.Function;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;

public class AttackClassic<T extends Entity> extends BaseAttack<T> {
  private final int probability;
  
  private final int duration;
  
  private IEntitySelector selector;
  
  private Function<T, Boolean> callback;
  
  public AttackClassic(int probability, int duration) {
    this.probability = probability;
    this.duration = duration;
  }
  
  public AttackClassic<T> setCallback(Function<T, Boolean> callback) {
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
    if (list.isEmpty())
      return false; 
    return ((Boolean)this.callback.apply(entity)).booleanValue();
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


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\attack\AttackClassic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */