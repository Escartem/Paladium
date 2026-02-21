package fr.paladium.palamod.modules.miner.blocks;

import fr.paladium.palamod.common.Registry;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockMinerFence extends BlockFence {
  public BlockMinerFence(String name) {
    super("palamod:miner/" + name, Material.field_151575_d);
    func_149663_c(name);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\blocks\BlockMinerFence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */