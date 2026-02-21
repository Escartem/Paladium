package fr.paladium.palaboss.common.entity.impl.attack;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaboss.common.entity.ai.attack.AttackProjectile;
import fr.paladium.palaboss.common.entity.impl.DemoEntity;
import fr.paladium.palaforgeutils.lib.projectile.ProjectileHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;

public class DemoAttackProjectile extends AttackProjectile<DemoEntity> {
  public DemoAttackProjectile(DemoEntity entity) {
    super((EntityBossMob)entity, 1, 50, "shot", 1410L, 10.0F, 16.0D);
  }
  
  public void onStart() {
    ((DemoEntity)getEntity()).clearSpeed();
    super.onStart();
  }
  
  public void onAnimationEnded(Entity entity) {
    ProjectileHandler projHandler = new ProjectileHandler(EntityPig.class, ((DemoEntity)getEntity()).field_70170_p);
    projHandler.throwProjectile((Entity)getEntity(), (Entity)((DemoEntity)getEntity()).func_70638_az(), 1.5D, 1.0D);
  }
  
  public void onEnd() {
    ((DemoEntity)getEntity()).resetSpeed();
    super.onEnd();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\impl\attack\DemoAttackProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */