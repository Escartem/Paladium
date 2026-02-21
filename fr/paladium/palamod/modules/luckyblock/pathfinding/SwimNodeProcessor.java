package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class SwimNodeProcessor extends NodeProcessor {
  public void init(IBlockAccess accessIn, Entity entityIn) {
    this.isWoddenDoorAllowed = true;
    this.isMovementBlockAllowed = false;
    this.isPathingInWater = false;
    this.canEntityDrown = false;
    super.init(accessIn, entityIn);
  }
  
  public void postProcess() {
    super.postProcess();
  }
  
  public PathPointEx getStart() {
    return openPoint(MathHelper.func_76128_c(this.entity.field_70121_D.field_72340_a), 
        MathHelper.func_76128_c(this.entity.field_70121_D.field_72338_b + 0.5D), 
        MathHelper.func_76128_c(this.entity.field_70121_D.field_72339_c));
  }
  
  public PathPointEx getPathPointToCoords(double x, double y, double z) {
    return openPoint(MathHelper.func_76128_c(x - (this.entity.field_70130_N / 2.0F)), 
        MathHelper.func_76128_c(y + 0.5D), 
        MathHelper.func_76128_c(z - (this.entity.field_70130_N / 2.0F)));
  }
  
  public int findPathOptions(PathPointEx[] pathOptions, PathPointEx startpoint, PathPointEx endpoint, float p_176164_5_) {
    int i = 0;
    EnumFacing[] aenumfacing = EnumFacing.values();
    int j = aenumfacing.length;
    for (int k = 0; k < j; k++) {
      EnumFacing enumfacing = aenumfacing[k];
      PathPointEx pathpoint2 = getWaterNode(this.entity, startpoint.field_75839_a + enumfacing.func_82601_c(), startpoint.field_75837_b + enumfacing
          .func_96559_d(), startpoint.field_75838_c + enumfacing
          .func_82599_e());
      if (pathpoint2 != null && !pathpoint2.visited && pathpoint2
        .func_75829_a(endpoint) < p_176164_5_)
        pathOptions[i++] = pathpoint2; 
    } 
    return i;
  }
  
  private PathPointEx getWaterNode(Entity entityIn, int x, int y, int z) {
    int l = getPathNodeType(entityIn, x, y, z);
    return (l == -1) ? openPoint(x, y, z) : null;
  }
  
  private int getPathNodeType(Entity entityIn, int x, int y, int z) {
    boolean isLavaCreature = false;
    for (int l = x; l < x + this.entitySizeX; l++) {
      for (int i1 = y; i1 < y + this.entitySizeY; i1++) {
        for (int j1 = z; j1 < z + this.entitySizeZ; j1++) {
          BlockPos blockpos = new BlockPos(l, i1, j1);
          Block block = this.blockaccess.func_147439_a(blockpos.getX(), blockpos.getY(), blockpos.getZ());
          if ((isLavaCreature || block.func_149688_o() != Material.field_151586_h) && (!isLavaCreature || block
            .func_149688_o() != Material.field_151587_i))
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\SwimNodeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */