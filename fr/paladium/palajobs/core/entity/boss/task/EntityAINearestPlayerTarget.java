package fr.paladium.palajobs.core.entity.boss.task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAINearestPlayerTarget extends EntityAITarget {
  private final Sorter sorter;
  
  private final IEntitySelector targetSelector;
  
  private EntityLivingBase targetEntity;
  
  public EntityAINearestPlayerTarget(EntityCreature entity) {
    super(entity, true, false);
    this.sorter = new Sorter((Entity)entity);
    func_75248_a(1);
    this.targetSelector = new IEntitySelector() {
        public boolean func_82704_a(Entity entity) {
          return (entity instanceof EntityPlayer && !((EntityPlayer)entity).field_71075_bZ.field_75102_a);
        }
      };
  }
  
  public boolean func_75250_a() {
    double d0 = func_111175_f();
    List<EntityPlayer> list = this.field_75299_d.field_70170_p.func_82733_a(EntityPlayer.class, this.field_75299_d.field_70121_D.func_72314_b(d0, 4.0D, d0), this.targetSelector);
    Collections.sort(list, this.sorter);
    if (list.isEmpty())
      return false; 
    this.targetEntity = (EntityLivingBase)list.get(0);
    return true;
  }
  
  public void func_75249_e() {
    this.field_75299_d.func_70624_b(this.targetEntity);
    super.func_75249_e();
  }
  
  public static class Sorter implements Comparator<Entity> {
    private final Entity theEntity;
    
    public Sorter(Entity entity) {
      this.theEntity = entity;
    }
    
    public int compare(Entity e1, Entity e2) {
      double d0 = this.theEntity.func_70068_e(e1);
      double d1 = this.theEntity.func_70068_e(e2);
      return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\task\EntityAINearestPlayerTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */