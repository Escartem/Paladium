package fr.paladium.palamod.modules.world;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.client.gui.PGuiOverlayDebug;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.blocks.BaseBlock;
import fr.paladium.palamod.common.blocks.BaseMetaBlock;
import fr.paladium.palamod.config.PConfigs;
import fr.paladium.palamod.modules.miner.blocks.BlockMiner;
import fr.paladium.palamod.modules.miner.dimminer.common.block.BlockEndiumNuggetOre;
import fr.paladium.palamod.modules.world.blocks.BaseBlockLog;
import fr.paladium.palamod.modules.world.blocks.BaseBlockLogSeve;
import fr.paladium.palamod.modules.world.blocks.BlockFindiumOre;
import fr.paladium.palamod.modules.world.blocks.BlockLeaveBase;
import fr.paladium.palamod.modules.world.blocks.BlockOre;
import fr.paladium.palamod.modules.world.blocks.BlockTrixiumOre;
import fr.paladium.palamod.modules.world.blocks.bush.BaseSaplingCustom;
import fr.paladium.palamod.modules.world.events.DebugInfoHandler;
import fr.paladium.palamod.modules.world.gen.BaseWorldGenerator;
import fr.paladium.palamod.modules.world.itemblocks.AndesiteItemBlock;
import fr.paladium.palamod.modules.world.itemblocks.DioriteItemBlock;
import fr.paladium.palamod.modules.world.itemblocks.GraniteItemBlock;
import fr.paladium.palamod.modules.world.network.PacketDebugInfo;
import fr.paladium.palamod.modules.world.network.PacketRequestDebugInfo;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

@ObjectHolder("palamod")
@Pulse(id = "palamod-world", description = "Ores, plants, essence berries, and the like.", forced = true)
public class PWorld {
  @Instance("palamod-world")
  public static PWorld instance;
  
  public static DebugInfoHandler serverHandler;
  
  public static Block AMETHYST_ORE;
  
  public static Block TITANE_ORE;
  
  public static Block PALADIUM_ORE;
  
  public static Block PALADIUM_GREEN_ORE;
  
  public static Block FINDIUM_ORE;
  
  public static Block TRIXIUM_ORE;
  
  public static Block GRANITE_BLOCK;
  
  public static Block DIORITE_BLOCK;
  
  public static Block ANDESITE_BLOCK;
  
  public static Block LIMESTONE_BLOCK;
  
  public static Block MARBLE_BLOCK;
  
  public static Block LOG_JUDEECERCIS;
  
  public static Block LEAVE_JUDEECERCIS;
  
  public static Block PLANKS_JUDEECERCIS;
  
  public static BaseSaplingCustom SAPLING_JUDEECERCIS;
  
  public static Block LOG_JACARANDA;
  
  public static Block LEAVE_JACARANDA;
  
  public static Block PLANKS_JACARANDA;
  
  public static BaseSaplingCustom SAPLING_JACARANDA;
  
  public static Block LOG_ERABLE;
  
  public static Block LEAVE_ERABLE;
  
  public static Block PLANKS_ERABLE;
  
  public static BaseSaplingCustom SAPLING_ERABLE;
  
  public static Block LOG_OSTRYA;
  
  public static Block LEAVE_OSTRYA;
  
  public static Block PLANKS_OSTRYA;
  
  public static BaseSaplingCustom SAPLING_OSTRYA;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    PalaMod.getNetwork().registerMessage(PacketRequestDebugInfo.Handler.class, PacketRequestDebugInfo.class, 0, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketDebugInfo.Handler.class, PacketDebugInfo.class, 1, Side.CLIENT);
    AMETHYST_ORE = (new BlockOre(Material.field_151576_e, 3.0F, "ores/amethyst_ore")).func_149752_b(5.0F).func_149663_c("amethyst.ore");
    AMETHYST_ORE.setHarvestLevel("pickaxe", 1);
    TITANE_ORE = (new BlockOre(Material.field_151576_e, 3.0F, "ores/titane_ore")).func_149752_b(5.0F).func_149663_c("titane.ore");
    TITANE_ORE.setHarvestLevel("pickaxe", 2);
    PALADIUM_ORE = (new BlockOre(Material.field_151576_e, 5.0F, "ores/paladium_ore")).func_149752_b(5.0F).func_149663_c("paladium.ore");
    PALADIUM_ORE.setHarvestLevel("pickaxe", 3);
    PALADIUM_GREEN_ORE = (new BlockOre(Material.field_151576_e, 5.0F, "ores/paladium_green_ore")).func_149752_b(5.0F).func_149663_c("paladium.green.ore");
    PALADIUM_GREEN_ORE.setHarvestLevel("pickaxe", 3);
    FINDIUM_ORE = (new BlockFindiumOre(Material.field_151576_e, 5.0F, "ores/findium_ore")).func_149752_b(5.0F).func_149663_c("findium.ore");
    FINDIUM_ORE.setHarvestLevel("pickaxe", 3);
    TRIXIUM_ORE = (new BlockTrixiumOre(Material.field_151576_e, 3.0F, "ores/trixium_ore")).func_149752_b(5.0F).func_149663_c("trixium.ore");
    TRIXIUM_ORE.setHarvestLevel("pickaxe", 3);
    String[] types = { "granite/granite_block", "granite/granite_smooth_block" };
    GRANITE_BLOCK = (new BaseMetaBlock(Material.field_151576_e, 1.5F, types)).func_149752_b(10.0F).func_149663_c("granite");
    types = new String[] { "diorite/diorite_block", "diorite/diorite_smooth_block" };
    DIORITE_BLOCK = (new BaseMetaBlock(Material.field_151576_e, 1.5F, types)).func_149752_b(10.0F).func_149663_c("diorite");
    types = new String[] { "andesite/andesite_block", "andesite/andesite_smooth_block" };
    ANDESITE_BLOCK = (new BaseMetaBlock(Material.field_151576_e, 1.5F, types)).func_149752_b(10.0F).func_149663_c("andesite");
    LIMESTONE_BLOCK = (new BaseBlock(Material.field_151576_e, 1.5F, "limestone/limestone_block")).func_149752_b(10.0F).func_149663_c("limestone");
    MARBLE_BLOCK = (new BaseBlock(Material.field_151576_e, 1.5F, "marble/marble_block")).func_149752_b(10.0F).func_149663_c("marble");
    SAPLING_JUDEECERCIS = new BaseSaplingCustom(1);
    LOG_JUDEECERCIS = (new BaseBlockLogSeve(Material.field_151575_d, 3.0F, "log/judeecercis")).func_149663_c("log.judeecercis");
    LEAVE_JUDEECERCIS = (Block)new BlockLeaveBase("judeecercis", 100, (Block)SAPLING_JUDEECERCIS);
    PLANKS_JUDEECERCIS = (new BaseBlock(Material.field_151575_d, 3.0F, "planks/judeecercis")).func_149663_c("planks.judeecercis").func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
    SAPLING_JACARANDA = new BaseSaplingCustom(2);
    LOG_JACARANDA = (new BaseBlockLogSeve(Material.field_151575_d, 3.0F, "log/jacaranda")).func_149663_c("log.jacaranda");
    LEAVE_JACARANDA = (Block)new BlockLeaveBase("jacaranda", 100, (Block)SAPLING_JACARANDA);
    PLANKS_JACARANDA = (new BaseBlock(Material.field_151575_d, 3.0F, "planks/jacaranda")).func_149663_c("planks.jacaranda").func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
    SAPLING_ERABLE = new BaseSaplingCustom(3);
    LOG_ERABLE = (new BaseBlockLogSeve(Material.field_151575_d, 3.0F, "log/erable")).func_149663_c("log.erable");
    LEAVE_ERABLE = (Block)new BlockLeaveBase("erable", 100, (Block)SAPLING_ERABLE);
    PLANKS_ERABLE = (new BaseBlock(Material.field_151575_d, 3.0F, "planks/erable")).func_149663_c("planks.erable").func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
    SAPLING_OSTRYA = new BaseSaplingCustom(4);
    LOG_OSTRYA = (new BaseBlockLogSeve(Material.field_151575_d, 3.0F, "log/ostrya")).func_149663_c("log.ostrya");
    LEAVE_OSTRYA = (Block)new BlockLeaveBase("ostrya", 100, (Block)SAPLING_OSTRYA);
    PLANKS_OSTRYA = (new BaseBlock(Material.field_151575_d, 3.0F, "planks/ostrya")).func_149663_c("planks.ostrya").func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
    BlocksRegister.DEAD_WOOD = (new BaseBlockLog(Material.field_151575_d, 3.0F, "log/dead_wood")).func_149663_c("log.dead_wood");
    BlocksRegister.PLANKS_DEAD_WOOD = (new BaseBlock(Material.field_151575_d, 3.0F, "planks/dead_wood")).func_149663_c("planks.dead_wood").func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
    GameRegistry.registerBlock((Block)SAPLING_JUDEECERCIS, SAPLING_JUDEECERCIS.func_149739_a());
    GameRegistry.registerBlock(LOG_JUDEECERCIS, LOG_JUDEECERCIS.func_149739_a());
    GameRegistry.registerBlock(LEAVE_JUDEECERCIS, LEAVE_JUDEECERCIS.func_149739_a());
    GameRegistry.registerBlock(PLANKS_JUDEECERCIS, PLANKS_JUDEECERCIS.func_149739_a());
    GameRegistry.registerBlock((Block)SAPLING_JACARANDA, SAPLING_JACARANDA.func_149739_a());
    GameRegistry.registerBlock(LOG_JACARANDA, LOG_JACARANDA.func_149739_a());
    GameRegistry.registerBlock(LEAVE_JACARANDA, LEAVE_JACARANDA.func_149739_a());
    GameRegistry.registerBlock(PLANKS_JACARANDA, PLANKS_JACARANDA.func_149739_a());
    GameRegistry.registerBlock((Block)SAPLING_ERABLE, SAPLING_ERABLE.func_149739_a());
    GameRegistry.registerBlock(LOG_ERABLE, LOG_ERABLE.func_149739_a());
    GameRegistry.registerBlock(LEAVE_ERABLE, LEAVE_ERABLE.func_149739_a());
    GameRegistry.registerBlock(PLANKS_ERABLE, PLANKS_ERABLE.func_149739_a());
    GameRegistry.registerBlock((Block)SAPLING_OSTRYA, SAPLING_OSTRYA.func_149739_a());
    GameRegistry.registerBlock(LOG_OSTRYA, LOG_OSTRYA.func_149739_a());
    GameRegistry.registerBlock(LEAVE_OSTRYA, LEAVE_OSTRYA.func_149739_a());
    GameRegistry.registerBlock(PLANKS_OSTRYA, PLANKS_OSTRYA.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.DEAD_WOOD, BlocksRegister.DEAD_WOOD.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.PLANKS_DEAD_WOOD, BlocksRegister.PLANKS_DEAD_WOOD.func_149739_a());
    GameRegistry.registerBlock(AMETHYST_ORE, AMETHYST_ORE.func_149739_a());
    GameRegistry.registerBlock(TITANE_ORE, TITANE_ORE.func_149739_a());
    GameRegistry.registerBlock(PALADIUM_ORE, PALADIUM_ORE.func_149739_a());
    GameRegistry.registerBlock(PALADIUM_GREEN_ORE, PALADIUM_GREEN_ORE.func_149739_a());
    GameRegistry.registerBlock(FINDIUM_ORE, FINDIUM_ORE.func_149739_a());
    GameRegistry.registerBlock(TRIXIUM_ORE, TRIXIUM_ORE.func_149739_a());
    GameRegistry.registerBlock(GRANITE_BLOCK, GraniteItemBlock.class, "granite");
    GameRegistry.registerBlock(DIORITE_BLOCK, DioriteItemBlock.class, "diorite");
    GameRegistry.registerBlock(ANDESITE_BLOCK, AndesiteItemBlock.class, "andesite");
    GameRegistry.registerBlock(LIMESTONE_BLOCK, LIMESTONE_BLOCK.func_149739_a());
    GameRegistry.registerBlock(MARBLE_BLOCK, MARBLE_BLOCK.func_149739_a());
    BlocksRegister.ENDIUM_NUGGET_ORE = (Block)new BlockEndiumNuggetOre("endium_nuggets_ore".toLowerCase());
    BlocksRegister.INVOKER_ORE = (Block)new BlockMiner("invoker_ore".toLowerCase());
    GameRegistry.registerBlock(BlocksRegister.ENDIUM_NUGGET_ORE, BlocksRegister.ENDIUM_NUGGET_ORE.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.INVOKER_ORE, BlocksRegister.INVOKER_ORE.func_149739_a());
    oreRegistry();
    serverHandler = new DebugInfoHandler();
    FMLCommonHandler.instance().bus().register(serverHandler);
    MinecraftForge.EVENT_BUS.register(serverHandler);
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    setupTabs();
    craftingTableRecipes();
    addRecipesForFurnace();
    if (PConfigs.server_minage_enabled)
      GameRegistry.registerWorldGenerator((IWorldGenerator)new BaseWorldGenerator(), 0); 
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {}
  
  private void setupTabs() {}
  
  private void oreRegistry() {
    OreDictionary.registerOre(AMETHYST_ORE.func_149732_F(), AMETHYST_ORE);
    OreDictionary.registerOre(TITANE_ORE.func_149732_F(), TITANE_ORE);
    OreDictionary.registerOre(PALADIUM_ORE.func_149732_F(), PALADIUM_ORE);
    OreDictionary.registerOre(PALADIUM_GREEN_ORE.func_149732_F(), PALADIUM_GREEN_ORE);
    OreDictionary.registerOre(FINDIUM_ORE.func_149732_F(), FINDIUM_ORE);
    OreDictionary.registerOre(TRIXIUM_ORE.func_149732_F(), TRIXIUM_ORE);
    OreDictionary.registerOre(GRANITE_BLOCK.func_149732_F(), GRANITE_BLOCK);
    OreDictionary.registerOre(DIORITE_BLOCK.func_149732_F(), DIORITE_BLOCK);
    OreDictionary.registerOre(ANDESITE_BLOCK.func_149732_F(), ANDESITE_BLOCK);
    OreDictionary.registerOre(LIMESTONE_BLOCK.func_149732_F(), LIMESTONE_BLOCK);
    OreDictionary.registerOre(MARBLE_BLOCK.func_149732_F(), MARBLE_BLOCK);
    OreDictionary.registerOre(BlocksRegister.ENDIUM_NUGGET_ORE.func_149732_F(), BlocksRegister.ENDIUM_NUGGET_ORE);
  }
  
  private void craftingTableRecipes() {
    GameRegistry.addShapelessRecipe(new ItemStack(PLANKS_ERABLE, 4), new Object[] { new ItemStack(LOG_ERABLE) });
    GameRegistry.addShapelessRecipe(new ItemStack(PLANKS_JACARANDA, 4), new Object[] { new ItemStack(LOG_JACARANDA) });
    GameRegistry.addShapelessRecipe(new ItemStack(PLANKS_JUDEECERCIS, 4), new Object[] { new ItemStack(LOG_JUDEECERCIS) });
    GameRegistry.addShapelessRecipe(new ItemStack(PLANKS_OSTRYA, 4), new Object[] { new ItemStack(LOG_OSTRYA) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegister.PLANKS_DEAD_WOOD, 4), new Object[] { new ItemStack(BlocksRegister.DEAD_WOOD) });
  }
  
  private void addRecipesForFurnace() {
    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(AMETHYST_ORE), new ItemStack(ItemsRegister.AMETHYST_INGOT), 0.7F);
    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(TITANE_ORE), new ItemStack(ItemsRegister.TITANE_INGOT), 0.7F);
    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(PALADIUM_ORE), new ItemStack(ItemsRegister.PALADIUM_INGOT), 1.0F);
    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(PALADIUM_GREEN_ORE), new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT), 1.0F);
    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(GRANITE_BLOCK, 1, 0), new ItemStack(GRANITE_BLOCK, 1, 1), 0.1F);
    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(DIORITE_BLOCK, 1, 0), new ItemStack(DIORITE_BLOCK, 1, 1), 0.1F);
    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ANDESITE_BLOCK, 1, 0), new ItemStack(ANDESITE_BLOCK, 1, 1), 0.1F);
    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(BlocksRegister.INVOKER_ORE, 1, 0), new ItemStack(ItemsRegister.INVOKER_STONE, 1, 0), 1.0F);
  }
  
  public static void initBlocksLayers(boolean paladiumgreen_enabled, boolean endium_enabled, boolean doublepaladium_enabled) {
    PGuiOverlayDebug.clearBlockLayer();
    PGuiOverlayDebug.addBlockLayer(new PGuiOverlayDebug.DebugBlockLayer(Block.func_149729_e(16)));
    PGuiOverlayDebug.DebugBlockLayer blocklayer = new PGuiOverlayDebug.DebugBlockLayer(Block.func_149729_e(15));
    blocklayer.setLayer(2, 54);
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(Block.func_149729_e(21));
    blocklayer.setLayer(14, 25);
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(Block.func_149729_e(73));
    blocklayer.setLayer(2, 12);
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(Block.func_149729_e(129));
    blocklayer.setBiome(new int[] { 3, 162 });
    blocklayer.setLayer(2, 29);
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(Block.func_149729_e(14));
    blocklayer.setLayer(2, 29);
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(Block.func_149729_e(56));
    blocklayer.setLayer(2, 12);
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(Block.func_149729_e(153));
    blocklayer.setDimensions(new int[] { -1 });
    blocklayer.setLayer(15, 120);
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(AMETHYST_ORE);
    blocklayer.setLayer(2, 54);
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(TITANE_ORE);
    blocklayer.setLayer(2, 24);
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(PALADIUM_ORE);
    blocklayer.setLayer(2, 11);
    blocklayer.setPrefix(doublepaladium_enabled ? (EnumChatFormatting.LIGHT_PURPLE.toString() + EnumChatFormatting.BOLD.toString() + "EVENT X2 !! : " + EnumChatFormatting.RED.toString()) : EnumChatFormatting.RED.toString());
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(BlocksRegister.INVOKER_ORE);
    blocklayer.setLayer(2, 25);
    blocklayer.setPrefix(EnumChatFormatting.LIGHT_PURPLE.toString());
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(FINDIUM_ORE);
    blocklayer.setLayer(2, 25);
    blocklayer.setPrefix(EnumChatFormatting.YELLOW.toString());
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    blocklayer = new PGuiOverlayDebug.DebugBlockLayer(TRIXIUM_ORE);
    blocklayer.setLayer(2, 25);
    blocklayer.setPrefix(EnumChatFormatting.DARK_AQUA.toString());
    PGuiOverlayDebug.addBlockLayer(blocklayer);
    if (paladiumgreen_enabled) {
      blocklayer = new PGuiOverlayDebug.DebugBlockLayer(PALADIUM_GREEN_ORE);
      blocklayer.setLayer(2, 11);
      blocklayer.setPrefix(EnumChatFormatting.GREEN.toString());
      PGuiOverlayDebug.addBlockLayer(blocklayer);
    } 
    if (endium_enabled) {
      blocklayer = new PGuiOverlayDebug.DebugBlockLayer(BlocksRegister.ENDIUM_NUGGET_ORE);
      blocklayer.setLayer(2, 11);
      blocklayer.setPrefix(EnumChatFormatting.BLUE.toString());
      PGuiOverlayDebug.addBlockLayer(blocklayer);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\PWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */