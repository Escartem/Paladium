package fr.paladium.palamod.modules.luckyblock.blocks;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBlazeAndSteelFire extends BlockFire {
  public BlockBlazeAndSteelFire() {
    func_149663_c("fire");
    func_149658_d("minecraft:fire");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public int func_149738_a(World p_149738_1_) {
    return 1;
  }
  
  public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
    boolean flag = p_149674_1_.func_147439_a(p_149674_2_, p_149674_3_ - 1, p_149674_4_).isFireSource(p_149674_1_, p_149674_2_, p_149674_3_ - 1, p_149674_4_, ForgeDirection.UP);
    if (!p_149674_1_.field_72995_K && 
      !WorldGuardUtils.checkFlag(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, DefaultFlag.FIRE_SPREAD, true))
      return; 
    if (!func_149742_c(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_))
      p_149674_1_.func_147468_f(p_149674_2_, p_149674_3_, p_149674_4_); 
    if (!flag && p_149674_1_.func_72896_J() && (p_149674_1_
      .func_72951_B(p_149674_2_, p_149674_3_, p_149674_4_) || p_149674_1_
      .func_72951_B(p_149674_2_ - 1, p_149674_3_, p_149674_4_) || p_149674_1_
      .func_72951_B(p_149674_2_ + 1, p_149674_3_, p_149674_4_) || p_149674_1_
      .func_72951_B(p_149674_2_, p_149674_3_, p_149674_4_ - 1) || p_149674_1_
      .func_72951_B(p_149674_2_, p_149674_3_, p_149674_4_ + 1))) {
      p_149674_1_.func_147468_f(p_149674_2_, p_149674_3_, p_149674_4_);
    } else {
      int l = p_149674_1_.func_72805_g(p_149674_2_, p_149674_3_, p_149674_4_);
      if (l < 15)
        p_149674_1_.func_72921_c(p_149674_2_, p_149674_3_, p_149674_4_, l + p_149674_5_
            .nextInt(3) / 2, 4); 
      p_149674_1_.func_147464_a(p_149674_2_, p_149674_3_, p_149674_4_, (Block)this, 
          func_149738_a(p_149674_1_) + p_149674_5_.nextInt(10));
      if (!flag && !canNeighborBurn(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_)) {
        if (!World.func_147466_a((IBlockAccess)p_149674_1_, p_149674_2_, p_149674_3_ - 1, p_149674_4_) || l > 3)
          p_149674_1_.func_147468_f(p_149674_2_, p_149674_3_, p_149674_4_); 
      } else if (!flag && 
        !canCatchFire((IBlockAccess)p_149674_1_, p_149674_2_, p_149674_3_ - 1, p_149674_4_, ForgeDirection.UP) && l == 15 && p_149674_5_
        .nextInt(4) == 0) {
        p_149674_1_.func_147468_f(p_149674_2_, p_149674_3_, p_149674_4_);
      } else {
        boolean flag1 = p_149674_1_.func_72958_C(p_149674_2_, p_149674_3_, p_149674_4_);
        byte b0 = 0;
        if (flag1)
          b0 = -50; 
        tryCatchFire(p_149674_1_, p_149674_2_ + 1, p_149674_3_, p_149674_4_, 300 + b0, p_149674_5_, l, ForgeDirection.WEST);
        tryCatchFire(p_149674_1_, p_149674_2_ - 1, p_149674_3_, p_149674_4_, 300 + b0, p_149674_5_, l, ForgeDirection.EAST);
        tryCatchFire(p_149674_1_, p_149674_2_, p_149674_3_ - 1, p_149674_4_, 250 + b0, p_149674_5_, l, ForgeDirection.UP);
        tryCatchFire(p_149674_1_, p_149674_2_, p_149674_3_ + 1, p_149674_4_, 250 + b0, p_149674_5_, l, ForgeDirection.DOWN);
        tryCatchFire(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_ - 1, 300 + b0, p_149674_5_, l, ForgeDirection.SOUTH);
        tryCatchFire(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_ + 1, 300 + b0, p_149674_5_, l, ForgeDirection.NORTH);
        for (int i1 = p_149674_2_ - 1; i1 <= p_149674_2_ + 1; i1++) {
          for (int j1 = p_149674_4_ - 1; j1 <= p_149674_4_ + 1; j1++) {
            for (int k1 = p_149674_3_ - 1; k1 <= p_149674_3_ + 4; k1++) {
              if (i1 != p_149674_2_ || k1 != p_149674_3_ || j1 != p_149674_4_) {
                int l1 = 100;
                if (k1 > p_149674_3_ + 1)
                  l1 += (k1 - p_149674_3_ + 1) * 100; 
                int i2 = getChanceOfNeighborsEncouragingFire(p_149674_1_, i1, k1, j1);
                if (i2 > 0) {
                  int j2 = (i2 + 40 + p_149674_1_.field_73013_u.func_151525_a() * 7) / (l + 30);
                  if (flag1)
                    j2 /= 2; 
                  if (j2 > 0 && p_149674_5_.nextInt(l1) <= j2 && (
                    !p_149674_1_.func_72896_J() || !p_149674_1_.func_72951_B(i1, k1, j1)) && 
                    !p_149674_1_.func_72951_B(i1 - 1, k1, p_149674_4_) && 
                    !p_149674_1_.func_72951_B(i1 + 1, k1, j1) && 
                    !p_149674_1_.func_72951_B(i1, k1, j1 - 1) && 
                    !p_149674_1_.func_72951_B(i1, k1, j1 + 1)) {
                    int k2 = l + p_149674_5_.nextInt(5) / 4;
                    if (k2 > 15)
                      k2 = 15; 
                    p_149674_1_.func_147465_d(i1, k1, j1, (Block)this, k2, 3);
                  } 
                } 
              } 
            } 
          } 
        } 
      } 
    } 
  }
  
  private boolean canNeighborBurn(World p_149847_1_, int p_149847_2_, int p_149847_3_, int p_149847_4_) {
    return (canCatchFire((IBlockAccess)p_149847_1_, p_149847_2_ + 1, p_149847_3_, p_149847_4_, ForgeDirection.WEST) || 
      canCatchFire((IBlockAccess)p_149847_1_, p_149847_2_ - 1, p_149847_3_, p_149847_4_, ForgeDirection.EAST) || 
      canCatchFire((IBlockAccess)p_149847_1_, p_149847_2_, p_149847_3_ - 1, p_149847_4_, ForgeDirection.UP) || 
      canCatchFire((IBlockAccess)p_149847_1_, p_149847_2_, p_149847_3_ + 1, p_149847_4_, ForgeDirection.DOWN) || 
      canCatchFire((IBlockAccess)p_149847_1_, p_149847_2_, p_149847_3_, p_149847_4_ - 1, ForgeDirection.SOUTH) || 
      canCatchFire((IBlockAccess)p_149847_1_, p_149847_2_, p_149847_3_, p_149847_4_ + 1, ForgeDirection.NORTH));
  }
  
  private int getChanceOfNeighborsEncouragingFire(World p_149845_1_, int p_149845_2_, int p_149845_3_, int p_149845_4_) {
    byte b0 = 0;
    if (!p_149845_1_.func_147437_c(p_149845_2_, p_149845_3_, p_149845_4_))
      return 0; 
    int l = 0;
    l = getChanceToEncourageFire((IBlockAccess)p_149845_1_, p_149845_2_ + 1, p_149845_3_, p_149845_4_, l, ForgeDirection.WEST);
    l = getChanceToEncourageFire((IBlockAccess)p_149845_1_, p_149845_2_ - 1, p_149845_3_, p_149845_4_, l, ForgeDirection.EAST);
    l = getChanceToEncourageFire((IBlockAccess)p_149845_1_, p_149845_2_, p_149845_3_ - 1, p_149845_4_, l, ForgeDirection.UP);
    l = getChanceToEncourageFire((IBlockAccess)p_149845_1_, p_149845_2_, p_149845_3_ + 1, p_149845_4_, l, ForgeDirection.DOWN);
    l = getChanceToEncourageFire((IBlockAccess)p_149845_1_, p_149845_2_, p_149845_3_, p_149845_4_ - 1, l, ForgeDirection.SOUTH);
    l = getChanceToEncourageFire((IBlockAccess)p_149845_1_, p_149845_2_, p_149845_3_, p_149845_4_ + 1, l, ForgeDirection.NORTH);
    return l;
  }
  
  private void tryCatchFire(World p_149841_1_, int p_149841_2_, int p_149841_3_, int p_149841_4_, int p_149841_5_, Random p_149841_6_, int p_149841_7_, ForgeDirection face) {
    int j1 = p_149841_1_.func_147439_a(p_149841_2_, p_149841_3_, p_149841_4_).getFlammability((IBlockAccess)p_149841_1_, p_149841_2_, p_149841_3_, p_149841_4_, face);
    if (p_149841_6_.nextInt(p_149841_5_) < j1) {
      boolean flag = (p_149841_1_.func_147439_a(p_149841_2_, p_149841_3_, p_149841_4_) == Blocks.field_150335_W);
      if (p_149841_6_.nextInt(p_149841_7_ + 10) < 5 && 
        !p_149841_1_.func_72951_B(p_149841_2_, p_149841_3_, p_149841_4_)) {
        int k1 = p_149841_7_ + p_149841_6_.nextInt(5) / 4;
        if (k1 > 15)
          k1 = 15; 
        p_149841_1_.func_147465_d(p_149841_2_, p_149841_3_, p_149841_4_, (Block)this, k1, 3);
      } else {
        p_149841_1_.func_147468_f(p_149841_2_, p_149841_3_, p_149841_4_);
      } 
      if (flag)
        Blocks.field_150335_W.func_149664_b(p_149841_1_, p_149841_2_, p_149841_3_, p_149841_4_, 1); 
    } 
  }
  
  public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
    super.func_149670_a(p_149670_1_, p_149670_2_, p_149670_3_, p_149670_4_, p_149670_5_);
    p_149670_5_.func_70015_d(2);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockBlazeAndSteelFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */