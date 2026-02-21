package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockTank;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.paladium.common.blocks.fluids.BlockSulfuricWater;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemTankPistol extends Item {
  public ItemTankPistol(String string) {
    func_77655_b(string);
    func_111206_d("palamod:" + string);
    func_77625_d(1);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    boolean flag = !p_77659_3_.func_70093_af();
    MovingObjectPosition movingobjectposition = func_77621_a(p_77659_2_, p_77659_3_, flag);
    if (movingobjectposition == null)
      return p_77659_1_; 
    if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      int i = movingobjectposition.field_72311_b;
      int j = movingobjectposition.field_72312_c;
      int k = movingobjectposition.field_72309_d;
      if (!p_77659_2_.func_72962_a(p_77659_3_, i, j, k))
        return p_77659_1_; 
      if (!EventUtils.canInteract(p_77659_3_, i, j, k))
        return p_77659_1_; 
      if (flag) {
        if (!p_77659_3_.func_82247_a(i, j, k, movingobjectposition.field_72310_e, p_77659_1_))
          return p_77659_1_; 
        Material material = p_77659_2_.func_147439_a(i, j, k).func_149688_o();
        int l = p_77659_2_.func_72805_g(i, j, k);
        if (p_77659_2_.func_147439_a(i, j, k) == BlocksRegister.FLUIDS_SULFURICWATER && l == 0 && hasMatchingTank(p_77659_3_, "item.sulfuricwater", true)) {
          p_77659_2_.func_147468_f(i, j, k);
          return updateTank(p_77659_1_, p_77659_3_, "item.sulfuricwater");
        } 
        if (p_77659_2_.func_147439_a(i, j, k) != BlocksRegister.FLUIDS_ANGELICWATER && material == Material.field_151586_h && l == 0 && hasMatchingTank(p_77659_3_, Blocks.field_150355_j.func_149739_a(), true)) {
          p_77659_2_.func_147468_f(i, j, k);
          return updateTank(p_77659_1_, p_77659_3_, Blocks.field_150355_j.func_149739_a());
        } 
        if (material == Material.field_151587_i && l == 0 && hasMatchingTank(p_77659_3_, "item.bucketLava", true)) {
          p_77659_2_.func_147468_f(i, j, k);
          return updateTank(p_77659_1_, p_77659_3_, Blocks.field_150353_l.func_149739_a());
        } 
      } else {
        if (movingobjectposition.field_72310_e == 0)
          j--; 
        if (movingobjectposition.field_72310_e == 1)
          j++; 
        if (movingobjectposition.field_72310_e == 2)
          k--; 
        if (movingobjectposition.field_72310_e == 3)
          k++; 
        if (movingobjectposition.field_72310_e == 4)
          i--; 
        if (movingobjectposition.field_72310_e == 5)
          i++; 
        if (!p_77659_3_.func_82247_a(i, j, k, movingobjectposition.field_72310_e, p_77659_1_))
          return p_77659_1_; 
        if (hasMatchingTank(p_77659_3_, "*", false))
          tryPlaceContainedLiquid(p_77659_3_, p_77659_2_, i, j, k); 
      } 
    } 
    return p_77659_1_;
  }
  
  private boolean hasMatchingTank(EntityPlayer p, String liquid, boolean needToBeNotFull) {
    for (int i = 0; i < p.field_71071_by.field_70462_a.length; i++) {
      ItemStack is = p.field_71071_by.field_70462_a[i];
      if (is != null && is.func_77973_b() != null && Block.func_149634_a(is.func_77973_b()) instanceof BlockTank && is.field_77994_a == 1)
        if (BlockTank.getLiquid(is).equalsIgnoreCase(liquid) || (!BlockTank.getLiquid(is).isEmpty() && liquid.equalsIgnoreCase("*"))) {
          BlockTank blockTank = (BlockTank)Block.func_149634_a(is.func_77973_b());
          if (!needToBeNotFull || BlockTank.getLiquidLevel(is) < blockTank.getTank().getMaxSeve())
            return true; 
        } else if (BlockTank.getLiquid(is).isEmpty()) {
          return true;
        }  
    } 
    return false;
  }
  
  private ItemStack updateTank(ItemStack p_150910_1_, EntityPlayer p_150910_2_, String liquid) {
    for (int i = 0; i < p_150910_2_.field_71071_by.field_70462_a.length; i++) {
      ItemStack is = p_150910_2_.field_71071_by.field_70462_a[i];
      if (is != null && is.func_77973_b() != null && Block.func_149634_a(is.func_77973_b()) instanceof BlockTank && is.field_77994_a == 1) {
        BlockTank blockTank = (BlockTank)Block.func_149634_a(is.func_77973_b());
        if (BlockTank.getLiquid(is).equalsIgnoreCase(liquid)) {
          if (BlockTank.getLiquidLevel(is) < blockTank.getTank().getMaxSeve()) {
            BlockTank.setLiquidLevel(is, BlockTank.getLiquidLevel(is) + 1000);
            if (BlockTank.getLiquidLevel(is) > blockTank.getTank().getMaxSeve())
              BlockTank.setLiquidLevel(is, blockTank.getTank().getMaxSeve()); 
            return p_150910_1_;
          } 
        } else if (BlockTank.getLiquid(is).isEmpty()) {
          BlockTank.setLiquid(is, liquid);
          BlockTank.setLiquidLevel(is, 1000);
          return p_150910_1_;
        } 
      } 
    } 
    return p_150910_1_;
  }
  
  public boolean tryPlaceContainedLiquid(EntityPlayer p, World p_77875_1_, int p_77875_2_, int p_77875_3_, int p_77875_4_) {
    BlockSulfuricWater blockSulfuricWater;
    ItemStack firstTank = null;
    Block fuel = null;
    for (int i = 0; i < p.field_71071_by.field_70462_a.length; i++) {
      ItemStack is = p.field_71071_by.field_70462_a[i];
      if (is != null && is.func_77973_b() != null && Block.func_149634_a(is.func_77973_b()) instanceof BlockTank && is.field_77994_a == 1 && 
        !BlockTank.getLiquid(is).isEmpty() && BlockTank.getLiquidLevel(is) > 0) {
        BlockLiquid blockLiquid;
        firstTank = is;
        switch (BlockTank.getLiquid(firstTank)) {
          case "tile.water":
            blockLiquid = Blocks.field_150358_i;
            break;
          case "item.bucketLava":
            blockLiquid = Blocks.field_150356_k;
            break;
          case "item.sulfuricwater":
            blockSulfuricWater = BlocksRegister.FLUIDS_SULFURICWATER;
            break;
        } 
        if (blockSulfuricWater == null)
          return false; 
        BlockTank.setLiquidLevel(is, BlockTank.getLiquidLevel(is) - 1000);
        if (BlockTank.getLiquidLevel(is) <= 0) {
          BlockTank.setLiquid(is, "");
          BlockTank.setLiquidLevel(is, 0);
        } 
        break;
      } 
    } 
    if (firstTank == null)
      return false; 
    Material material = p_77875_1_.func_147439_a(p_77875_2_, p_77875_3_, p_77875_4_).func_149688_o();
    boolean flag = !material.func_76220_a();
    if (blockSulfuricWater == null)
      return false; 
    if (!p_77875_1_.func_147437_c(p_77875_2_, p_77875_3_, p_77875_4_) && !flag)
      return false; 
    if (p_77875_1_.field_73011_w.field_76575_d && blockSulfuricWater == Blocks.field_150358_i) {
      p_77875_1_.func_72908_a((p_77875_2_ + 0.5F), (p_77875_3_ + 0.5F), (p_77875_4_ + 0.5F), "random.fizz", 0.5F, 2.6F + (p_77875_1_.field_73012_v.nextFloat() - p_77875_1_.field_73012_v.nextFloat()) * 0.8F);
      for (int l = 0; l < 8; l++)
        p_77875_1_.func_72869_a("largesmoke", p_77875_2_ + Math.random(), p_77875_3_ + Math.random(), p_77875_4_ + Math.random(), 0.0D, 0.0D, 0.0D); 
    } else {
      if (!p_77875_1_.field_72995_K && flag && !material.func_76224_d())
        p_77875_1_.func_147480_a(p_77875_2_, p_77875_3_, p_77875_4_, true); 
      p_77875_1_.func_147465_d(p_77875_2_, p_77875_3_, p_77875_4_, (Block)blockSulfuricWater, 0, 3);
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemTankPistol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */