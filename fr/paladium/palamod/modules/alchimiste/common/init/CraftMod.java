package fr.paladium.palamod.modules.alchimiste.common.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftMod {
  public static void init() {
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.EXTRACTOR, 2), new Object[] { 
          " S ", "SPS", "WFW", Character.valueOf('S'), Items.field_151055_y, 
          Character.valueOf('P'), ItemsRegister.PALADIUM_CORE, Character.valueOf('W'), Blocks.field_150344_f, Character.valueOf('F'), 
          ItemsRegister.BUCKET_SULFURIC });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.TANK_GOLD), new Object[] { "GVG", "V V", "GVG", Character.valueOf('G'), Items.field_151043_k, 
          Character.valueOf('V'), Blocks.field_150359_w });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.TANK_AMETHYSTE), new Object[] { "GVG", "V V", "GVG", Character.valueOf('G'), ItemsRegister.AMETHYST_INGOT, 
          Character.valueOf('V'), Blocks.field_150359_w });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.TANK_TITANE), new Object[] { "GVG", "V V", "GVG", Character.valueOf('G'), ItemsRegister.TITANE_INGOT, 
          Character.valueOf('V'), Blocks.field_150359_w });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.TANK_PALADIUM), new Object[] { "GVG", "V V", "GVG", Character.valueOf('G'), ItemsRegister.PALADIUM_INGOT, 
          Character.valueOf('V'), Blocks.field_150359_w });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.FLASK), new Object[] { "   ", "V V", "VVV", Character.valueOf('V'), Blocks.field_150359_w });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.CAULDRON_BLOCK, 3), new Object[] { "LLL", "LPL", "LLL", 
          Character.valueOf('L'), ItemsRegister.LIGHTNING_POTION, Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.CAULDRON_CORE, 1), new Object[] { "LPL", "LPL", "LPL", Character.valueOf('L'), ItemsRegister.LIGHTNING_POTION, 
          Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.GLUEBALL_PATTERN, 1), new Object[] { "OOO", "SLS", "OOO", 
          Character.valueOf('O'), Blocks.field_150344_f, Character.valueOf('S'), Items.field_151055_y, Character.valueOf('L'), Items.field_151116_aA });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.AMETHYSTE_PORTAL_BLOCK), new Object[] { "WWW", "WHW", "WWW", 
          Character.valueOf('W'), BlocksRegister.SHINY_JACARANDA_WOOD, Character.valueOf('H'), BlocksRegister.BLOCK_AMETHYST });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.TITANE_PORTAL_BLOCK), new Object[] { "WWW", "WHW", "WWW", 
          Character.valueOf('W'), BlocksRegister.SHINY_JUDEECERCIS_WOOD, Character.valueOf('H'), BlocksRegister.BLOCK_TITANE });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.PALADIUM_PORTAL_BLOCK), new Object[] { "WWW", "WHW", "WWW", 
          Character.valueOf('W'), BlocksRegister.SHINY_ERABLE_WOOD, Character.valueOf('H'), BlocksRegister.BLOCK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.ENDIUM_PORTAL_BLOCK), new Object[] { "WWW", "WHW", "WWW", 
          Character.valueOf('W'), BlocksRegister.SHINY_OSTRYA_WOOD, Character.valueOf('H'), ItemsRegister.ENDIUM_HEART });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.ENDIUM_HEART), new Object[] { "FEF", "EPE", "FEF", Character.valueOf('F'), BlocksRegister.BLOCK_FINDIUM, 
          Character.valueOf('E'), BlocksRegister.FLOWER_ENDIUM, Character.valueOf('P'), BlocksRegister.BLOCK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK), new Object[] { 
          "NPN", "FHF", "NPN", 
          Character.valueOf('N'), ItemsRegister.ENDIUM_POLLEN, Character.valueOf('P'), BlocksRegister.BLOCK_PALADIUM, Character.valueOf('F'), BlocksRegister.BLOCK_FINDIUM, 
          Character.valueOf('H'), 
          ItemsRegister.ENDIUM_HEART });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.AMETHYSTE_PORTAL_KEY), new Object[] { 
          "LSL", "ECE", " S ", 
          Character.valueOf('L'), ItemsRegister.LIGHTNING_POTION, Character.valueOf('S'), ItemsRegister.STICK_AMETHYST, Character.valueOf('E'), ItemsRegister.AMETHYST_SWORD, 
          Character.valueOf('C'), 
          ItemsRegister.COMPRESSED_AMETHYST });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.TITANE_PORTAL_KEY), new Object[] { 
          "LSL", "ECE", " S ", Character.valueOf('L'), ItemsRegister.LIGHTNING_POTION, 
          Character.valueOf('S'), ItemsRegister.STICK_TITANE, Character.valueOf('E'), ItemsRegister.TITANE_SWORD, 
          Character.valueOf('C'), 
          ItemsRegister.COMPRESSED_TITANE });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.PALADIUM_PORTAL_KEY), new Object[] { 
          "LSL", "ECE", " S ", 
          Character.valueOf('L'), ItemsRegister.LIGHTNING_POTION, Character.valueOf('S'), ItemsRegister.STICK_PALADIUM, Character.valueOf('E'), ItemsRegister.PALADIUM_SWORD, 
          Character.valueOf('C'), 
          ItemsRegister.COMPRESSED_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.ENDIUM_PORTAL_KEY), new Object[] { 
          "LFL", "BNB", " F ", Character.valueOf('B'), BlocksRegister.BLOCK_FINDIUM, 
          Character.valueOf('L'), ItemsRegister.LIGHTNING_POTION, Character.valueOf('F'), ItemsRegister.FINDIUM, 
          Character.valueOf('N'), 
          ItemsRegister.ENDIUM_POLLEN });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.MYSTICAL_BOOKSHELF, 2), new Object[] { "WWW", "BBB", "WWW", 
          Character.valueOf('W'), BlocksRegister.SHINY_JACARANDA_WOOD, Character.valueOf('B'), Items.field_151122_aG });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\init\CraftMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */