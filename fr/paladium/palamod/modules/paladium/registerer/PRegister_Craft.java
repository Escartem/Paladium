package fr.paladium.palamod.modules.paladium.registerer;

import com.jaquadro.minecraft.storagedrawers.core.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import fr.paladium.mount.core.blocks.BlocksRegister;
import fr.paladium.mount.core.items.ItemsRegister;
import fr.paladium.palajobs.core.registry.ItemsRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.paladium.common.crafting.AlchemyCreatorArrowRecipies;
import fr.paladium.palamod.modules.paladium.common.crafting.AlchemyCreatorPotionRecipies;
import fr.paladium.palamod.modules.paladium.common.crafting.BowMachineRecipies;
import fr.paladium.palamod.modules.paladium.common.crafting.EndiumGauntletRecipe;
import fr.paladium.palamod.modules.paladium.common.crafting.PalaRecipe;
import fr.paladium.palamod.modules.paladium.common.crafting.PaladiumMachineRecipies;
import fr.paladium.palamod.modules.paladium.craft.SpikeUpgradeRecipe;
import fr.paladium.palamod.modules.smeltery.crafting.GrinderRecipe;
import fr.paladium.palarpg.common.api.ItemsRegister;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class PRegister_Craft {
  public static void init() {
    BowRecipies();
    AlchemyCreatorRecipies();
    craftingRecipies();
    registerPaladiumMachineRecipies();
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_HELMET), new Object[] { "XXX", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE), new Object[] { "X X", "XXX", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_LEGGINGS), new Object[] { "XXX", "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_BOOTS), new Object[] { "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_HELMET), new Object[] { "XXX", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.ENDIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_CHESTPLATE), new Object[] { "X X", "XXX", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.ENDIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_LEGGINGS), new Object[] { "XXX", "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.ENDIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_BOOTS), new Object[] { "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.ENDIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.MIXED_ENDIUM_INGOT, 8), new Object[] { "PPP", "PEP", "PPP", 
          Character.valueOf('P'), new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT), Character.valueOf('E'), new ItemStack(ItemsRegister.ENDIUM_FRAGMENT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.MIXED_ENDIUM_HELMET), new Object[] { "XXX", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.MIXED_ENDIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.MIXED_ENDIUM_CHESTPLATE), new Object[] { "X X", "XXX", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.MIXED_ENDIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.MIXED_ENDIUM_LEGGINGS), new Object[] { "XXX", "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.MIXED_ENDIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.MIXED_ENDIUM_BOOTS), new Object[] { "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.MIXED_ENDIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.OBSIDIAN_SLAB, 6), new Object[] { "XXX", 
          Character.valueOf('X'), new ItemStack(Blocks.field_150343_Z) });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.OBSIDIAN_HARDENED_SLAB, 3), new Object[] { "XXX", 
          Character.valueOf('X'), new ItemStack((Block)BlocksRegister.OBSIDIAN_SLAB) });
    GameRegistry.addShapelessRecipe(new ItemStack((Item)ItemsRegister.SEED_ICE, 4), new Object[] { new ItemStack((Block)BlocksRegister.CROPS_ICE, 1, 2) });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.PRINT_PRESS), new Object[] { 
          "IBI", "SPS", "ZZZ", 
          Character.valueOf('I'), Items.field_151042_j, Character.valueOf('B'), Items.field_151072_bj, Character.valueOf('S'), BlocksRegister.PALADIUM_SLAB, Character.valueOf('P'), 
          Blocks.field_150445_bS, Character.valueOf('Z'), Blocks.field_150339_S });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.TYPE_MACHINE), new Object[] { 
          "ICI", "SSS", "BRB", 
          Character.valueOf('I'), Items.field_151042_j, Character.valueOf('C'), ItemsRegister.CHASE, Character.valueOf('S'), Blocks.field_150376_bx, Character.valueOf('B'), 
          Blocks.field_150344_f, Character.valueOf('R'), ItemsRegister.FINDIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.CHASE), new Object[] { " S ", "SIS", " S ", Character.valueOf('S'), BlocksRegister.PALADIUM_SLAB, Character.valueOf('I'), Blocks.field_150339_S });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_INK, 4), new Object[] { "XXX", "XYX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('Y'), Items.field_151100_aR });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.FACTORY), new Object[] { "PSP", "SRS", "PSP", Character.valueOf('P'), Blocks.field_150347_e, 
          Character.valueOf('S'), Blocks.field_150348_b, Character.valueOf('R'), Items.field_151137_ax });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.FUTURA), new Object[] { "SSS", "SRS", "SSS", Character.valueOf('S'), Blocks.field_150348_b, 
          Character.valueOf('R'), Blocks.field_150451_bX });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.ANVIL_AMETHYST), new Object[] { "BBB", " I ", "III", Character.valueOf('B'), BlocksRegister.BLOCK_AMETHYST, 
          Character.valueOf('I'), ItemsRegister.AMETHYST_INGOT });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.ANVIL_TITANE), new Object[] { "BBB", " I ", "III", Character.valueOf('B'), BlocksRegister.BLOCK_TITANE, 
          Character.valueOf('I'), ItemsRegister.TITANE_INGOT });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.ANVIL_PALADIUM), new Object[] { "BBB", " I ", "III", Character.valueOf('B'), BlocksRegister.BLOCK_PALADIUM, 
          Character.valueOf('I'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack(ItemsRegistry.BAMBOO_BOAT), new Object[] { "X X", "XXX", Character.valueOf('X'), BlocksRegister.BAMBOO_BLOCK });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PISTOL_TANK, 1), new Object[] { " TQ", "QPQ", " Q ", 
          
          Character.valueOf('P'), BlocksRegister.BLOCK_PALADIUM, 
          Character.valueOf('Q'), Blocks.field_150371_ca, 
          Character.valueOf('T'), BlocksRegister.TANK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_KEY, 1), new Object[] { " P ", "PKP", " P ", 
          Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('K'), ModItems.personalKey });
    GameRegistry.addRecipe(new ItemStack(ItemsRegistry.TITANE_RADIUS_HOE, 1), new Object[] { "PP ", " K ", " K ", 
          Character.valueOf('P'), ItemsRegister.TITANE_INGOT, Character.valueOf('K'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(ItemsRegistry.AMETHYST_RADIUS_HOE, 1), new Object[] { "PP ", " K ", " K ", 
          Character.valueOf('P'), ItemsRegister.AMETHYST_INGOT, Character.valueOf('K'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(ItemsRegistry.PALADIUM_RADIUS_HOE, 1), new Object[] { "PP ", " K ", " K ", 
          Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('K'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(ItemsRegistry.GREEN_PALADIUM_RADIUS_HOE, 1), new Object[] { "PP ", " K ", " K ", 
          Character.valueOf('P'), ItemsRegister.PALADIUM_GREEN_INGOT, Character.valueOf('K'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(ItemsRegistry.ENDIUM_RADIUS_HOE, 1), new Object[] { "PP ", " K ", " K ", 
          Character.valueOf('P'), ItemsRegister.ENDIUM_NUGGET, Character.valueOf('K'), Items.field_151055_y });
  }
  
  private static void BowRecipies() {
    BowMachineRecipies.instance.addRecipie((Item)ItemsRegister.BOWUPGRADE_RANGEMODIFIER, 10);
    BowMachineRecipies.instance.addRecipie((Item)ItemsRegister.BOWUPGRADE_SPEEDMODIFIER, 11);
  }
  
  private static void AlchemyCreatorRecipies() {
    AlchemyCreatorPotionRecipies.getManager().add(new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151069_bo, 1, 0), new ItemStack((Item)ItemsRegister.POTION_POSION));
    AlchemyCreatorPotionRecipies.getManager().add(new ItemStack(Items.field_151065_br), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151059_bz), new ItemStack(Items.field_151069_bo, 1, 0), new ItemStack((Item)ItemsRegister.POTION_FIRE));
    AlchemyCreatorPotionRecipies.getManager().add(new ItemStack(ItemsRegister.WITHER_SKULLFRAGMENT), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151069_bo, 1, 0), new ItemStack((Item)ItemsRegister.POTION_WITHER));
    AlchemyCreatorPotionRecipies.getManager().add(new ItemStack(Items.field_151065_br, 1, 0), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151069_bo, 1, 0), new ItemStack((Item)ItemsRegister.POTION_SICKNESS, 1, 0));
    AlchemyCreatorPotionRecipies.getManager().add(new ItemStack(Items.field_151065_br), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151069_bo, 1, 0), new ItemStack((Item)ItemsRegister.POTION_SICKNESS, 1, 1));
    AlchemyCreatorArrowRecipies.getManager().add(new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151032_g, 16), new ItemStack((Item)ItemsRegister.ARROW_POISON, 16));
    AlchemyCreatorArrowRecipies.getManager().add(new ItemStack(ItemsRegister.WITHER_SKULLFRAGMENT), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151032_g, 16), new ItemStack((Item)ItemsRegister.ARROW_WITHER, 16));
    AlchemyCreatorArrowRecipies.getManager().add(new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151032_g, 16), new ItemStack((Item)ItemsRegister.ARROW_SLOWNESS, 16));
    AlchemyCreatorArrowRecipies.getManager().add(new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151032_g, 16), new ItemStack((Item)ItemsRegister.ARROW_SWITCH, 16));
    AlchemyCreatorArrowRecipies.getManager().add(new ItemStack(Blocks.field_150433_aE), new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151123_aH), new ItemStack(Items.field_151032_g, 16), new ItemStack((Item)ItemsRegister.ARROW_FROST, 16));
  }
  
  public static void craftingRecipies() {
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COMPRESSED_ENDIUM), new Object[] { "XYX", "YYY", "XYX", 
          Character.valueOf('Y'), BlocksRegister.BLOCK_PALADIUM, Character.valueOf('X'), new ItemStack(ItemsRegister.ENDIUM_FRAGMENT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new Object[] { "XXX", "XYX", "XXX", 
          Character.valueOf('Y'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('X'), new ItemStack(BlocksRegister.BLOCK_PALADIUM) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COMPRESSED_TITANE), new Object[] { "XXX", "XYX", "XXX", 
          Character.valueOf('Y'), ItemsRegister.TITANE_INGOT, Character.valueOf('X'), new ItemStack(BlocksRegister.BLOCK_TITANE) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COMPRESSED_AMETHYST), new Object[] { "XXX", "XYX", "XXX", 
          Character.valueOf('Y'), ItemsRegister.AMETHYST_INGOT, Character.valueOf('X'), new ItemStack(BlocksRegister.BLOCK_AMETHYST) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.STICK_PALADIUM, 4), new Object[] { "X", "X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.TRAVEL_LEGGINGS, 1), new Object[] { "XXX", "Y Y", "Z Z", 
          Character.valueOf('X'), new ItemStack(Items.field_151116_aA), Character.valueOf('Y'), new ItemStack(Items.field_151008_G), Character.valueOf('Z'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.TRAVEL_BOOTS, 1), new Object[] { "X X", "Y Y", Character.valueOf('X'), new ItemStack(Items.field_151008_G), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_APPLE, 1), new Object[] { "XXX", "XYX", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151034_e) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.INFERNAL_KNOCKER, 1), new Object[] { "X", "Z", "Y", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.ORB_KNOCKBACK), Character.valueOf('Y'), new ItemStack(ItemsRegister.STICK_PALADIUM) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_PICKAXE, 1), new Object[] { "XXX", " Z ", " Z ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_NUGGET), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.ENDIUM_FRAGMENT });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.ENDIUM_FRAGMENT, 9), new Object[] { new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_PICKAXE, 1), new Object[] { "XXX", " Z ", " Z ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.ENDIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_PICKAXE, 1), new Object[] { "XXX", " Y ", " Y ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_AXE, 1), new Object[] { " XX", " ZX", " Z ", Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_AXE, 1), new Object[] { "XX ", "XZ ", " Z ", Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_AXE, 1), new Object[] { " XX", " ZX", " Z ", Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_AXE, 1), new Object[] { "XX ", "XZ ", " Z ", Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_AXE, 1), new Object[] { "XX ", "XZ ", " Z ", Character.valueOf('X'), new ItemStack(ItemsRegister.ENDIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_SWORD, 1), new Object[] { "X", "X", "Y", Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_SWORD, 1), new Object[] { "X", "X", "Y", Character.valueOf('X'), new ItemStack(ItemsRegister.ENDIUM_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_SWORD, 1), new Object[] { "X", "X", "Y", Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_SHOVEL, 1), new Object[] { "X", "Z", "Z", Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_SHOVEL, 1), new Object[] { "X", "Z", "Z", Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.STICK_TITANE, 1), new Object[] { "X", "X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.STICK_AMETHYST, 1), new Object[] { "X", "X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.DIAMOND_STRING, 9), new Object[] { "XYX", "XYX", "XYX", Character.valueOf('X'), new ItemStack(Items.field_151007_F), Character.valueOf('Y'), new ItemStack(Items.field_151045_i) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.PALADIUM_BOW, 1), new Object[] { " XY", "X Y", " XY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.STICK_PALADIUM), Character.valueOf('Y'), new ItemStack(ItemsRegister.DIAMOND_STRING) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_HELMET), new Object[] { "XXX", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_CHESTPLATE), new Object[] { "X X", "XXX", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_LEGGINGS), new Object[] { "XXX", "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_BOOTS), new Object[] { "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_HELMET), new Object[] { "XXX", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_CHESTPLATE), new Object[] { "X X", "XXX", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_LEGGINGS), new Object[] { "XXX", "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_BOOTS), new Object[] { "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.TRAVEL_JUMPCHEST, 1), new Object[] { "X X", "YZY", "YZY", 
          Character.valueOf('X'), new ItemStack(Items.field_151008_G), Character.valueOf('Z'), new ItemStack(Items.field_151042_j), Character.valueOf('Y'), new ItemStack(Items.field_151116_aA) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.TRAVEL_SLIMYHELMET, 1), new Object[] { "XXX", "Y Y", Character.valueOf('X'), new ItemStack(Items.field_151123_aH), Character.valueOf('Y'), new ItemStack(Items.field_151045_i) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.TRAVEL_SCUBAHELMET, 1), new Object[] { "XXX", "XZX", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Z'), new ItemStack(Blocks.field_150410_aZ) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.TRAVEL_HOODHELMET, 1), new Object[] { "XXX", "XZX", "XXX", 
          Character.valueOf('X'), new ItemStack(Blocks.field_150325_L), Character.valueOf('Z'), new ItemStack(ItemsRegister.PALADIUM_CORE) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.WING, 2), new Object[] { "  X", " XZ", "XZZ", Character.valueOf('X'), new ItemStack(ItemsRegister.STICK_PALADIUM), Character.valueOf('Z'), new ItemStack(Items.field_151116_aA) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.HANGGLIDER, 1), new Object[] { " Y ", "XYX", Character.valueOf('X'), new ItemStack(ItemsRegister.WING), Character.valueOf('Y'), new ItemStack(Items.field_151116_aA) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.VOIDSTONE, 1), new Object[] { " X ", "XZX", " X ", Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT), Character.valueOf('Z'), new ItemStack((Block)Blocks.field_150486_ae) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.CHESTEXPLORER, 1), new Object[] { "XYX", "XZX", "XYX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.FINDIUM), Character.valueOf('Z'), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), Character.valueOf('Y'), new ItemStack(ItemsRegister.PALADIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.BACKPACK, 1), new Object[] { "XXX", "YZY", "YYY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.STICK_PALADIUM), Character.valueOf('Z'), new ItemStack((Block)Blocks.field_150486_ae), Character.valueOf('Y'), new ItemStack(Items.field_151116_aA) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ORB_HEAL, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151060_bw) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ORB_STRENGHT, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151065_br) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ORB_JUMP, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151075_bm) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ORB_HEAL, 1), new Object[] { " X ", "XZX", " X ", Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151122_aG) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.POTION_LAUNCHER, 1), new Object[] { "XXX", "ZYY", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.PALADIUM_CORE), Character.valueOf('Y'), new ItemStack(Items.field_151069_bo) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.BOWUPGRADE_EMPTY, 1), new Object[] { " X ", "XYX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.STICK_PALADIUM), Character.valueOf('Y'), new ItemStack(Items.field_151121_aF) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_CORE, 1), new Object[] { "YXY", "XZX", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.PALADIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ORB_KNOCKBACK, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151073_bk) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_AXE, 1), new Object[] { " XX", " YX", " Y ", Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_AXE, 1), new Object[] { "XX ", "XY ", " Y ", Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_PICKAXE, 1), new Object[] { "XXX", " Y ", " Y ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_SHOVEL, 1), new Object[] { "X", "Y", "Y", Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_SWORD, 1), new Object[] { "X", "X", "Y", Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.BUCKET_SULFURIC, 1), new Object[] { "Y", "X", Character.valueOf('X'), new ItemStack(Items.field_151131_as
            .func_77642_a(Item.func_150898_a(Blocks.field_150350_a))), Character.valueOf('Y'), new ItemStack((Item)Items.field_151068_bn, 1, 8268), 
          Character.valueOf('Y'), new ItemStack(ItemsRegister.STICK_PALADIUM) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.BUCKET_ANGELIC, 1), new Object[] { "Y", "X", Character.valueOf('X'), new ItemStack(Items.field_151131_as), Character.valueOf('Y'), new ItemStack((Item)Items.field_151068_bn, 1, 8193) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ORB_SPEED, 1), new Object[] { " X ", "XZX", " X ", Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151102_aT) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.WITHER_SKULLFRAGMENT, 9), new Object[] { new ItemStack(Items.field_151144_bL, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.ORB_CHAOS, 8), new Object[] { new ItemStack(ItemsRegister.LEGENDARYSTONE_CHAOS, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.TITANE_INGOT, 9), new Object[] { new ItemStack(BlocksRegister.BLOCK_TITANE, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.PALADIUM_INGOT, 9), new Object[] { new ItemStack(BlocksRegister.BLOCK_PALADIUM, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.AMETHYST_INGOT, 9), new Object[] { new ItemStack(BlocksRegister.BLOCK_AMETHYST, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Item)ItemsRegister.BOWUPGRADE_SPEEDMODIFIER, 1), new Object[] { new ItemStack(ItemsRegister.BOWUPGRADE_EMPTY, 1), new ItemStack((Item)Items.field_151068_bn, 1, 8258) });
    GameRegistry.addShapelessRecipe(new ItemStack((Item)ItemsRegister.BOWUPGRADE_RANGEMODIFIER, 1), new Object[] { new ItemStack(ItemsRegister.BOWUPGRADE_EMPTY, 1), new ItemStack((Item)Items.field_151068_bn, 1, 8201) });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.BOW_MACHINE_BLOCK), new Object[] { "XXX", "XYX", "XXX", 
          Character.valueOf('Y'), ItemsRegister.COMPRESSED_PALADIUM, Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.ALCHEMY_CREATOR_BLOCK), new Object[] { 
          "YUY", "XZX", "XXX", 
          Character.valueOf('Y'), Items.field_151065_br, Character.valueOf('U'), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), Character.valueOf('X'), ItemsRegister.TITANE_INGOT, Character.valueOf('Z'), 
          ItemsRegister.PALADIUM_CORE });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.ONLINE_DETECTOR_BLOCK), new Object[] { 
          "YYY", "YXY", "ZZZ", 
          Character.valueOf('Y'), Items.field_151100_aR, Character.valueOf('U'), new ItemStack(ItemsRegister.WITHER_SKULLFRAGMENT), Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('Z'), 
          ItemsRegister.TITANE_INGOT });
    GameRegistry.addRecipe(new ItemStack(Items.field_151144_bL, 1, 1), new Object[] { "YYY", "YYY", "YYY", 
          Character.valueOf('Y'), ItemsRegister.WITHER_SKULLFRAGMENT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.SPIKE_IRON), new Object[] { " Y ", "YXY", "XXX", 
          Character.valueOf('Y'), Items.field_151041_m, Character.valueOf('X'), Items.field_151042_j });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.SPIKE_AMETHYST), new Object[] { " Y ", "YXY", "XXX", Character.valueOf('Y'), Items.field_151048_u, Character.valueOf('X'), ItemsRegister.AMETHYST_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.SPIKE_DIAMOND), new Object[] { " Y ", "YXY", "XXX", 
          Character.valueOf('Y'), Items.field_151010_B, Character.valueOf('X'), Items.field_151045_i });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.SPIKE_GOLD), new Object[] { " Y ", "YXY", "XXX", 
          Character.valueOf('Y'), Items.field_151040_l, Character.valueOf('X'), Items.field_151043_k });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM), new Object[] { " Y ", "YXY", "XXX", Character.valueOf('Y'), ItemsRegister.TITANE_SWORD, Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.SPIKE_TITANE), new Object[] { " Y ", "YXY", "XXX", Character.valueOf('Y'), ItemsRegister.AMETHYST_SWORD, Character.valueOf('X'), ItemsRegister.TITANE_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.SPIKE_WOOD), new Object[] { " Y ", "YXY", "XXX", 
          Character.valueOf('Y'), Items.field_151041_m, Character.valueOf('X'), Blocks.field_150344_f });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.SLIMEPAD_BLOCK), new Object[] { "XXX", "XXX", 
          Character.valueOf('X'), Items.field_151123_aH });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.CAVE_BLOCK), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150359_w), Character.valueOf('Y'), new ItemStack(ItemsRegister.FINDIUM) });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.Totem), new Object[] { 
          "EYE", "UEU", "EJE", 
          Character.valueOf('E'), new ItemStack(Blocks.field_150364_r), Character.valueOf('Y'), new ItemStack(Items.field_151144_bL, 1, 4), Character.valueOf('U'), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM, 2), Character.valueOf('J'), 
          new ItemStack(Items.field_151100_aR, 64, 15) });
    GameRegistry.addRecipe((IRecipe)new SpikeUpgradeRecipe(new ItemStack((Block)BlocksRegister.SPIKE_IRON), new ItemStack(Items.field_151042_j)));
    GameRegistry.addRecipe((IRecipe)new SpikeUpgradeRecipe(new ItemStack((Block)BlocksRegister.SPIKE_GOLD), new ItemStack(Items.field_151043_k)));
    GameRegistry.addRecipe((IRecipe)new SpikeUpgradeRecipe(new ItemStack((Block)BlocksRegister.SPIKE_DIAMOND), new ItemStack(Items.field_151045_i)));
    GameRegistry.addRecipe((IRecipe)new SpikeUpgradeRecipe(new ItemStack((Block)BlocksRegister.SPIKE_TITANE), new ItemStack(ItemsRegister.TITANE_INGOT)));
    GameRegistry.addRecipe((IRecipe)new SpikeUpgradeRecipe(new ItemStack((Block)BlocksRegister.SPIKE_AMETHYST), new ItemStack(ItemsRegister.AMETHYST_INGOT)));
    GameRegistry.addRecipe((IRecipe)new SpikeUpgradeRecipe(new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_INGOT)));
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.FURNACE_UPGRADE), new Object[] { "CS ", "   ", "   ", 
          Character.valueOf('C'), Items.field_151044_h, Character.valueOf('S'), ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.GOLDMIXEDCOAL, 6), new Object[] { "SC", 
          Character.valueOf('C'), Items.field_151044_h, Character.valueOf('S'), Items.field_151043_k });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYSTMIXEDCOAL, 8), new Object[] { " C ", "CSC", " C ", Character.valueOf('C'), ItemsRegister.AMETHYST_INGOT, Character.valueOf('S'), ItemsRegister.GOLDMIXEDCOAL });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANEMIXEDCOAL, 8), new Object[] { " C ", "CSC", " C ", Character.valueOf('C'), ItemsRegister.TITANE_INGOT, Character.valueOf('S'), ItemsRegister.GOLDMIXEDCOAL });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALAMIXEDCOAL, 1), new Object[] { "TCT", "CSC", " C ", 
          Character.valueOf('C'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('S'), ItemsRegister.GOLDMIXEDCOAL, Character.valueOf('T'), ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.AMETHYST_CHEST), new Object[] { "XSX", "SCS", "XSX", 
          Character.valueOf('X'), ItemsRegister.AMETHYST_INGOT, Character.valueOf('S'), ItemsRegister.COMPRESSED_AMETHYST, Character.valueOf('C'), Blocks.field_150486_ae });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.TITANE_CHEST), new Object[] { "XSX", "SCS", "XSX", 
          Character.valueOf('X'), ItemsRegister.TITANE_INGOT, Character.valueOf('S'), ItemsRegister.COMPRESSED_TITANE, Character.valueOf('C'), BlocksRegister.AMETHYST_CHEST });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.PALADIUM_CHEST), new Object[] { "XSX", "SCS", "XSX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('S'), ItemsRegister.COMPRESSED_PALADIUM, Character.valueOf('C'), BlocksRegister.TITANE_CHEST });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.GPALADIUM_CHEST), new Object[] { "XSX", "SCS", "XSX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT, Character.valueOf('S'), ItemsRegister.COMPRESSED_PALADIUM, Character.valueOf('C'), BlocksRegister.TITANE_CHEST });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.ENDIUM_CHEST), new Object[] { "EEE", "CPC", "CCC", 
          Character.valueOf('E'), ItemsRegister.ENDIUM_INGOT, Character.valueOf('C'), ItemsRegister.COMPRESSED_PALADIUM, Character.valueOf('P'), BlocksRegister.PALADIUM_CHEST });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.CRUSHER), new Object[] { 
          "XSX", "TFT", "XTX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_CORE, Character.valueOf('S'), ItemsRegister.COMPRESSED_AMETHYST, Character.valueOf('T'), ItemsRegister.STICK_PALADIUM, Character.valueOf('F'), 
          ItemsRegister.FINDIUM });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.FORGE), new Object[] { "BFB", "FXF", "BFB", Character.valueOf('X'), ItemsRegister.FINDIUM, Character.valueOf('B'), Blocks.field_150336_V, Character.valueOf('F'), BlocksRegister.PALADIUM_FURNACE });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.PALADIUM_MACHINE_BLOCK), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), Blocks.field_150348_b, Character.valueOf('Y'), ItemsRegister.COMPRESSED_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.PALADIUM_FURNACE), new Object[] { "XXX", "X X", "XXX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BLOCK_FINDIUM), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.FINDIUM });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BLOCK_PALADIUM), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BLOCK_TITANE), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.TITANE_INGOT });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BLOCK_AMETHYST), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.AMETHYST_INGOT });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BLOCK_GPALADIUM), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BLOCK_TRIXIUM), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.TRIXIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_INGOT), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.ENDIUM_NUGGET });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_BOOTS), new Object[] { "X X", "X X", "   ", 
          Character.valueOf('X'), ItemsRegister.TITANE_INGOT });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_PICKAXE), new Object[] { "XXX", " S ", " S ", Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT, Character.valueOf('S'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_SHOVEL), new Object[] { " X ", " S ", " S ", Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT, Character.valueOf('S'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_AXE), new Object[] { "XX ", "XS ", " S ", Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT, Character.valueOf('S'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_AXE), new Object[] { " XX", " SX", " S ", Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT, Character.valueOf('S'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_SWORD), new Object[] { " X ", " X ", " S ", Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT, Character.valueOf('S'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_CHESTPLATE), new Object[] { "X X", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_HELMET), new Object[] { "XXX", "X X", "   ", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_LEGGINGS), new Object[] { "XXX", "X X", "X X", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_BOOTS), new Object[] { "   ", "X X", "X X", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_GREEN_INGOT });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.DYNAMITE, 4), new Object[] { "S  ", "X  ", "X  ", 
          Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('S'), ItemsRegister.DIAMOND_STRING });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.DYNAMITE_BIG), new Object[] { "XXX", "   ", "   ", 
          Character.valueOf('X'), ItemsRegister.DYNAMITE });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.DYNAMITE_NINJA), new Object[] { "XDX", "XDX", "XDX", Character.valueOf('X'), ItemsRegister.TITANE_INGOT, Character.valueOf('D'), ItemsRegister.DYNAMITE });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.FINDIUM, 9), new Object[] { new ItemStack(BlocksRegister.BLOCK_FINDIUM, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.TRIXIUM, 9), new Object[] { new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 1) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL1), new Object[] { "AAA", "ASA", "TTT", 
          Character.valueOf('A'), ItemsRegister.AMETHYST_INGOT, Character.valueOf('S'), ItemsRegister.COMPRESSED_AMETHYST, Character.valueOf('T'), ItemsRegister.STICK_AMETHYST });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL2), new Object[] { 
          "THT", "TGT", "SSS", 
          Character.valueOf('T'), ItemsRegister.TITANE_INGOT, Character.valueOf('H'), ItemsRegister.COMPRESSED_TITANE, Character.valueOf('G'), ItemsRegister.SEEDPLANTER_LVL1, Character.valueOf('S'), 
          ItemsRegister.STICK_TITANE });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL3), new Object[] { 
          "THT", "TGT", "SSS", 
          Character.valueOf('T'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('H'), ItemsRegister.COMPRESSED_PALADIUM, Character.valueOf('G'), ItemsRegister.SEEDPLANTER_LVL2, Character.valueOf('S'), 
          ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL4), new Object[] { 
          "RBR", "EWE", "SSS", 
          Character.valueOf('B'), BlocksRegister.BLOCK_GPALADIUM, Character.valueOf('R'), ItemsRegister.COMPRESSED_PALADIUM, Character.valueOf('E'), ItemsRegister.ENDIUM_INGOT, Character.valueOf('W'), 
          ItemsRegister.SEEDPLANTER_LVL5, Character.valueOf('S'), ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.SEEDPLANTER_LVL5), new Object[] { 
          "PBP", "PWP", "SSS", 
          Character.valueOf('B'), BlocksRegister.BLOCK_GPALADIUM, Character.valueOf('P'), ItemsRegister.PALADIUM_GREEN_INGOT, Character.valueOf('W'), ItemsRegister.SEEDPLANTER_LVL3, Character.valueOf('S'), 
          ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.OBSIDIAN_ITEM_DOOR), new Object[] { "XX ", "XX ", "XX ", 
          Character.valueOf('X'), Blocks.field_150343_Z });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.OBSIDIAN_TRAP_DOOR, 2), new Object[] { "XXX", "XXX", 
          Character.valueOf('X'), Blocks.field_150343_Z });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.AMETHYST_SLAB, 6), new Object[] { "XXX", 
          Character.valueOf('X'), ItemsRegister.AMETHYST_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.AMETHYST_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 
          Character.valueOf('X'), ItemsRegister.AMETHYST_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.TITANE_SLAB, 6), new Object[] { "XXX", 
          Character.valueOf('X'), ItemsRegister.TITANE_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.TITANE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 
          Character.valueOf('X'), ItemsRegister.TITANE_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.PALADIUM_SLAB, 6), new Object[] { "XXX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.PALADIUM_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.FINDIUM_SLAB, 6), new Object[] { "XXX", 
          Character.valueOf('X'), ItemsRegister.FINDIUM });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.FINDIUM_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 
          Character.valueOf('X'), ItemsRegister.FINDIUM });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.VOIDSTONE_MINAGE, 1), new Object[] { 
          " F ", "XDX", " C ", 
          Character.valueOf('X'), ItemsRegister.TITANE_INGOT, Character.valueOf('F'), Items.field_151079_bi, Character.valueOf('D'), ItemsRegister.VOIDSTONE, Character.valueOf('C'), 
          BlocksRegister.TITANE_CHEST });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_INGOT), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PARTICLE_AMETHYST });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_INGOT), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PARTICLE_TITANE });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_INGOT), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PARTICLE_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(Items.field_151042_j), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PARTICLE_IRON });
    GameRegistry.addRecipe(new ItemStack(Items.field_151043_k), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PARTICLE_GOLD });
    GameRegistry.addRecipe(new ItemStack(Items.field_151045_i), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.PARTICLE_DIAMOND });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.COMPRESSED_XP_BERRY), new Object[] { "XXX", "XXX", "XXX", 
          Character.valueOf('X'), ItemsRegister.XP_BERRY });
    GameRegistry.addShapelessRecipe(new ItemStack((Item)ItemsRegister.XP_BERRY, 9), new Object[] { new ItemStack((Item)ItemsRegister.COMPRESSED_XP_BERRY) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.PARTICLE_AMETHYST, 9), new Object[] { new ItemStack(ItemsRegister.AMETHYST_INGOT) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.PARTICLE_TITANE, 9), new Object[] { new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.PARTICLE_PALADIUM, 9), new Object[] { new ItemStack(ItemsRegister.PALADIUM_INGOT) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.PARTICLE_IRON, 9), new Object[] { new ItemStack(Items.field_151042_j) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.PARTICLE_GOLD, 9), new Object[] { new ItemStack(Items.field_151043_k) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.PARTICLE_DIAMOND, 9), new Object[] { new ItemStack(Items.field_151045_i) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)Blocks.field_150354_m, 4, 0), new Object[] { new ItemStack(Blocks.field_150322_A, 1, 0) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.MAGICAL_TOOL), new Object[] { " X ", "XYX", " Z ", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('Y'), BlocksRegister.BLOCK_PALADIUM, Character.valueOf('Z'), ItemsRegister.STICK_PALADIUM });
    Item[] infiniteStones = { ItemsRegister.LEGENDARYSTONE_CHAOS, ItemsRegister.LEGENDARYSTONE_FORTUNE, ItemsRegister.LEGENDARYSTONE_INVISIBILITY, ItemsRegister.LEGENDARYSTONE_JOBS, ItemsRegister.LEGENDARYSTONE_POWER, ItemsRegister.LEGENDARYSTONE_TELEPORTATION };
    for (Item stone : infiniteStones) {
      GameRegistry.addRecipe((IRecipe)EndiumGauntletRecipe.toRecipe(new ItemStack(ItemsRegister.ENDIUM_GAUNTLET), new Object[] { " Y ", "XZX", " Y ", Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE, Character.valueOf('X'), ItemsRegister.ENDIUM_INGOT, Character.valueOf('Z'), stone }));
    } 
    GrinderRecipe.getManager().addUpgrade(ItemsRegister.ENDIUM_GAUNTLET, ItemsRegister.LEGENDARYSTONE_INVISIBILITY, 9);
    GrinderRecipe.getManager().addUpgrade(ItemsRegister.ENDIUM_GAUNTLET, ItemsRegister.LEGENDARYSTONE_CHAOS, 11);
    GrinderRecipe.getManager().addUpgrade(ItemsRegister.ENDIUM_GAUNTLET, ItemsRegister.LEGENDARYSTONE_POWER, 12);
    GrinderRecipe.getManager().addUpgrade(ItemsRegister.ENDIUM_GAUNTLET, ItemsRegister.LEGENDARYSTONE_TELEPORTATION, 13);
    GrinderRecipe.getManager().addUpgrade(ItemsRegister.ENDIUM_GAUNTLET, ItemsRegister.LEGENDARYSTONE_JOBS, 14);
    GrinderRecipe.getManager().addUpgrade(ItemsRegister.ENDIUM_GAUNTLET, ItemsRegister.LEGENDARYSTONE_FORTUNE, 10);
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 4), new Object[] { Blocks.field_150343_Z, Blocks.field_150343_Z, Blocks.field_150343_Z, Blocks.field_150343_Z });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.OBSIDIAN_STAIRS, 3), new Object[] { "X  ", "XX ", "XXX", 
          Character.valueOf('X'), Blocks.field_150343_Z });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.OBSIDIAN_STAIRS, 3), new Object[] { "  X", " XX", "XXX", 
          Character.valueOf('X'), Blocks.field_150343_Z });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.OBSIDIAN_HARDENED_STAIRS, 3), new Object[] { "X  ", "XX ", "XXX", 
          Character.valueOf('X'), BlocksRegister.OBSIDIAN_STAIRS });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.OBSIDIAN_HARDENED_STAIRS, 3), new Object[] { "  X", " XX", "XXX", 
          Character.valueOf('X'), BlocksRegister.OBSIDIAN_STAIRS });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.EXTRAPOLATED_BUCKET), new Object[] { " Y ", "X X", " X ", Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE, Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.LAVA_SPONGE), new Object[] { "XYX", "YZY", "XYX", Character.valueOf('X'), Items.field_151129_at, 
          Character.valueOf('Y'), Blocks.field_150425_aM, Character.valueOf('Z'), ModBlocks.sponge });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.WITHER_SKULLFRAGMENT), new Object[] { "XXX", "XYX", "XXX", 
          Character.valueOf('X'), ItemsRegister.WITHER_DUST, Character.valueOf('Y'), Items.field_151144_bL });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT, 9), new Object[] { BlocksRegister.BLOCK_GPALADIUM });
    GameRegistry.addRecipe(new ItemStack(Blocks.field_150343_Z, 4), new Object[] { "XX ", "XX ", "   ", Character.valueOf('X'), BlocksRegister.HARDENED_OBSIDIAN });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BARN_FOUNDATION), new Object[] { "XXX", "XPX", "XXX", 
          
          Character.valueOf('X'), new ItemStack(Blocks.field_150406_ce, 1, 7), 
          Character.valueOf('P'), BlocksRegister.BLOCK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BARN_FENCE), new Object[] { "XPX", "XPX", "   ", 
          
          Character.valueOf('X'), new ItemStack(Blocks.field_150406_ce, 1, 7), 
          Character.valueOf('P'), BlocksRegister.BLOCK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.INCUBATOR), new Object[] { "XXX", "GEG", "XXX", 
          
          Character.valueOf('X'), new ItemStack(Blocks.field_150406_ce, 1, 7), 
          Character.valueOf('G'), Blocks.field_150359_w, 
          Character.valueOf('E'), ItemsRegister.ORB_HEAL });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BARN_CONTROLLER), new Object[] { "XXX", "PCP", "XXX", 
          
          Character.valueOf('X'), new ItemStack(Blocks.field_150406_ce, 1, 7), 
          Character.valueOf('P'), BlocksRegister.BLOCK_PALADIUM, 
          Character.valueOf('C'), new ItemStack((Block)IronChest.ironChestBlock, 1, IronChestType.GOLD.ordinal()) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.BANDAGE), new Object[] { "PSP", "PEP", "PSP", 
          
          Character.valueOf('P'), Items.field_151121_aF, 
          Character.valueOf('S'), Items.field_151007_F, 
          Character.valueOf('E'), ItemsRegister.ORB_HEAL });
  }
  
  private static void addpalrecipe(ItemStack p_92103_1_, Object... p_92103_2_) {
    String s = "";
    int i = 0;
    int j = 0;
    int k = 0;
    if (p_92103_2_[i] instanceof String[]) {
      String[] astring = (String[])p_92103_2_[i++];
      for (String s1 : astring) {
        k++;
        j = s1.length();
        s = s + s1;
      } 
    } else {
      while (p_92103_2_[i] instanceof String) {
        String s2 = (String)p_92103_2_[i++];
        k++;
        j = s2.length();
        s = s + s2;
      } 
    } 
    HashMap<Object, Object> hashmap;
    for (hashmap = new HashMap<>(); i < p_92103_2_.length; i += 2) {
      Character character = (Character)p_92103_2_[i];
      ItemStack itemstack1 = null;
      if (p_92103_2_[i + 1] instanceof Item) {
        itemstack1 = new ItemStack((Item)p_92103_2_[i + 1]);
      } else if (p_92103_2_[i + 1] instanceof Block) {
        itemstack1 = new ItemStack((Block)p_92103_2_[i + 1], 1, 32767);
      } else if (p_92103_2_[i + 1] instanceof ItemStack) {
        itemstack1 = (ItemStack)p_92103_2_[i + 1];
      } 
      hashmap.put(character, itemstack1);
    } 
    ItemStack[] aitemstack = new ItemStack[j * k];
    for (int i1 = 0; i1 < j * k; i1++) {
      char c0 = s.charAt(i1);
      if (hashmap.containsKey(Character.valueOf(c0))) {
        aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).func_77946_l();
      } else {
        aitemstack[i1] = null;
      } 
    } 
    PalaRecipe shapedrecipes = new PalaRecipe(j, k, aitemstack, p_92103_1_);
    CraftingManager.func_77594_a().func_77592_b().add(shapedrecipes);
  }
  
  private static void registerPaladiumMachineRecipies() {
    PaladiumMachineRecipies.add(new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.SMALL_RING, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.SMALL_RING), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.MEDIUM_RING, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.MEDIUM_RING), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.BIG_RING, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.MEDIUM_RING), new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), new ItemStack(ItemsRegister.RING_ENDIUM_SMALL, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.RING_ENDIUM_SMALL), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_FINDIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.ENDIUM_NUGGET), new ItemStack(ItemsRegister.RING_ENDIUM_MEDIUM, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.RING_ENDIUM_MEDIUM), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.ENDIUM_NUGGET), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.ENDIUM_NUGGET), new ItemStack(ItemsRegister.RING_ENDIUM_BIG, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.SMALL_RING), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.SMALL_RING, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.MEDIUM_RING), new ItemStack(ItemsRegister.PARTICLE_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PARTICLE_PALADIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.MEDIUM_RING, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.BIG_RING), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.BIG_RING, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.RING_ENDIUM_SMALL), new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.ENDIUM_FRAGMENT), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.RING_ENDIUM_SMALL, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.RING_ENDIUM_MEDIUM), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.COMPRESSED_ENDIUM), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.RING_ENDIUM_MEDIUM, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.RING_ENDIUM_BIG), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.COMPRESSED_ENDIUM), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), new ItemStack(ItemsRegister.COMPRESSED_ENDIUM), new ItemStack(ItemsRegister.RING_ENDIUM_BIG, 1));
    PaladiumMachineRecipies.add(new ItemStack((Block)BlocksRegister.EXTRACTOR), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(Items.field_151131_as), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack((Block)BlocksRegister.EXTRACTOR, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.ORB_SPEED), new ItemStack(ItemsRegister.STICK_AMETHYST), new ItemStack(ItemsRegister.COMPRESSED_AMETHYST), new ItemStack(ItemsRegister.STICK_TITANE), new ItemStack(ItemsRegister.COMPRESSED_TITANE), new ItemStack((Item)ItemsRegister.STICK_SPEED, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.ORB_STRENGHT), new ItemStack(ItemsRegister.STICK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.STICK_TITANE), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack((Item)ItemsRegister.STICK_STRENGHT, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.ORB_JUMP), new ItemStack(ItemsRegister.STICK_TITANE), new ItemStack(BlocksRegister.SLIMEPAD_BLOCK), new ItemStack(ItemsRegister.STICK_TITANE), new ItemStack(BlocksRegister.SLIMEPAD_BLOCK), new ItemStack((Item)ItemsRegister.STICK_JUMP, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.ORB_HEAL), new ItemStack(ItemsRegister.STICK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.STICK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack((Item)ItemsRegister.STICK_HEAL, 1));
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.STICK_STRENGHT), new ItemStack((Item)ItemsRegister.STICK_HEAL), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack((Item)ItemsRegister.STICK_SPEED), new ItemStack(ItemsRegister.FINDIUM), new ItemStack((Item)ItemsRegister.STICK_GOD, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.ORB_JUMP), new ItemStack((Item)ItemsRegister.STICK_JUMP), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack((Item)ItemsRegister.STICK_JUMP), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack((Item)ItemsRegister.STICK_HYPERJUMP, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.TITANE_SWORD), new ItemStack(ItemsRegister.STICK_AMETHYST), new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM), new ItemStack(ItemsRegister.STICK_AMETHYST), new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM), new ItemStack((Item)ItemsRegister.STICK_DAMAGE, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.TUSK), new ItemStack(ItemsRegister.AMETHYST_INGOT), new ItemStack(BlocksRegister.BLOCK_AMETHYST), new ItemStack(ItemsRegister.AMETHYST_INGOT), new ItemStack(BlocksRegister.BLOCK_AMETHYST), new ItemStack(ItemsRegister.AMETHYST_HORN, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.TUSK), new ItemStack(ItemsRegister.TITANE_INGOT), new ItemStack(BlocksRegister.BLOCK_TITANE), new ItemStack(ItemsRegister.TITANE_INGOT), new ItemStack(BlocksRegister.BLOCK_TITANE), new ItemStack(ItemsRegister.TITANE_HORN, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.TUSK), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_HORN, 1));
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.STICK_HEAL), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_TITANE), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_TITANE), new ItemStack((Item)ItemsRegister.STICK_HEAL, 1));
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.STICK_SPEED), new ItemStack(BlocksRegister.BLOCK_TITANE), new ItemStack(ItemsRegister.COMPRESSED_AMETHYST), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(BlocksRegister.BLOCK_TITANE), new ItemStack((Item)ItemsRegister.STICK_SPEED, 1));
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.STICK_STRENGHT), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_TITANE), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_TITANE), new ItemStack((Item)ItemsRegister.STICK_STRENGHT, 1));
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.STICK_JUMP), new ItemStack(ItemsRegister.STICK_TITANE), new ItemStack(BlocksRegister.SLIMEPAD_BLOCK), new ItemStack(ItemsRegister.STICK_TITANE), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack((Item)ItemsRegister.STICK_JUMP, 1));
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.STICK_GOD), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack((Item)ItemsRegister.STICK_GOD, 1));
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.STICK_DAMAGE), new ItemStack(BlocksRegister.BLOCK_AMETHYST), new ItemStack(ItemsRegister.STICK_AMETHYST), new ItemStack(ItemsRegister.STICK_AMETHYST), new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM), new ItemStack((Item)ItemsRegister.STICK_DAMAGE, 1));
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.STICK_HYPERJUMP), new ItemStack(BlocksRegister.SLIMEPAD_BLOCK), new ItemStack(ItemsRegister.PALADIUM_CORE), new ItemStack(ItemsRegister.PALADIUM_CORE), new ItemStack((Item)ItemsRegister.STICK_HEAL), new ItemStack((Item)ItemsRegister.STICK_HYPERJUMP, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.AMETHYST_HORN), new ItemStack(ItemsRegister.STICK_AMETHYST), new ItemStack(ItemsRegister.STICK_AMETHYST), new ItemStack(ItemsRegister.STICK_AMETHYST), new ItemStack(BlocksRegister.BLOCK_AMETHYST), new ItemStack(ItemsRegister.AMETHYST_HORN, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.TITANE_HORN), new ItemStack(ItemsRegister.STICK_TITANE), new ItemStack(ItemsRegister.STICK_TITANE), new ItemStack(ItemsRegister.STICK_TITANE), new ItemStack(BlocksRegister.BLOCK_TITANE), new ItemStack(ItemsRegister.TITANE_HORN, 1));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.PALADIUM_HORN), new ItemStack(ItemsRegister.STICK_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_CORE), new ItemStack(ItemsRegister.STICK_PALADIUM), new ItemStack(BlocksRegister.BLOCK_PALADIUM), new ItemStack(ItemsRegister.PALADIUM_HORN, 1));
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.UNCLAIMFINDER), new Object[] { " X ", "YWY", " X ", 
          Character.valueOf('X'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE, Character.valueOf('W'), ItemsRegister.FINDIUM });
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.UNCLAIMFINDER), new ItemStack(ItemsRegister.FINDIUM), new ItemStack(ItemsRegister.FINDIUM), new ItemStack(ItemsRegister.FINDIUM), new ItemStack(ItemsRegister.FINDIUM), new ItemStack((Item)ItemsRegister.UNCLAIMFINDER_ORANGE));
    PaladiumMachineRecipies.add(new ItemStack((Item)ItemsRegister.UNCLAIMFINDER_ORANGE), new ItemStack(ItemsRegister.FINDIUM), new ItemStack(ItemsRegister.PALADIUM_CORE), new ItemStack(ItemsRegister.FINDIUM), new ItemStack(ItemsRegister.PALADIUM_CORE), new ItemStack((Item)ItemsRegister.UNCLAIMFINDER_ROUGE));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.BIG_RING), new ItemStack(BlocksRegister.BLOCK_FINDIUM), new ItemStack((Item)ItemsRegister.UNCLAIMFINDER_ROUGE), new ItemStack(BlocksRegister.BLOCK_FINDIUM), new ItemStack((Item)ItemsRegister.UNCLAIMFINDER_ROUGE), new ItemStack((Item)ItemsRegister.UNCLAIMFINDER_BLEU));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.INVOKER_STONE), new ItemStack(ItemsRegister.DUNGEON_RUNE_FRAGMENT_TIER_0), new ItemStack(ItemsRegister.DUNGEON_RUNE_FRAGMENT_TIER_0), new ItemStack(ItemsRegister.DUNGEON_RUNE_FRAGMENT_TIER_0), new ItemStack(ItemsRegister.DUNGEON_RUNE_FRAGMENT_TIER_0), new ItemStack(ItemsRegister.DUNGEON_RUNE_TIER_0));
    PaladiumMachineRecipies.add(new ItemStack(ItemsRegister.INVOKER_STONE), new ItemStack(ItemsRegister.DUNGEON_RUNE_FRAGMENT_TIER_1), new ItemStack(ItemsRegister.DUNGEON_RUNE_FRAGMENT_TIER_1), new ItemStack(ItemsRegister.DUNGEON_RUNE_FRAGMENT_TIER_1), new ItemStack(ItemsRegister.DUNGEON_RUNE_FRAGMENT_TIER_1), new ItemStack(ItemsRegister.DUNGEON_RUNE_TIER_1));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Craft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */