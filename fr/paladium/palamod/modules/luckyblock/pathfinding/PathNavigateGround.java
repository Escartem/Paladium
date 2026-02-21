package fr.paladium.palamod.modules.luckyblock.pathfinding;

import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PathNavigateGround extends PathNavigatePala {
  private boolean noSunPathfind;
  
  public PathNavigateGround(EntityLiving entitylivingIn, World worldIn) {
    super(entitylivingIn, worldIn);
  }
  
  protected PathFinderMoC getPathFinder() {
    this.nodeProcessor = new WalkNodeProcessor();
    this.nodeProcessor.setEnterDoors(true);
    return new PathFinderMoC(this.nodeProcessor);
  }
  
  protected boolean func_75485_k() {
    return (this.field_75515_a.field_70122_E || (getCanSwim() && func_75506_l()) || (this.field_75515_a
      .func_70115_ae() && this.field_75515_a instanceof net.minecraft.entity.monster.EntityZombie && this.field_75515_a.field_70154_o instanceof net.minecraft.entity.passive.EntityChicken));
  }
  
  protected Vec3 func_75502_i() {
    return Vec3.func_72443_a(this.field_75515_a.field_70165_t, getPathableYPos(), this.field_75515_a.field_70161_v);
  }
  
  private int getPathableYPos() {
    if (this.field_75515_a.func_70090_H() && getCanSwim()) {
      int i = (int)this.field_75515_a.field_70121_D.field_72338_b;
      Block block = this.field_75513_b.func_147439_a(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, 
          MathHelper.func_76128_c(this.field_75515_a.field_70161_v));
      int j = 0;
      do {
        if (block != Blocks.field_150358_i && block != Blocks.field_150355_j)
          return i; 
        i++;
        block = this.field_75513_b.func_147439_a(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, 
            MathHelper.func_76128_c(this.field_75515_a.field_70161_v));
        ++j;
      } while (j <= 16);
      return (int)this.field_75515_a.field_70121_D.field_72338_b;
    } 
    return (int)(this.field_75515_a.field_70121_D.field_72338_b + 0.5D);
  }
  
  protected void func_75487_m() {
    super.func_75487_m();
    if (this.noSunPathfind) {
      if (this.field_75513_b.func_72937_j(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), (int)(this.field_75515_a.field_70121_D.field_72338_b + 0.5D), 
          
          MathHelper.func_76128_c(this.field_75515_a.field_70161_v)))
        return; 
      for (int i = 0; i < this.field_75514_c.func_75874_d(); i++) {
        PathPointEx pathpoint = (PathPointEx)this.field_75514_c.func_75877_a(i);
        if (this.field_75513_b.func_72937_j(pathpoint.field_75839_a, pathpoint.field_75837_b, pathpoint.field_75838_c)) {
          this.field_75514_c.func_75871_b(i - 1);
          return;
        } 
      } 
    } 
  }
  
  protected boolean func_75493_a(Vec3 p_75493_1_, Vec3 p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_) {
    int l = MathHelper.func_76128_c(p_75493_1_.field_72450_a);
    int i1 = MathHelper.func_76128_c(p_75493_1_.field_72449_c);
    double d0 = p_75493_2_.field_72450_a - p_75493_1_.field_72450_a;
    double d1 = p_75493_2_.field_72449_c - p_75493_1_.field_72449_c;
    double d2 = d0 * d0 + d1 * d1;
    if (d2 < 1.0E-8D)
      return false; 
    double d3 = 1.0D / Math.sqrt(d2);
    d0 *= d3;
    d1 *= d3;
    p_75493_3_ += 2;
    p_75493_5_ += 2;
    if (!isSafeToStandAt(l, (int)p_75493_1_.field_72448_b, i1, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, d0, d1))
      return false; 
    p_75493_3_ -= 2;
    p_75493_5_ -= 2;
    double d4 = 1.0D / Math.abs(d0);
    double d5 = 1.0D / Math.abs(d1);
    double d6 = (l * 1) - p_75493_1_.field_72450_a;
    double d7 = (i1 * 1) - p_75493_1_.field_72449_c;
    if (d0 >= 0.0D)
      d6++; 
    if (d1 >= 0.0D)
      d7++; 
    d6 /= d0;
    d7 /= d1;
    int j1 = (d0 < 0.0D) ? -1 : 1;
    int k1 = (d1 < 0.0D) ? -1 : 1;
    int l1 = MathHelper.func_76128_c(p_75493_2_.field_72450_a);
    int i2 = MathHelper.func_76128_c(p_75493_2_.field_72449_c);
    int j2 = l1 - l;
    int k2 = i2 - i1;
    do {
      if (j2 * j1 <= 0 && k2 * k1 <= 0)
        return true; 
      if (d6 < d7) {
        d6 += d4;
        l += j1;
        j2 = l1 - l;
      } else {
        d7 += d5;
        i1 += k1;
        k2 = i2 - i1;
      } 
    } while (isSafeToStandAt(l, (int)p_75493_1_.field_72448_b, i1, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, d0, d1));
    return false;
  }
  
  private boolean isSafeToStandAt(int p_179683_1_, int p_179683_2_, int p_179683_3_, int p_179683_4_, int p_179683_5_, int p_179683_6_, Vec3 p_179683_7_, double p_179683_8_, double p_179683_10_) {
    int k1 = p_179683_1_ - p_179683_4_ / 2;
    int l1 = p_179683_3_ - p_179683_6_ / 2;
    if (!isPositionClear(k1, p_179683_2_, l1, p_179683_4_, p_179683_5_, p_179683_6_, p_179683_7_, p_179683_8_, p_179683_10_))
      return false; 
    for (int i2 = k1; i2 < k1 + p_179683_4_; i2++) {
      for (int j2 = l1; j2 < l1 + p_179683_6_; j2++) {
        double d2 = i2 + 0.5D - p_179683_7_.field_72450_a;
        double d3 = j2 + 0.5D - p_179683_7_.field_72449_c;
        if (d2 * p_179683_8_ + d3 * p_179683_10_ >= 0.0D) {
          Block block = this.field_75513_b.func_147439_a(i2, p_179683_2_ - 1, j2);
          Material material = block.func_149688_o();
          if (material == Material.field_151579_a)
            return false; 
          if (material == Material.field_151586_h && !this.field_75515_a.func_70090_H())
            return false; 
          if (material == Material.field_151587_i)
            return false; 
        } 
      } 
    } 
    return true;
  }
  
  private boolean isPositionClear(int p_179692_1_, int p_179692_2_, int p_179692_3_, int p_179692_4_, int p_179692_5_, int p_179692_6_, Vec3 p_179692_7_, double p_179692_8_, double p_179692_10_) {
    Iterator<?> iterator = BlockPos.getAllInBox(new BlockPos(p_179692_1_, p_179692_2_, p_179692_3_), new BlockPos(p_179692_1_ + p_179692_4_ - 1, p_179692_2_ + p_179692_5_ - 1, p_179692_3_ + p_179692_6_ - 1)).iterator();
    while (iterator.hasNext()) {
      BlockPos blockpos = (BlockPos)iterator.next();
      double d2 = blockpos.getX() + 0.5D - p_179692_7_.field_72450_a;
      double d3 = blockpos.getZ() + 0.5D - p_179692_7_.field_72449_c;
      if (d2 * p_179692_8_ + d3 * p_179692_10_ >= 0.0D) {
        Block block = this.field_75513_b.func_147439_a(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        if (!block.func_149655_b((IBlockAccess)this.field_75513_b, blockpos.getX(), blockpos.getY(), blockpos
            .getZ()))
          return false; 
      } 
    } 
    return true;
  }
  
  public void func_75504_d(boolean p_179685_1_) {
    super.func_75504_d(p_179685_1_);
    this.noSunPathfind = p_179685_1_;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\PathNavigateGround.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */