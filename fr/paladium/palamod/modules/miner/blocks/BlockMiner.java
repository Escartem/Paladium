package fr.paladium.palamod.modules.miner.blocks;

import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockMiner extends Block {
  public BlockMiner(String name) {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149711_c(5.0F);
    func_149663_c(name);
    func_149658_d("palamod:miner/" + name);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\blocks\BlockMiner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */