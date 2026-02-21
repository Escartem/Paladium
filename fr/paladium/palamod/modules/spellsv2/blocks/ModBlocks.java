package fr.paladium.palamod.modules.spellsv2.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {
  public static Block inertiumBlock;
  
  public static void init() {
    inertiumBlock = (Block)new BlockInertium();
    GameRegistry.registerBlock(inertiumBlock, "inertium_block");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\blocks\ModBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */