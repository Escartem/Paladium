package fr.paladium.palaboss.common.entity.ai.attack;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import net.minecraft.entity.Entity;

public class AttackProjectile<T extends EntityBossMob> extends ABaseAttack<T> {
  private final double range;
  
  public AttackProjectile(T entity, int probability, int duration, String animation, long animationDuration, float damage, double range) {
    super(entity, probability, duration, animation, animationDuration, damage);
    this.range = range;
  }
  
  public boolean canPerform() {
    return (getEntity().func_70638_az() != null && getEntity().func_70032_d((Entity)getEntity().func_70638_az()) <= this.range);
  }
  
  public void perform() {}
  
  public void onEnd() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\attack\AttackProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */