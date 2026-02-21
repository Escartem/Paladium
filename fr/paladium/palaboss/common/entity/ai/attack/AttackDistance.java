package fr.paladium.palaboss.common.entity.ai.attack;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaboss.common.entity.ai.attack.selector.AttackEntitySelector;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class AttackDistance<T extends EntityBossMob> extends ABaseAttack<T> {
  private final double minDist;
  
  private final double maxDist;
  
  private final AttackEntitySelector selector;
  
  private EntityLivingBase target;
  
  public AttackDistance(T entity, int probability, int duration, String animation, long animationDuration, float damage, double minDist, double maxDist) {
    super(entity, probability, duration, animation, animationDuration, damage);
    this.minDist = minDist;
    this.maxDist = maxDist;
    this.selector = new AttackEntitySelector(e -> {
          if (!(e instanceof EntityPlayer))
            return Boolean.valueOf(false); 
          EntityPlayer player = (EntityPlayer)e;
          if (player.field_71075_bZ.field_75102_a || !player.func_70089_S())
            return Boolean.valueOf(false); 
          double dist = getEntity().func_70032_d((Entity)player);
          return (dist < this.minDist || dist > this.maxDist) ? Boolean.valueOf(false) : Boolean.valueOf(getEntity().func_70635_at().func_75522_a((Entity)player));
        });
  }
  
  public boolean canPerform() {
    List<EntityPlayer> list = getEntitiesAround(EntityPlayer.class, this.maxDist, (IEntitySelector)this.selector);
    Collections.shuffle(list);
    if (list.isEmpty())
      return false; 
    this.target = (EntityLivingBase)list.get(0);
    return true;
  }
  
  public void perform() {
    double d0 = this.target.field_70165_t - ((EntityBossMob)getEntity()).field_70165_t;
    double d1 = this.target.field_70163_u - ((EntityBossMob)getEntity()).field_70163_u + getEntity().func_70047_e();
    double d2 = this.target.field_70161_v - ((EntityBossMob)getEntity()).field_70161_v;
    double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
    float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
    float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
    ((EntityBossMob)getEntity()).field_70125_A = f1;
    ((EntityBossMob)getEntity()).field_70177_z = f;
  }
  
  public void onEnd() {
    damage((Entity)this.target);
  }
  
  public void damage(Entity target) {
    double distance = getEntity().func_70032_d(target);
    if (distance < this.minDist || distance > this.maxDist)
      return; 
    super.damage(target);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\attack\AttackDistance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */