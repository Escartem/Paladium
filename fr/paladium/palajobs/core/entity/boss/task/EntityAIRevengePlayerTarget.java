package fr.paladium.palajobs.core.entity.boss.task;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITarget;

public class EntityAIRevengePlayerTarget extends EntityAITarget {
  private int revengeTimer;
  
  public EntityAIRevengePlayerTarget(EntityCreature entity) {
    super(entity, true, false);
    func_75248_a(1);
  }
  
  public boolean func_75250_a() {
    int i = this.field_75299_d.func_142015_aE();
    return (i != this.revengeTimer && func_75296_a(this.field_75299_d.func_70643_av(), false));
  }
  
  public void func_75249_e() {
    this.field_75299_d.func_70624_b(this.field_75299_d.func_70643_av());
    this.revengeTimer = this.field_75299_d.func_142015_aE();
    super.func_75249_e();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\task\EntityAIRevengePlayerTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */