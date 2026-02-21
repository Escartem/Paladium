package fr.paladium.palamod.modules.back2future.entities.ai;

import net.minecraft.entity.EntityLiving;

public class EntityAIOpenCustomDoor extends EntityAICustomDoorInteract {
  boolean field_75361_i;
  
  int field_75360_j;
  
  public EntityAIOpenCustomDoor(EntityLiving p_i1644_1_, boolean p_i1644_2_) {
    super(p_i1644_1_);
    this.theEntity = p_i1644_1_;
    this.field_75361_i = p_i1644_2_;
  }
  
  public boolean func_75253_b() {
    return (this.field_75361_i && this.field_75360_j > 0 && super.func_75253_b());
  }
  
  public void func_75249_e() {
    this.field_75360_j = 20;
    this.field_151504_e.func_150014_a(this.theEntity.field_70170_p, this.entityPosX, this.entityPosY, this.entityPosZ, true);
  }
  
  public void func_75251_c() {
    if (this.field_75361_i)
      this.field_151504_e.func_150014_a(this.theEntity.field_70170_p, this.entityPosX, this.entityPosY, this.entityPosZ, false); 
  }
  
  public void func_75246_d() {
    this.field_75360_j--;
    super.func_75246_d();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ai\EntityAIOpenCustomDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */