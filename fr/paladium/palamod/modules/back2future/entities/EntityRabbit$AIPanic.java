package fr.paladium.palamod.modules.back2future.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIPanic;

class AIPanic extends EntityAIPanic {
  private double speed;
  
  private EntityRabbit theEntity = EntityRabbit.this;
  
  public AIPanic(double speed) {
    super((EntityCreature)EntityRabbit.this, speed);
    this.speed = speed;
  }
  
  public void func_75246_d() {
    super.func_75246_d();
    this.theEntity.setMovementSpeed(this.speed);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityRabbit$AIPanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */