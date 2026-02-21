package fr.paladium.palajobs.core.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palajobs.core.block.BlockBramble;
import fr.paladium.palajobs.core.block.BlockColoredGrass;
import fr.paladium.palajobs.core.block.BlockColoredGrassSeeds;
import fr.paladium.palajobs.core.block.BlockColoredTallGrass;
import fr.paladium.palajobs.core.item.ItemBlockColoredGrass;
import fr.paladium.palajobs.core.item.ItemBlockColoredGrassSeeds;
import fr.paladium.palajobs.core.item.ItemBlockColoredTallGrass;
import fr.paladium.palajobs.core.tileentity.TileEntityBramble;
import net.minecraft.block.Block;

public class BlocksRegistry {
  public static Block COLORED_GRASS_SEEDS;
  
  public static BlockColoredGrass COLORED_GRASS;
  
  public static BlockColoredTallGrass COLORED_TALL_GRASS;
  
  public static BlockBramble BRAMBLE;
  
  private static void registerBlock(Block block) {
    GameRegistry.registerBlock(block, block.func_149739_a());
  }
  
  public static void registerBlocks() {
    COLORED_GRASS_SEEDS = (Block)new BlockColoredGrassSeeds("colored_grass_seeds");
    GameRegistry.registerBlock(COLORED_GRASS_SEEDS, ItemBlockColoredGrassSeeds.class, "colored_grass_seeds");
    COLORED_GRASS = new BlockColoredGrass("colored_grass");
    GameRegistry.registerBlock((Block)COLORED_GRASS, ItemBlockColoredGrass.class, "colored_grass");
    COLORED_TALL_GRASS = new BlockColoredTallGrass();
    GameRegistry.registerBlock((Block)COLORED_TALL_GRASS, ItemBlockColoredTallGrass.class, "colored_tall_grass");
    registerBlock((Block)(BRAMBLE = new BlockBramble()));
    GameRegistry.registerTileEntity(TileEntityBramble.class, "tileEntityBramble");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\registry\BlocksRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */