package fr.paladium.palamod.modules.back2future.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityJumpHelper;

public class RabbitJumpHelper extends EntityJumpHelper {
  private EntityRabbit theEntity;
  
  private boolean field_180068_d = false;
  
  public RabbitJumpHelper(EntityRabbit rabbit) {
    super((EntityLiving)rabbit);
    this.theEntity = rabbit;
  }
  
  public boolean getIsJumping() {
    return EntityRabbit.access$000(EntityRabbit.this);
  }
  
  public boolean func_180065_d() {
    return this.field_180068_d;
  }
  
  public void func_180066_a(boolean p_180066_1_) {
    this.field_180068_d = p_180066_1_;
  }
  
  public void func_75661_b() {
    if (EntityRabbit.access$100(EntityRabbit.this)) {
      this.theEntity.doMovementAction(EntityRabbit.EnumMoveType.STEP);
      EntityRabbit.access$202(EntityRabbit.this, false);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityRabbit$RabbitJumpHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */