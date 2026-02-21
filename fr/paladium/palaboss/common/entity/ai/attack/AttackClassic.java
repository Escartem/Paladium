package fr.paladium.palaboss.common.entity.ai.attack;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class AttackClassic<T extends EntityBossMob> extends ABaseAttack<T> {
  private final double range;
  
  public AttackClassic(T entity, int probability, int duration, String animation, long animationDuration, float range, float damage) {
    super(entity, probability, duration, animation, animationDuration, damage);
    this.range = range;
  }
  
  public boolean canPerform() {
    EntityLivingBase target = getEntity().func_70638_az();
    if (target == null || !(target instanceof net.minecraft.entity.player.EntityPlayer) || target.func_70068_e((Entity)getEntity()) > this.range)
      return false; 
    return true;
  }
  
  public void perform() {}
  
  public void onEnd() {
    damage((Entity)getEntity().func_70638_az());
  }
  
  public void damage(Entity target) {
    if (target.func_70068_e((Entity)getEntity()) > this.range)
      return; 
    super.damage(target);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\attack\AttackClassic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */