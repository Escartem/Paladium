package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class WalkNodeProcessor extends NodeProcessor {
  private boolean field_176182_j;
  
  public void init(IBlockAccess access, Entity mob) {
    this.isWoddenDoorAllowed = true;
    this.isMovementBlockAllowed = false;
    this.canEntityDrown = true;
    this.isPathingInWater = true;
    super.init(access, mob);
    this.field_176182_j = this.isPathingInWater;
  }
  
  public void postProcess() {
    super.postProcess();
    this.isPathingInWater = this.field_176182_j;
  }
  
  public PathPointEx getStart() {
    int i;
    if (this.canEntityDrown && this.entity.func_70090_H()) {
      i = (int)this.entity.field_70121_D.field_72338_b;
      Block block = this.blockaccess.func_147439_a(MathHelper.func_76128_c(this.entity.field_70165_t), i, 
          MathHelper.func_76128_c(this.entity.field_70161_v));
      for (; block == Blocks.field_150358_i || block == Blocks.field_150355_j; block = this.blockaccess.func_147439_a(
          MathHelper.func_76128_c(this.entity.field_70165_t), i, MathHelper.func_76128_c(this.entity.field_70161_v)))
        i++; 
      this.isPathingInWater = false;
    } else {
      i = MathHelper.func_76128_c(this.entity.field_70121_D.field_72338_b + 0.5D);
    } 
    return openPoint(MathHelper.func_76128_c(this.entity.field_70121_D.field_72340_a), i, 
        MathHelper.func_76128_c(this.entity.field_70121_D.field_72339_c));
  }
  
  public PathPointEx getPathPointToCoords(double x, double y, double z) {
    return openPoint(MathHelper.func_76128_c(x - (this.entity.field_70130_N / 2.0F)), 
        MathHelper.func_76128_c(y), MathHelper.func_76128_c(z - (this.entity.field_70130_N / 2.0F)));
  }
  
  public int findPathOptions(PathPointEx[] pathOptions, PathPointEx currentPoint, PathPointEx targetPoint, float maxDistance) {
    int i = 0;
    byte b0 = 0;
    if (getVerticalOffset(this.entity, currentPoint.field_75839_a, currentPoint.field_75837_b + 1, currentPoint.field_75838_c) == 1)
      b0 = 1; 
    PathPointEx pathpoint2 = getSafePoint(this.entity, currentPoint.field_75839_a, currentPoint.field_75837_b, currentPoint.field_75838_c + 1, b0);
    PathPointEx pathpoint3 = getSafePoint(this.entity, currentPoint.field_75839_a - 1, currentPoint.field_75837_b, currentPoint.field_75838_c, b0);
    PathPointEx pathpoint4 = getSafePoint(this.entity, currentPoint.field_75839_a + 1, currentPoint.field_75837_b, currentPoint.field_75838_c, b0);
    PathPointEx pathpoint5 = getSafePoint(this.entity, currentPoint.field_75839_a, currentPoint.field_75837_b, currentPoint.field_75838_c - 1, b0);
    if (pathpoint2 != null && !pathpoint2.visited && pathpoint2
      .func_75829_a(targetPoint) < maxDistance)
      pathOptions[i++] = pathpoint2; 
    if (pathpoint3 != null && !pathpoint3.visited && pathpoint3
      .func_75829_a(targetPoint) < maxDistance)
      pathOptions[i++] = pathpoint3; 
    if (pathpoint4 != null && !pathpoint4.visited && pathpoint4
      .func_75829_a(targetPoint) < maxDistance)
      pathOptions[i++] = pathpoint4; 
    if (pathpoint5 != null && !pathpoint5.visited && pathpoint5
      .func_75829_a(targetPoint) < maxDistance)
      pathOptions[i++] = pathpoint5; 
    return i;
  }
  
  private PathPointEx getSafePoint(Entity mob, int x, int y, int z, int p_176171_5_) {
    PathPointEx pathpoint = null;
    int i1 = getVerticalOffset(mob, x, y, z);
    if (i1 == 2)
      return openPoint(x, y, z); 
    if (i1 == 1)
      pathpoint = openPoint(x, y, z); 
    if (pathpoint == null && p_176171_5_ > 0 && i1 != -3 && i1 != -4 && 
      getVerticalOffset(mob, x, y + p_176171_5_, z) == 1) {
      pathpoint = openPoint(x, y + p_176171_5_, z);
      y += p_176171_5_;
    } 
    if (pathpoint != null) {
      int j1 = 0;
      int k1;
      for (k1 = 0; y > 0; pathpoint = openPoint(x, y, z)) {
        k1 = getVerticalOffset(mob, x, y - 1, z);
        if (this.isPathingInWater && k1 == -1)
          return null; 
        if (k1 != 1)
          break; 
        if (j1++ >= mob.func_82143_as())
          return null; 
        y--;
        if (y <= 0)
          return null; 
      } 
      if (k1 == -2)
        return null; 
    } 
    return pathpoint;
  }
  
  private int getVerticalOffset(Entity mob, int x, int y, int z) {
    return canEntityStandAt(this.blockaccess, mob, x, y, z, this.entitySizeX, this.entitySizeY, this.entitySizeZ, this.isPathingInWater, this.isMovementBlockAllowed, this.isWoddenDoorAllowed);
  }
  
  public static int canEntityStandAt(IBlockAccess access, Entity mob, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ, boolean PathingInWater, boolean MovementBlockAllowed, boolean WoddenDoorAllowed) {
    boolean flag3 = false;
    BlockPos blockpos = new BlockPos(mob);
    for (int k1 = posX; k1 < posX + sizeX; k1++) {
      for (int l1 = posY; l1 < posY + sizeY; l1++) {
        for (int i2 = posZ; i2 < posZ + sizeZ; i2++) {
          BlockPos blockpos1 = new BlockPos(k1, l1, i2);
          Block block = access.func_147439_a(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
          if (block.func_149688_o() != Material.field_151579_a) {
            if (block != Blocks.field_150415_aT) {
              if (block != Blocks.field_150358_i && block != Blocks.field_150355_j) {
                if (!WoddenDoorAllowed && block instanceof net.minecraft.block.BlockDoor && block
                  .func_149688_o() == Material.field_151575_d)
                  return 0; 
              } else {
                if (PathingInWater)
                  return -1; 
                flag3 = true;
              } 
            } else {
              flag3 = true;
            } 
            if (mob.field_70170_p.func_147439_a(blockpos1.getX(), blockpos1.getY(), blockpos1
                .getZ()) instanceof net.minecraft.block.BlockRailBase) {
              if (!(mob.field_70170_p.func_147439_a(blockpos.getX(), blockpos.getY(), blockpos
                  .getZ()) instanceof net.minecraft.block.BlockRailBase) && 
                !(mob.field_70170_p.func_147439_a(blockpos.down().getX(), blockpos.down().getY(), blockpos
                  .down().getZ()) instanceof net.minecraft.block.BlockRailBase))
                return -3; 
            } else if (!block.func_149655_b((IBlockAccess)mob.field_70170_p, k1, l1, i2) && (!MovementBlockAllowed || !(block instanceof net.minecraft.block.BlockDoor) || block
              .func_149688_o() != Material.field_151575_d)) {
              if (block instanceof net.minecraft.block.BlockFence || block instanceof net.minecraft.block.BlockFenceGate || block instanceof net.minecraft.block.BlockWall)
                return -3; 
              if (block == Blocks.field_150415_aT)
                return -4; 
              Material material = block.func_149688_o();
              if (material != Material.field_151587_i)
                return 0; 
              if (!mob.func_70058_J())
                return -2; 
            } 
          } 
        } 
      } 
    } 
    return flag3 ? 2 : 1;
  }
  
  public int getPathNodeType(IBlockAccess blockaccessIn, int x, int y, int z, EntityLiving entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
    return canEntityStandAt(blockaccessIn, (Entity)entitylivingIn, x, y, z, xSize, ySize, zSize, this.canEntityDrown, canBreakDoorsIn, canEnterDoorsIn);
  }
  
  public int getPathNodeType(IBlockAccess blockaccessIn, int x, int y, int z) {
    int l = getPathNodeType(blockaccessIn, x, y, z, (EntityLiving)this.entity, this.entitySizeX, this.entitySizeY, this.entitySizeZ, this.isMovementBlockAllowed, this.isWoddenDoorAllowed);
    return (l == 1 || l == 2) ? 0 : 1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\WalkNodeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */