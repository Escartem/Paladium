package fr.paladium.palaboss.common.entity.ai.attack;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaboss.common.entity.ai.attack.selector.AttackEntitySelector;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import software.bernie.geckolib3.core.event.CustomInstructionKeyframeEvent;

public class AttackZone<T extends EntityBossMob> extends ABaseAttack<T> {
  private final double minDist;
  
  private final double maxDist;
  
  private final AttackEntitySelector selector;
  
  private final List<EntityPlayer> entities = new ArrayList<>();
  
  public AttackZone(T entity, double minDist, double maxDist, int probability, int duration, String animation, long animationDuration, float damage) {
    super(entity, probability, duration, animation, animationDuration, damage);
    this.minDist = minDist;
    this.maxDist = maxDist;
    this.selector = new AttackEntitySelector(e -> {
          if (!(e instanceof EntityPlayer))
            return Boolean.valueOf(false); 
          EntityPlayer player = (EntityPlayer)e;
          if (player.field_71075_bZ.field_75102_a)
            return Boolean.valueOf(false); 
          double dist = entity.func_70032_d((Entity)getEntity());
          return (dist < this.minDist || dist > this.maxDist) ? Boolean.valueOf(false) : Boolean.valueOf(true);
        });
  }
  
  public boolean canPerform() {
    List<EntityPlayer> list = ((EntityBossMob)getEntity()).field_70170_p.func_82733_a(EntityPlayer.class, ((EntityBossMob)getEntity()).field_70121_D.func_72314_b(this.maxDist, 4.0D, this.maxDist), (IEntitySelector)this.selector);
    if (list.isEmpty())
      return false; 
    this.entities.clear();
    this.entities.addAll(list);
    return true;
  }
  
  public void onAnimationKeyframe(CustomInstructionKeyframeEvent event) {}
  
  public void perform() {}
  
  public void onEnd() {
    for (EntityPlayer entity : this.entities)
      super.damage((Entity)entity); 
    this.entities.clear();
  }
  
  public void damage(Entity target) {
    double dist = target.func_70032_d((Entity)getEntity());
    if (dist < this.minDist || dist > this.maxDist)
      return; 
    super.damage(target);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\attack\AttackZone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */