package fr.paladium.palamod.modules.miner.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.blocks.BlockAutoCrafter;
import fr.paladium.palamod.modules.miner.blocks.BlockBedrockTnt;
import fr.paladium.palamod.modules.miner.blocks.BlockDrawBridge;
import fr.paladium.palamod.modules.miner.blocks.BlockMiner;
import fr.paladium.palamod.modules.miner.blocks.BlockMinerLeaves;
import fr.paladium.palamod.modules.miner.blocks.BlockMinerMultiFaces;
import fr.paladium.palamod.modules.miner.blocks.BlockMinerPlant;
import fr.paladium.palamod.modules.miner.blocks.BlockPaladiumHopper;
import fr.paladium.palamod.modules.miner.blocks.BlockVoidStone;
import fr.paladium.palamod.modules.miner.dimminer.common.block.BlockEndiumCave;
import fr.paladium.palamod.modules.miner.dimminer.common.block.BlockMinerPortal;
import fr.paladium.palamod.modules.miner.dimminer.common.block.BlockWitheredObsidian;
import fr.paladium.palamod.modules.miner.dimminer.common.block.BlockWitheredReinforcedPiston;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityEndiumCaveBlock;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityMinerPortal;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityWitheredObsidian;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityWitheredPiston;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityAutoCrafter;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityDrawBridge;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityPaladiumHopper;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityVoidStone;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Blocks {
  public static void register() {
    BlocksRegister.PALADIUM_HOPPER = (Block)new BlockPaladiumHopper();
    BlocksRegister.DRAWBRIDGE = (Block)new BlockDrawBridge();
    BlocksRegister.BEDROCK_TNT = (Block)new BlockBedrockTnt();
    BlocksRegister.WITHERED_REINFORCED_PISTON = new BlockWitheredReinforcedPiston();
    BlocksRegister.VOID_STONE = (Block)new BlockVoidStone();
    BlocksRegister.AUTOCRAFTER = (Block)new BlockAutoCrafter();
    BlocksRegister.WITHERED_OBSIDIAN = (new BlockWitheredObsidian("withered_obsidian")).func_149711_c(50.0F).func_149752_b(100.0F);
    BlocksRegister.MINER_PORTAL = (BlockMinerPortal)(new BlockMinerPortal()).func_149711_c(-1.0F).func_149715_a(0.75F).func_149658_d("palamod:miner_portal");
    BlocksRegister.STRANGE_GRASS = (Block)new BlockMinerMultiFaces("STRANGE_GRASS".toLowerCase(), "strange_grass_top", "strange_grass_side", "strange_grass_side");
    BlocksRegister.STRANGE_SAND = (Block)new BlockMiner("STRANGE_SAND".toLowerCase());
    BlocksRegister.STRANGE_DIRT = (Block)new BlockMiner("STRANGE_DIRT".toLowerCase());
    BlocksRegister.STRANGE_COBBLE = (Block)new BlockMiner("STRANGE_COBBLE".toLowerCase());
    BlocksRegister.STRANGE_STONE = (Block)new BlockMiner("STRANGE_STONE".toLowerCase());
    BlocksRegister.STRANGE_OAK_LOG = (Block)new BlockMinerMultiFaces("STRANGE_OAK_LOG".toLowerCase(), "STRANGE_OAK_LOG_TOP".toLowerCase(), "STRANGE_OAK_LOG_SIDE".toLowerCase(), "STRANGE_OAK_LOG_SIDE".toLowerCase());
    BlocksRegister.STRANGE_SPRUCE_LOG = (Block)new BlockMinerMultiFaces("STRANGE_SPRUCE_LOG".toLowerCase(), "STRANGE_SPRUCE_LOG_TOP".toLowerCase(), "STRANGE_SPRUCE_LOG_SIDE".toLowerCase(), "STRANGE_SPRUCE_LOG_SIDE".toLowerCase());
    BlocksRegister.STRANGE_JUNGLE_LOG = (Block)new BlockMinerMultiFaces("STRANGE_JUNGLE_LOG".toLowerCase(), "STRANGE_JUNGLE_LOG_TOP".toLowerCase(), "STRANGE_JUNGLE_LOG_SIDE".toLowerCase(), "STRANGE_JUNGLE_LOG_SIDE".toLowerCase());
    BlocksRegister.STRANGE_ACACIA_LOG = (Block)new BlockMinerMultiFaces("STRANGE_ACACIA_LOG".toLowerCase(), "STRANGE_ACACIA_LOG_TOP".toLowerCase(), "STRANGE_ACACIA_LOG_SIDE".toLowerCase(), "STRANGE_ACACIA_LOG_SIDE".toLowerCase());
    BlocksRegister.STRANGE_DARK_OAK_LOG = (Block)new BlockMinerMultiFaces("STRANGE_DARK_OAK_LOG".toLowerCase(), "STRANGE_DARK_OAK_LOG_TOP".toLowerCase(), "STRANGE_DARK_OAK_LOG_SIDE".toLowerCase(), "STRANGE_DARK_OAK_LOG_SIDE".toLowerCase());
    BlocksRegister.STRANGE_OAK_LEAVES = (Block)new BlockMinerLeaves("STRANGE_OAK_LEAVES".toLowerCase());
    BlocksRegister.STRANGE_SPRUCE_LEAVES = (Block)new BlockMinerLeaves("STRANGE_SPRUCE_LEAVES".toLowerCase());
    BlocksRegister.STRANGE_JUNGLE_LEAVES = (Block)new BlockMinerLeaves("STRANGE_JUNGLE_LEAVES".toLowerCase());
    BlocksRegister.STRANGE_ACACIA_LEAVES = (Block)new BlockMinerLeaves("STRANGE_ACACIA_LEAVES".toLowerCase());
    BlocksRegister.STRANGE_DARK_OAK_LEAVES = (Block)new BlockMinerLeaves("STRANGE_DARK_OAK_LEAVES".toLowerCase());
    BlocksRegister.REED = (Block)new BlockMinerPlant("REED".toLowerCase(), false, 2, new float[] { 0.9F, 2.0F }) {
        public boolean func_149854_a(Block block) {
          return (block == BlocksRegister.STRANGE_GRASS || block == BlocksRegister.STRANGE_DIRT || block == BlocksRegister.STRANGE_SAND);
        }
        
        public boolean func_149718_j(World world, int x, int y, int z) {
          Block block = world.func_147439_a(x, y - 1, z);
          return ((block == BlocksRegister.STRANGE_GRASS || block == BlocksRegister.STRANGE_DIRT || block == BlocksRegister.STRANGE_SAND) && (world.func_147439_a(x - 1, y - 1, z) == net.minecraft.init.Blocks.field_150355_j || world.func_147439_a(x + 1, y - 1, z) == net.minecraft.init.Blocks.field_150355_j || world.func_147439_a(x, y - 1, z - 1) == net.minecraft.init.Blocks.field_150355_j || world.func_147439_a(x, y - 1, z + 1) == net.minecraft.init.Blocks.field_150355_j));
        }
      };
    BlocksRegister.REED.func_149676_a(0.15F, 0.0F, 0.15F, 0.85F, 0.9F, 0.85F);
    BlocksRegister.THISTLE = (Block)new BlockMinerPlant("THISTLE".toLowerCase()) {
        public boolean func_149854_a(Block block) {
          return (block == BlocksRegister.STRANGE_GRASS || block == BlocksRegister.STRANGE_DIRT || block == BlocksRegister.STRANGE_SAND);
        }
        
        public boolean func_149718_j(World world, int x, int y, int z) {
          Block block = world.func_147439_a(x, y - 1, z);
          return (block == BlocksRegister.STRANGE_GRASS || block == BlocksRegister.STRANGE_DIRT || block == BlocksRegister.STRANGE_SAND);
        }
      };
    BlocksRegister.THISTLE.func_149676_a(0.15F, 0.0F, 0.15F, 0.85F, 0.9F, 0.85F);
    BlocksRegister.HOLLOW_LOG = (Block)new BlockMinerMultiFaces("HOLLOW_LOG".toLowerCase(), "HOLLOW_LOG_TOP".toLowerCase(), "HOLLOW_LOG_FRONT".toLowerCase(), "HOLLOW_LOG_SIDE".toLowerCase()) {
        public boolean func_149662_c() {
          return false;
        }
        
        public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
          return true;
        }
      };
    BlocksRegister.MUD = (Block)new BlockMiner("MUD".toLowerCase());
    BlocksRegister.ENDIUM_CAVE_BLOCK = (Block)new BlockEndiumCave();
    GameRegistry.registerBlock(BlocksRegister.PALADIUM_HOPPER, BlocksRegister.PALADIUM_HOPPER.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.DRAWBRIDGE, BlocksRegister.DRAWBRIDGE.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BEDROCK_TNT, BlocksRegister.BEDROCK_TNT.func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.WITHERED_REINFORCED_PISTON, BlocksRegister.WITHERED_REINFORCED_PISTON.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.VOID_STONE, BlocksRegister.VOID_STONE.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.AUTOCRAFTER, BlocksRegister.AUTOCRAFTER.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.WITHERED_OBSIDIAN, BlocksRegister.WITHERED_OBSIDIAN.func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.MINER_PORTAL, BlocksRegister.MINER_PORTAL.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_GRASS, BlocksRegister.STRANGE_GRASS.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_SAND, BlocksRegister.STRANGE_SAND.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_DIRT, BlocksRegister.STRANGE_DIRT.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_COBBLE, BlocksRegister.STRANGE_COBBLE.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_STONE, BlocksRegister.STRANGE_STONE.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_OAK_LOG, BlocksRegister.STRANGE_OAK_LOG.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_SPRUCE_LOG, BlocksRegister.STRANGE_SPRUCE_LOG.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_JUNGLE_LOG, BlocksRegister.STRANGE_JUNGLE_LOG.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_ACACIA_LOG, BlocksRegister.STRANGE_ACACIA_LOG.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_DARK_OAK_LOG, BlocksRegister.STRANGE_DARK_OAK_LOG.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_OAK_LEAVES, BlocksRegister.STRANGE_OAK_LEAVES.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_SPRUCE_LEAVES, BlocksRegister.STRANGE_SPRUCE_LEAVES.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_JUNGLE_LEAVES, BlocksRegister.STRANGE_JUNGLE_LEAVES.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_ACACIA_LEAVES, BlocksRegister.STRANGE_ACACIA_LEAVES.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STRANGE_DARK_OAK_LEAVES, BlocksRegister.STRANGE_DARK_OAK_LEAVES.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.REED, BlocksRegister.REED.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.THISTLE, BlocksRegister.THISTLE.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.HOLLOW_LOG, BlocksRegister.HOLLOW_LOG.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.MUD, BlocksRegister.MUD.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ENDIUM_CAVE_BLOCK, BlocksRegister.ENDIUM_CAVE_BLOCK.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityPaladiumHopper.class, "palamod:tileEntityPaladiumHopper");
    GameRegistry.registerTileEntity(TileEntityDrawBridge.class, "palamod:tileEntityDrawBridge");
    GameRegistry.registerTileEntity(TileEntityWitheredPiston.class, "palamod:tileEntityWitheredPiston");
    GameRegistry.registerTileEntity(TileEntityVoidStone.class, "palamod:tileEntityVoidStone");
    GameRegistry.registerTileEntity(TileEntityAutoCrafter.class, "palamod:tileEntityAutoCrafter");
    GameRegistry.registerTileEntity(TileEntityWitheredObsidian.class, "palamod:tileEntityWitheredObsidian");
    GameRegistry.registerTileEntity(TileEntityEndiumCaveBlock.class, "palamod:tileEntityEndiumCaveBlock");
    GameRegistry.registerTileEntity(TileEntityMinerPortal.class, "palamod:tileEntityMinerPortal");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\init\Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */