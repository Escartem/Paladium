package fr.paladium.palamod.modules.paladium.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.blocks.BaseBlock;
import fr.paladium.palamod.common.blocks.BaseBlockCrops;
import fr.paladium.palamod.common.blocks.BaseBlockFlower;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.paladium.common.blocks.BaseBlockSlimePad;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockAmethystChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockAnvil;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockBet;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockCave;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockConnectedDirt;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockCorrupted;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockCustomWeb;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockEndiumChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockGPaladiumChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockHardenedObsidian;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockInvisible;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockInvisibleCollide;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockLavaSponge;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockPaladiumChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockPaladiumMachine;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockPrintPress;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockShulker;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockSpike;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockStatue;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockTitaneChest;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockTotem;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockTypeMachine;
import fr.paladium.palamod.modules.paladium.common.blocks.TileEntityBetBlock;
import fr.paladium.palamod.modules.paladium.common.blocks.bush.BlockXPBush;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BaseBlockSlab;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BaseBlockSlabHardened;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BaseBlockStairs;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BaseBlockStairsHardened;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BlockObsidianDoor;
import fr.paladium.palamod.modules.paladium.common.blocks.decorative.BlockObsidianTrapDoor;
import fr.paladium.palamod.modules.paladium.common.blocks.flower.HarpagophytumBlockFlower;
import fr.paladium.palamod.modules.paladium.common.blocks.fluids.BlockAngelicWater;
import fr.paladium.palamod.modules.paladium.common.blocks.fluids.BlockSulfuricWater;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockAlchemyCreator;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockBowMachine;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockCobbleBreaker;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockCrusher;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockForge;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockOnlineDetector;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockPaladiumFurnace;
import fr.paladium.palamod.modules.paladium.common.items.ItemBlockInvisible;
import fr.paladium.palamod.modules.paladium.common.items.ItemTotem;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class PRegister_Blocks {
  public static void init() {
    BlocksRegister.PALADIUM_MACHINE_BLOCK = (new BlockPaladiumMachine(Material.field_151573_f, 12.0F, "machines/paladium_machine_block")).func_149663_c("paladium.machine");
    BlocksRegister.SLIMEPAD_BLOCK = (new BaseBlockSlimePad(Material.field_151576_e, 12.0F, "slime/slime_green")).func_149752_b(8.0F).func_149663_c("slimepad");
    BlocksRegister.PALADIUM_CHEST = new BlockPaladiumChest("paladium_chest");
    BlocksRegister.ENDIUM_CHEST = new BlockEndiumChest("endium_chest");
    BlocksRegister.TITANE_CHEST = new BlockTitaneChest("titane_chest");
    BlocksRegister.AMETHYST_CHEST = new BlockAmethystChest("amethyst_chest");
    BlocksRegister.GPALADIUM_CHEST = new BlockGPaladiumChest("gpaladium_chest");
    BlocksRegister.PRINT_PRESS = (Block)new BlockPrintPress("print_press");
    BlocksRegister.TYPE_MACHINE = (Block)new BlockTypeMachine("type_machine");
    BlocksRegister.STATUE = (Block)new BlockStatue();
    BlocksRegister.LAVA_SPONGE = (Block)new BlockLavaSponge();
    GameRegistry.registerBlock(BlocksRegister.PALADIUM_MACHINE_BLOCK, BlocksRegister.PALADIUM_MACHINE_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SLIMEPAD_BLOCK, BlocksRegister.SLIMEPAD_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.PALADIUM_CHEST, BlocksRegister.PALADIUM_CHEST
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.ENDIUM_CHEST, BlocksRegister.ENDIUM_CHEST
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.TITANE_CHEST, BlocksRegister.TITANE_CHEST
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.AMETHYST_CHEST, BlocksRegister.AMETHYST_CHEST
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.GPALADIUM_CHEST, BlocksRegister.GPALADIUM_CHEST
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.PRINT_PRESS, BlocksRegister.PRINT_PRESS
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.TYPE_MACHINE, BlocksRegister.TYPE_MACHINE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.STATUE, BlocksRegister.STATUE.func_149739_a());
    String name = BlocksRegister.LAVA_SPONGE.func_149739_a();
    String[] strings = name.split("\\.");
    GameRegistry.registerBlock(BlocksRegister.LAVA_SPONGE, ((ModBlocks.ISubBlocksBlock)BlocksRegister.LAVA_SPONGE)
        .getItemBlockClass(), strings[strings.length - 1]);
    BlocksRegister.FLOWER_HARPAGOPHYTUM = (Block)new HarpagophytumBlockFlower("flower_harpagophytum");
    BlocksRegister.FLOWER_ABSINTHE = (Block)new BaseBlockFlower("flower_absinthe");
    BlocksRegister.FLOWER_ACTAEAPACHYPODA = (Block)new BaseBlockFlower("flower_actaeapachypoda");
    BlocksRegister.FLOWER_CLATHRUSARCHERI = (Block)new BaseBlockFlower("flower_clathrusarcheri");
    BlocksRegister.FLOWER_ENDIUM = (Block)new BaseBlockFlower("flower_endium");
    BlocksRegister.FLOWER_PALADIUM = (Block)new BaseBlockFlower("flower_paladium");
    BlocksRegister.FLOWER_MINT = (Block)new BaseBlockFlower("flower_mint");
    BlocksRegister.FLOWER_ORTIE = (Block)new BaseBlockFlower("flower_ortie");
    BlocksRegister.FLOWER_SAUGE = (Block)new BaseBlockFlower("flower_sauge");
    BlocksRegister.FLOWER_TREFLE = (Block)new BaseBlockFlower("flower_trefle");
    BlocksRegister.FLOWER_MINERAL = (Block)new BaseBlockFlower("flower_mineral");
    BlocksRegister.connectedDirt = new BlockConnectedDirt(false);
    BlocksRegister.connectedDirtTilled = new BlockConnectedDirt(true);
    BlocksRegister.Totem = new BlockTotem("totem");
    GameRegistry.registerBlock(BlocksRegister.FLOWER_HARPAGOPHYTUM, BlocksRegister.FLOWER_HARPAGOPHYTUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_ABSINTHE, BlocksRegister.FLOWER_ABSINTHE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_ACTAEAPACHYPODA, BlocksRegister.FLOWER_ACTAEAPACHYPODA
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_CLATHRUSARCHERI, BlocksRegister.FLOWER_CLATHRUSARCHERI
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_ENDIUM, BlocksRegister.FLOWER_ENDIUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_PALADIUM, BlocksRegister.FLOWER_PALADIUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_MINT, BlocksRegister.FLOWER_MINT
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_ORTIE, BlocksRegister.FLOWER_ORTIE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_SAUGE, BlocksRegister.FLOWER_SAUGE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_TREFLE, BlocksRegister.FLOWER_TREFLE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FLOWER_MINERAL, BlocksRegister.FLOWER_MINERAL
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.connectedDirt, BlocksRegister.connectedDirt.blockname);
    GameRegistry.registerBlock((Block)BlocksRegister.connectedDirtTilled, BlocksRegister.connectedDirtTilled.blockname);
    GameRegistry.registerBlock((Block)BlocksRegister.Totem, ItemTotem.class, BlocksRegister.Totem
        .func_149739_a());
    BlocksRegister.BLOCK_FINDIUM = (new BaseBlock(Material.field_151573_f, 6.0F, "findium/findium_block")).func_149663_c("findium_block");
    BlocksRegister.BLOCK_TRIXIUM = (new BaseBlock(Material.field_151573_f, 6.0F, "trixium/trixium_block")).func_149663_c("trixium_block");
    BlocksRegister.BLOCK_PALADIUM = (new BaseBlock(Material.field_151573_f, 6.0F, "paladium/paladium_block")).func_149663_c("paladium_block");
    BlocksRegister.BLOCK_TITANE = (new BaseBlock(Material.field_151573_f, 6.0F, "titane/titane_block")).func_149663_c("titane_block");
    BlocksRegister.BLOCK_AMETHYST = (new BaseBlock(Material.field_151573_f, 6.0F, "amethyst/amethyst_block")).func_149663_c("amethyst_block");
    BlocksRegister.BLOCK_GPALADIUM = (new BaseBlock(Material.field_151573_f, 6.0F, "gpaladium/gpaladium_block")).func_149663_c("gpaladium_block");
    GameRegistry.registerBlock(BlocksRegister.BLOCK_FINDIUM, BlocksRegister.BLOCK_FINDIUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLOCK_TRIXIUM, BlocksRegister.BLOCK_TRIXIUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLOCK_PALADIUM, BlocksRegister.BLOCK_PALADIUM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLOCK_TITANE, BlocksRegister.BLOCK_TITANE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLOCK_AMETHYST, BlocksRegister.BLOCK_AMETHYST
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BLOCK_GPALADIUM, BlocksRegister.BLOCK_GPALADIUM
        .func_149739_a());
    BlocksRegister.FORGE = new BlockForge("forge", false);
    BlocksRegister.LIT_FORGE = new BlockForge("lit_forge", true);
    BlocksRegister.PALADIUM_FURNACE = new BlockPaladiumFurnace("paladium_furnace_block", false);
    BlocksRegister.LIT_PALADIUM_FURNACE = new BlockPaladiumFurnace("lit_paladium_furnace_block", true);
    BlocksRegister.ALCHEMY_CREATOR_BLOCK = new BlockAlchemyCreator("alchemy_creator_block");
    BlocksRegister.BOW_MACHINE_BLOCK = new BlockBowMachine("bow_machine_block");
    BlocksRegister.COBBLEBREAKER = new BlockCobbleBreaker("cobbleBreaker");
    BlocksRegister.CRUSHER = new BlockCrusher();
    BlocksRegister.ONLINE_DETECTOR_BLOCK = new BlockOnlineDetector("online_detector_block");
    GameRegistry.registerBlock((Block)BlocksRegister.FORGE, BlocksRegister.FORGE.func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.LIT_FORGE, BlocksRegister.LIT_FORGE
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.PALADIUM_FURNACE, BlocksRegister.PALADIUM_FURNACE
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.LIT_PALADIUM_FURNACE, BlocksRegister.LIT_PALADIUM_FURNACE
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.ALCHEMY_CREATOR_BLOCK, BlocksRegister.ALCHEMY_CREATOR_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.BOW_MACHINE_BLOCK, BlocksRegister.BOW_MACHINE_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.COBBLEBREAKER, BlocksRegister.COBBLEBREAKER
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.CRUSHER, BlocksRegister.CRUSHER
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.ONLINE_DETECTOR_BLOCK, BlocksRegister.ONLINE_DETECTOR_BLOCK
        .func_149739_a());
    BlocksRegister.FLUIDS_SULFURICWATER = new BlockSulfuricWater(PRegister_Fluids.FLUIDS_SULFURICWATER, Material.field_151586_h);
    BlocksRegister.FLUIDS_ANGELICWATER = new BlockAngelicWater(PRegister_Fluids.FLUIDS_ANGELICWATER, Material.field_151586_h);
    GameRegistry.registerBlock((Block)BlocksRegister.FLUIDS_SULFURICWATER, BlocksRegister.FLUIDS_SULFURICWATER
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.FLUIDS_ANGELICWATER, BlocksRegister.FLUIDS_ANGELICWATER
        .func_149739_a());
    BlocksRegister.CROPS_ORANGEBLUE = new BaseBlockCrops("crops.orangeblue", new String[] { "orangeblue_stage_0", "orangeblue_stage_0", "orangeblue_stage_1", "orangeblue_stage_1", "orangeblue_stage_2", "orangeblue_stage_2", "orangeblue_stage_3", "orangeblue_stage_3", "orangeblue_stage_4", "orangeblue_stage_4" }, 20, 10);
    BlocksRegister.CROPS_KIWANO = new BaseBlockCrops("crops.kiwano", new String[] { "kiwano_stage_0", "kiwano_stage_0", "kiwano_stage_1", "kiwano_stage_1", "kiwano_stage_2", "kiwano_stage_2", "kiwano_stage_3", "kiwano_stage_3", "kiwano_stage_4", "kiwano_stage_4" }, 15, 10);
    BlocksRegister.CROPS_CHERVIL = new BaseBlockCrops("crops.chervil", new String[] { "chervil_stage_0", "chervil_stage_0", "chervil_stage_1", "chervil_stage_1", "chervil_stage_2", "chervil_stage_2", "chervil_stage_3", "chervil_stage_3" }, 10, 8);
    BlocksRegister.CROPS_EGGPLANT = new BaseBlockCrops("crops.eggplant", new String[] { "eggplant_stage_0", "eggplant_stage_0", "eggplant_stage_1", "eggplant_stage_1", "eggplant_stage_2", "eggplant_stage_2", "eggplant_stage_3", "eggplant_stage_3" }, 5, 8);
    BlocksRegister.CROPS_ICE = new BaseBlockCrops("crops.ice", new String[] { "ice_stage_0", "ice_stage_1", "ice_stage_1" }, 0, 3);
    GameRegistry.registerBlock((Block)BlocksRegister.CROPS_ORANGEBLUE, BlocksRegister.CROPS_ORANGEBLUE
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.CROPS_KIWANO, BlocksRegister.CROPS_KIWANO
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.CROPS_CHERVIL, BlocksRegister.CROPS_CHERVIL
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.CROPS_EGGPLANT, BlocksRegister.CROPS_EGGPLANT
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.CROPS_ICE, BlocksRegister.CROPS_ICE
        .func_149739_a());
    BlocksRegister.BUSH_XP_BERRY = new BlockXPBush("berry_xp_bush", new String[] { "berry_xp_bush", "", "", "", "berry_xp_bush_ripe", "", "", "" });
    GameRegistry.registerBlock((Block)BlocksRegister.BUSH_XP_BERRY, BlocksRegister.BUSH_XP_BERRY
        .func_149739_a());
    BlocksRegister.OBSIDIAN_BLOCK_DOOR = new BlockObsidianDoor();
    BlocksRegister.OBSIDIAN_TRAP_DOOR = new BlockObsidianTrapDoor();
    BlocksRegister.OBSIDIAN_STAIRS = new BaseBlockStairs(Blocks.field_150343_Z, "obsidianBlockStairs", 100.0F, 50.0F, "pickaxe", 2);
    BlocksRegister.OBSIDIAN_HARDENED_STAIRS = new BaseBlockStairsHardened(Blocks.field_150343_Z, "hardenedObsidianBlockStairs", 100.0F, -1.0F, "pickaxe", 2);
    BlocksRegister.OBSIDIAN_SLAB = new BaseBlockSlab((Block)BlocksRegister.OBSIDIAN_SLAB, Material.field_151573_f, "obsidianBlockSlab", "obsidian", 100.0F, 50.0F, "pickaxe", 2);
    BlocksRegister.OBSIDIAN_HARDENED_SLAB = new BaseBlockSlabHardened((Block)BlocksRegister.OBSIDIAN_HARDENED_SLAB, Material.field_151573_f, "hardenedObsidianBlockSlab", "obsidian", 100.0F, -1.0F, "pickaxe", 2);
    BlocksRegister.FINDIUM_STAIRS = new BaseBlockStairs(BlocksRegister.BLOCK_FINDIUM, "findiumBlockStairs", 15.0F, 20.0F, "pickaxe", 1);
    BlocksRegister.FINDIUM_SLAB = new BaseBlockSlab((Block)BlocksRegister.FINDIUM_SLAB, Material.field_151573_f, "findiumBlockSlab", "palamod:findium/findium_block", 15.0F, 20.0F, "pickaxe", 1);
    BlocksRegister.PALADIUM_STAIRS = new BaseBlockStairs(BlocksRegister.BLOCK_PALADIUM, "paladiumBlockStairs", 15.0F, 20.0F, "pickaxe", 1);
    BlocksRegister.PALADIUM_SLAB = new BaseBlockSlab((Block)BlocksRegister.PALADIUM_SLAB, Material.field_151573_f, "paladiumBlockSlab", "palamod:paladium/paladium_block", 15.0F, 20.0F, "pickaxe", 1);
    BlocksRegister.TITANE_STAIRS = new BaseBlockStairs(BlocksRegister.BLOCK_TITANE, "titaneBlockStairs", 15.0F, 20.0F, "pickaxe", 1);
    BlocksRegister.TITANE_SLAB = new BaseBlockSlab((Block)BlocksRegister.TITANE_SLAB, Material.field_151573_f, "titaneBlockSlab", "palamod:titane/titane_block", 15.0F, 20.0F, "pickaxe", 1);
    BlocksRegister.AMETHYST_STAIRS = new BaseBlockStairs(BlocksRegister.BLOCK_AMETHYST, "amethystBlockStairs", 15.0F, 20.0F, "pickaxe", 1);
    BlocksRegister.AMETHYST_SLAB = new BaseBlockSlab((Block)BlocksRegister.AMETHYST_SLAB, Material.field_151573_f, "amethystBlockSlab", "palamod:amethyst/amethyst_block", 15.0F, 20.0F, "pickaxe", 1);
    BlocksRegister.FACTORY = (new BaseBlock(Material.field_151573_f, 4.0F, "factory/circuit")).func_149663_c("factory");
    BlocksRegister.FUTURA = (new BaseBlock(Material.field_151573_f, 4.0F, "futura/screenmetallic")).func_149663_c("futura");
    BlocksRegister.SHULKER_WHITE = (Block)new BlockShulker(0);
    BlocksRegister.SHULKER_GREEN = (Block)new BlockShulker(1);
    BlocksRegister.SHULKER_RED = (Block)new BlockShulker(2);
    BlocksRegister.SHULKER_BLUE = (Block)new BlockShulker(3, true, true, true);
    GameRegistry.registerBlock((Block)BlocksRegister.OBSIDIAN_BLOCK_DOOR, BlocksRegister.OBSIDIAN_BLOCK_DOOR
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.OBSIDIAN_TRAP_DOOR, BlocksRegister.OBSIDIAN_TRAP_DOOR
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.OBSIDIAN_STAIRS, BlocksRegister.OBSIDIAN_STAIRS
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.OBSIDIAN_SLAB, BlocksRegister.OBSIDIAN_SLAB
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.OBSIDIAN_HARDENED_STAIRS, BlocksRegister.OBSIDIAN_HARDENED_STAIRS
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.OBSIDIAN_HARDENED_SLAB, BlocksRegister.OBSIDIAN_HARDENED_SLAB
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.FINDIUM_STAIRS, BlocksRegister.FINDIUM_STAIRS
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.FINDIUM_SLAB, BlocksRegister.FINDIUM_SLAB
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.PALADIUM_SLAB, BlocksRegister.PALADIUM_SLAB
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.PALADIUM_STAIRS, BlocksRegister.PALADIUM_STAIRS
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.TITANE_SLAB, BlocksRegister.TITANE_SLAB
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.TITANE_STAIRS, BlocksRegister.TITANE_STAIRS
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.AMETHYST_SLAB, BlocksRegister.AMETHYST_SLAB
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.AMETHYST_STAIRS, BlocksRegister.AMETHYST_STAIRS
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SHULKER_WHITE, BlocksRegister.SHULKER_WHITE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SHULKER_GREEN, BlocksRegister.SHULKER_GREEN
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SHULKER_RED, BlocksRegister.SHULKER_RED
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SHULKER_BLUE, BlocksRegister.SHULKER_BLUE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FACTORY, BlocksRegister.FACTORY
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.FUTURA, BlocksRegister.FUTURA.func_149739_a());
    BlocksRegister.SPIKE_WOOD = new BlockSpike("wood_spike_block", "spikewood", "spike/SpikeWood_2", "spike/SpikeWood_1", 2.0F, false);
    BlocksRegister.SPIKE_IRON = new BlockSpike("iron_spike_block", "spikeiron", "spike/SpikeIron_1", "spike/SpikeIron_2", 5.0F, false);
    BlocksRegister.SPIKE_GOLD = new BlockSpike("gold_spike_block", "spikegold", "spike/SpikeGold_2", "spike/SpikeGold_1", 7.0F, false);
    BlocksRegister.SPIKE_DIAMOND = new BlockSpike("diamond_spike_block", "spikediamond", "spike/SpikeDiamond_2", "spike/SpikeDiamond_1", 10.0F, false);
    BlocksRegister.SPIKE_AMETHYST = new BlockSpike("amethyst_spike_block", "spikeamethyst", "spike/SpikeAmethyst_1", "spike/SpikeAmethyst_2", 10.0F, false);
    BlocksRegister.SPIKE_TITANE = new BlockSpike("titane_spike_block", "spiketitane", "spike/SpikeTitane_1", "spike/SpikeTitane_2", 12.0F, false);
    BlocksRegister.SPIKE_PALADIUM = new BlockSpike("paladium_spike_block", "spikepaladium", "spike/SpikePaladium_1", "spike/SpikePaladium_2", 15.0F, true);
    GameRegistry.registerBlock((Block)BlocksRegister.SPIKE_WOOD, BlocksRegister.SPIKE_WOOD
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.SPIKE_IRON, BlocksRegister.SPIKE_IRON
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.SPIKE_GOLD, BlocksRegister.SPIKE_GOLD
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.SPIKE_DIAMOND, BlocksRegister.SPIKE_DIAMOND
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.SPIKE_AMETHYST, BlocksRegister.SPIKE_AMETHYST
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.SPIKE_TITANE, BlocksRegister.SPIKE_TITANE
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.SPIKE_PALADIUM, BlocksRegister.SPIKE_PALADIUM
        .func_149739_a());
    BlocksRegister.ANVIL_AMETHYST = (Block)new BlockAnvil(PToolMaterial.amethyst, 0);
    BlocksRegister.ANVIL_TITANE = (Block)new BlockAnvil(PToolMaterial.titane, 1);
    BlocksRegister.ANVIL_PALADIUM = (Block)new BlockAnvil(PToolMaterial.paladium, 2);
    GameRegistry.registerBlock(BlocksRegister.ANVIL_AMETHYST, BlocksRegister.ANVIL_AMETHYST
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ANVIL_TITANE, BlocksRegister.ANVIL_TITANE
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ANVIL_PALADIUM, BlocksRegister.ANVIL_PALADIUM
        .func_149739_a());
    BlocksRegister.BET_BLOCK = (Block)new BlockBet();
    GameRegistry.registerBlock(BlocksRegister.BET_BLOCK, BlocksRegister.BET_BLOCK.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityBetBlock.class, "palamod:tileEntityBetBlock");
    BlocksRegister.WEB_CUSTOM = new BlockCustomWeb();
    BlocksRegister.CAVE_BLOCK = new BlockCave("cave_block");
    BlocksRegister.INVISIBLE_BLOCK = new BlockInvisible("invisible_block");
    BlocksRegister.COLLIDE_INVISIBLE_BLOCK = new BlockInvisibleCollide("collide_invisible_block");
    BlocksRegister.HARDENED_OBSIDIAN = new BlockHardenedObsidian();
    BlocksRegister.CORRUPTED_BLOCK = (Block)new BlockCorrupted();
    RegistryUtils.block(new Block[] { BlocksRegister.CORRUPTED_BLOCK });
    GameRegistry.registerBlock((Block)BlocksRegister.WEB_CUSTOM, BlocksRegister.WEB_CUSTOM
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.CAVE_BLOCK, BlocksRegister.CAVE_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.INVISIBLE_BLOCK, ItemBlockInvisible.class, BlocksRegister.INVISIBLE_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.COLLIDE_INVISIBLE_BLOCK, BlocksRegister.COLLIDE_INVISIBLE_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock((Block)BlocksRegister.HARDENED_OBSIDIAN, BlocksRegister.HARDENED_OBSIDIAN
        .func_149739_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */