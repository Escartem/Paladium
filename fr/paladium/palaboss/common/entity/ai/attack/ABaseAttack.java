package fr.paladium.palaboss.common.entity.ai.attack;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaboss.common.entity.EntityBossMob;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import software.bernie.geckolib3.core.event.CustomInstructionKeyframeEvent;
import software.bernie.geckolib3.entity.animation.Animation;

public abstract class ABaseAttack<T extends EntityBossMob> {
  private T entity;
  
  private int probability;
  
  private int duration;
  
  private String animation;
  
  private long animationDuration;
  
  private float damage;
  
  public void setEntity(T entity) {
    this.entity = entity;
  }
  
  public void setProbability(int probability) {
    this.probability = probability;
  }
  
  public void setDuration(int duration) {
    this.duration = duration;
  }
  
  public void setAnimation(String animation) {
    this.animation = animation;
  }
  
  public void setAnimationDuration(long animationDuration) {
    this.animationDuration = animationDuration;
  }
  
  public void setDamage(float damage) {
    this.damage = damage;
  }
  
  public T getEntity() {
    return this.entity;
  }
  
  public int getProbability() {
    return this.probability;
  }
  
  public int getDuration() {
    return this.duration;
  }
  
  public String getAnimation() {
    return this.animation;
  }
  
  public long getAnimationDuration() {
    return this.animationDuration;
  }
  
  public float getDamage() {
    return this.damage;
  }
  
  public ABaseAttack(T entity, int probability, int duration, String animation, long animationDuration, float damage) {
    this.entity = entity;
    this.probability = probability;
    this.duration = duration;
    this.animation = animation;
    this.animationDuration = animationDuration;
    this.damage = damage;
  }
  
  public void onStart() {
    playAnimation(this::onAnimationEnded);
  }
  
  public void onAnimationEnded(Entity entity) {}
  
  public void onAnimationKeyframe(CustomInstructionKeyframeEvent event) {}
  
  public void playAnimation(Consumer<Entity> callback) {
    if (this.animation != null && !this.animation.trim().isEmpty()) {
      Animation<Entity> animation = this.entity.playAnimation(this.animation, this.animationDuration, (FMLCommonHandler.instance().getSide() == Side.SERVER));
      if (callback != null)
        animation.setCallback(callback); 
    } 
  }
  
  public void damage(Entity target) {
    if (target != null && target.func_70089_S())
      target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this.entity), this.damage); 
  }
  
  public <S extends Entity> List<S> getEntitiesAround(Class<S> clazz, double range) {
    return getEntitiesAround(clazz, range, null);
  }
  
  public <S extends Entity> List<S> getEntitiesAround(Class<S> clazz, double range, IEntitySelector selector) {
    return ((EntityBossMob)this.entity).field_70170_p.func_82733_a(clazz, ((EntityBossMob)this.entity).field_70121_D.func_72314_b(range, 4.0D, range), selector);
  }
  
  public abstract boolean canPerform();
  
  public abstract void perform();
  
  public abstract void onEnd();
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\attack\ABaseAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */