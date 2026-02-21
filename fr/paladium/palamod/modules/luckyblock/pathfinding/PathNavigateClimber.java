package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PathNavigateClimber extends PathNavigateGround {
  private BlockPos targetPosition;
  
  public PathNavigateClimber(EntityLiving entityIn, World worldIn) {
    super(entityIn, worldIn);
  }
  
  public PathEntity getPathToPos(BlockPos pos) {
    this.targetPosition = pos;
    return super.getPathToPos(pos);
  }
  
  public PathEntity func_75494_a(Entity entityIn) {
    this.targetPosition = new BlockPos(entityIn);
    return super.func_75494_a(entityIn);
  }
  
  public boolean func_75497_a(Entity entityIn, double speedIn) {
    PathEntity pathentity = func_75494_a(entityIn);
    if (pathentity != null)
      return func_75484_a(pathentity, speedIn); 
    this.targetPosition = new BlockPos(entityIn);
    this.field_75511_d = speedIn;
    return true;
  }
  
  public void func_75501_e() {
    if (!func_75500_f()) {
      super.func_75501_e();
    } else {
      UpdateNavigationstate();
      if (getNavigateState())
        this.navigation.updateFlight(); 
      if (this.targetPosition != null) {
        double d0 = (this.field_75515_a.field_70130_N * this.field_75515_a.field_70130_N);
        if (this.field_75515_a.func_70092_e(this.targetPosition.getX() - 0.5D, this.targetPosition
            .getY() - 0.5D, this.targetPosition.getZ() - 0.5D) >= d0 && (this.field_75515_a.field_70163_u <= this.targetPosition
          .getY() || this.field_75515_a
          .func_70092_e(this.targetPosition.getX() - 0.5D, 
            MathHelper.func_76128_c(this.field_75515_a.field_70163_u) - 0.5D, this.targetPosition
            .getZ() - 0.5D) >= d0)) {
          this.field_75515_a.func_70605_aq().func_75642_a(this.targetPosition.getX(), this.targetPosition
              .getY(), this.targetPosition.getZ(), this.field_75511_d);
        } else {
          this.targetPosition = null;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\PathNavigateClimber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */