package fr.paladium.palamod.modules.back2future.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;

class RabbitMoveHelper extends EntityMoveHelper {
  private EntityRabbit theEntity = EntityRabbit.this;
  
  private double posX;
  
  private double posY;
  
  private double posZ;
  
  public RabbitMoveHelper() {
    super((EntityLiving)EntityRabbit.this);
  }
  
  public void func_75642_a(double p_75642_1_, double p_75642_3_, double p_75642_5_, double p_75642_7_) {
    super.func_75642_a(p_75642_1_, p_75642_3_, p_75642_5_, p_75642_7_);
    this.posX = p_75642_1_;
    this.posY = p_75642_3_;
    this.posZ = p_75642_5_;
  }
  
  public double getX() {
    return this.posX;
  }
  
  public double getY() {
    return this.posY;
  }
  
  public double getZ() {
    return this.posZ;
  }
  
  public void func_75641_c() {
    if (this.theEntity.field_70122_E && !EntityRabbit.access$300(EntityRabbit.this))
      this.theEntity.setMovementSpeed(0.0D); 
    super.func_75641_c();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityRabbit$RabbitMoveHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */