package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class FlyingNodeProcessor extends NodeProcessor {
  private boolean field_176182_j;
  
  public void init(IBlockAccess iblockaccessIn, Entity entityIn) {
    this.isWoddenDoorAllowed = true;
    this.isMovementBlockAllowed = false;
    this.canEntityDrown = true;
    this.isPathingInWater = true;
    super.init(iblockaccessIn, entityIn);
    this.field_176182_j = this.isPathingInWater;
  }
  
  public void postProcess() {
    super.postProcess();
    this.isPathingInWater = this.field_176182_j;
  }
  
  public PathPointEx getStart() {
    return openPoint(MathHelper.func_76128_c(this.entity.field_70121_D.field_72340_a), 
        MathHelper.func_76128_c(this.entity.field_70121_D.field_72338_b + 0.5D), 
        MathHelper.func_76128_c(this.entity.field_70121_D.field_72339_c));
  }
  
  public PathPointEx getPathPointToCoords(double x, double y, double target) {
    return openPoint(MathHelper.func_76128_c(x - (this.entity.field_70130_N / 2.0F)), 
        MathHelper.func_76128_c(y + 0.5D), MathHelper.func_76128_c(target - (this.entity.field_70130_N / 2.0F)));
  }
  
  public int findPathOptions(PathPointEx[] pathOptions, PathPointEx currentPoint, PathPointEx targetPoint, float maxDistance) {
    int i = 0;
    EnumFacing[] aenumfacing = EnumFacing.values();
    int j = aenumfacing.length;
    for (int k = 0; k < j; k++) {
      EnumFacing enumfacing = aenumfacing[k];
      PathPointEx pathpoint2 = getSafePoint(this.entity, currentPoint.field_75839_a + enumfacing.func_82601_c(), currentPoint.field_75837_b + enumfacing
          .func_96559_d(), currentPoint.field_75838_c + enumfacing
          .func_82599_e());
      if (pathpoint2 != null && !pathpoint2.visited && pathpoint2
        .func_75829_a(targetPoint) < maxDistance)
        pathOptions[i++] = pathpoint2; 
    } 
    return i;
  }
  
  private PathPointEx getSafePoint(Entity entityIn, int x, int y, int z) {
    int l = getPathNodeType(entityIn, x, y, z);
    return (l == -1) ? openPoint(x, y, z) : null;
  }
  
  private int getPathNodeType(Entity entityIn, int x, int y, int z) {
    for (int l = x; l < x + this.entitySizeX; l++) {
      for (int i1 = y; i1 < y + this.entitySizeY; i1++) {
        for (int j1 = z; j1 < z + this.entitySizeZ; j1++) {
          Block block = this.blockaccess.func_147439_a(l, i1, j1);
          if (block.func_149688_o() != Material.field_151579_a)
            return 0; 
        } 
      } 
    } 
    return -1;
  }
  
  public int getPathNodeType(IBlockAccess blockaccessIn, int x, int y, int z, EntityLiving entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
    return getPathNodeType((Entity)entitylivingIn, xSize, ySize, zSize);
  }
  
  public int getPathNodeType(IBlockAccess blockaccessIn, int x, int y, int z) {
    int l = getPathNodeType(this.entity, x, y, z);
    return (l == -1) ? 0 : 1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\FlyingNodeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */