package fr.paladium.palamod.modules.chisel.block.impl;

import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;

public class BlockChiselStairs extends BlockStairs {
  public BlockChiselStairs(Block block, int meta) {
    super(block, meta);
    func_149647_a((CreativeTabs)Registry.CHISEL_TAB);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\block\impl\BlockChiselStairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */