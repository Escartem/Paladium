package fr.paladium.palamod.modules.back2future.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;

public abstract class EntityAICustomDoorInteract extends EntityAIBase {
  protected EntityLiving theEntity;
  
  protected int entityPosX;
  
  protected int entityPosY;
  
  protected int entityPosZ;
  
  protected BlockDoor field_151504_e;
  
  boolean hasStoppedDoorInteraction;
  
  float entityPositionX;
  
  float entityPositionZ;
  
  public EntityAICustomDoorInteract(EntityLiving p_i1621_1_) {
    this.theEntity = p_i1621_1_;
  }
  
  public boolean func_75250_a() {
    if (!this.theEntity.field_70123_F)
      return false; 
    PathNavigate pathnavigate = this.theEntity.func_70661_as();
    PathEntity pathentity = pathnavigate.func_75505_d();
    if (pathentity != null && !pathentity.func_75879_b() && pathnavigate.func_75507_c()) {
      for (int i = 0; i < Math.min(pathentity.func_75873_e() + 2, pathentity
          .func_75874_d()); i++) {
        PathPoint pathpoint = pathentity.func_75877_a(i);
        this.entityPosX = pathpoint.field_75839_a;
        this.entityPosY = pathpoint.field_75837_b + 1;
        this.entityPosZ = pathpoint.field_75838_c;
        if (this.theEntity.func_70092_e(this.entityPosX, this.theEntity.field_70163_u, this.entityPosZ) <= 2.25D) {
          this.field_151504_e = func_151503_a(this.entityPosX, this.entityPosY, this.entityPosZ);
          if (this.field_151504_e != null)
            return true; 
        } 
      } 
      this.entityPosX = MathHelper.func_76128_c(this.theEntity.field_70165_t);
      this.entityPosY = MathHelper.func_76128_c(this.theEntity.field_70163_u + 1.0D);
      this.entityPosZ = MathHelper.func_76128_c(this.theEntity.field_70161_v);
      this.field_151504_e = func_151503_a(this.entityPosX, this.entityPosY, this.entityPosZ);
      return (this.field_151504_e != null);
    } 
    return false;
  }
  
  public boolean func_75253_b() {
    return !this.hasStoppedDoorInteraction;
  }
  
  public void func_75249_e() {
    this.hasStoppedDoorInteraction = false;
    this.entityPositionX = (float)((this.entityPosX + 0.5F) - this.theEntity.field_70165_t);
    this.entityPositionZ = (float)((this.entityPosZ + 0.5F) - this.theEntity.field_70161_v);
  }
  
  public void func_75246_d() {
    float f = (float)((this.entityPosX + 0.5F) - this.theEntity.field_70165_t);
    float f1 = (float)((this.entityPosZ + 0.5F) - this.theEntity.field_70161_v);
    float f2 = this.entityPositionX * f + this.entityPositionZ * f1;
    if (f2 < 0.0F)
      this.hasStoppedDoorInteraction = true; 
  }
  
  private BlockDoor func_151503_a(int x, int y, int z) {
    Block block = this.theEntity.field_70170_p.func_147439_a(x, y, z);
    return (block instanceof BlockDoor) ? (BlockDoor)block : null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ai\EntityAICustomDoorInteract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */