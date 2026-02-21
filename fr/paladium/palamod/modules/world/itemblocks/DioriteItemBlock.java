package fr.paladium.palamod.modules.world.itemblocks;

import fr.paladium.palamod.common.blocks.abstracts.MultiItemBlock;
import net.minecraft.block.Block;

public class DioriteItemBlock extends MultiItemBlock {
  private static final String[] blockTypes = new String[] { "diorite", "diorite_smooth" };
  
  public DioriteItemBlock(Block b) {
    super(b, "diorite", blockTypes);
    func_77656_e(0);
    func_77627_a(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\itemblocks\DioriteItemBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */