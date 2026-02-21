package fr.paladium.palamod.modules.ajobs.common.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.ajobs.common.blocks.root.BlockInferiorRoot;
import fr.paladium.palamod.modules.ajobs.common.blocks.root.BlockRootLeaves;
import fr.paladium.palamod.modules.ajobs.common.blocks.root.BlockRootLog;
import fr.paladium.palamod.modules.ajobs.common.blocks.root.BlockRootPlank;
import fr.paladium.palamod.modules.ajobs.common.blocks.root.BlockRootSeeds;
import fr.paladium.palamod.modules.ajobs.common.blocks.thrones.BlockAlchemistThrone;
import fr.paladium.palamod.modules.ajobs.common.blocks.thrones.BlockFarmerThrone;
import fr.paladium.palamod.modules.ajobs.common.blocks.thrones.BlockHunterThrone;
import fr.paladium.palamod.modules.ajobs.common.blocks.thrones.BlockMinerThrone;
import net.minecraft.block.Block;

public class BlocksRegistry {
  public static Block ROOT_LEAVES;
  
  public static Block ROOT_LOG;
  
  public static Block ROOT_PLANK;
  
  public static Block ROOT_SEEDS_BLOCK;
  
  public static Block ROOT_INFERIOR;
  
  public static Block ROOT_LAST;
  
  public static Block ALCHEMIST_THRONE;
  
  public static Block HUNTER_THRONE;
  
  public static Block MINER_THRONE;
  
  public static Block FARMER_THRONE;
  
  private static void registerBlock(Block block) {
    GameRegistry.registerBlock(block, block.func_149739_a());
  }
  
  public static void registerBlocks() {
    registerBlock(ROOT_LEAVES = (Block)new BlockRootLeaves("root_leaves"));
    registerBlock(ROOT_LOG = (Block)new BlockRootLog("root_log"));
    registerBlock(ROOT_PLANK = (Block)new BlockRootPlank("root_planks"));
    registerBlock(ROOT_SEEDS_BLOCK = (Block)new BlockRootSeeds("root_seeds"));
    registerBlock(ROOT_INFERIOR = (Block)new BlockInferiorRoot("root_inferior"));
    registerBlock(ROOT_LAST = (Block)new BlockInferiorRoot("root_last"));
    registerBlock(ALCHEMIST_THRONE = (Block)new BlockAlchemistThrone());
    registerBlock(HUNTER_THRONE = (Block)new BlockHunterThrone());
    registerBlock(MINER_THRONE = (Block)new BlockMinerThrone());
    registerBlock(FARMER_THRONE = (Block)new BlockFarmerThrone());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\BlocksRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */