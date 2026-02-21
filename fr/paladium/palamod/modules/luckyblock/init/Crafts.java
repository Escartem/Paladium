package fr.paladium.palamod.modules.luckyblock.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.recipes.RecipeGadgeto;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class Crafts {
  public static void register() {
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.GRAPPIN_HOOK), new Object[] { "II ", "IF ", "  S", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('F'), ItemsRegister.DIAMOND_STRING, Character.valueOf('S'), ItemsRegister.STICK_TITANE });
    GameRegistry.addRecipe(new ItemStack(Blocks.field_150423_aK), new Object[] { "III", "III", "III", 
          Character.valueOf('I'), ItemsRegister.PUMPKINCRUSH });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.PLANKS_BANANIER, 4), new Object[] { "X", 
          Character.valueOf('X'), new ItemStack(BlocksRegister.WOOD_BANANIER) });
    GameRegistry.addRecipe((IRecipe)new RecipeGadgeto());
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.WATER_PISTOL), new Object[] { "AAA", " B ", Character.valueOf('A'), new ItemStack((Item)Items.field_151068_bn, 1, 0), Character.valueOf('B'), new ItemStack(ItemsRegister.WATER_PISTOL, 1, 32767) });
    GameRegistry.addRecipe(new ItemStack(Items.field_151043_k, 20), new Object[] { "X", Character.valueOf('X'), new ItemStack(ItemsRegister.GOLDEN_EGG) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.RAINBOW_LEATHER_HELMET), new Object[] { "XXX", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.RAINBOW_LEATHER) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.RAINBOW_LEATHER_CHESTPLATE), new Object[] { "X X", "XXX", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.RAINBOW_LEATHER) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.RAINBOW_LEATHER_LEGGINGS), new Object[] { "XXX", "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.RAINBOW_LEATHER) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.RAINBOW_LEATHER_BOOTS), new Object[] { "X X", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.RAINBOW_LEATHER) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ROLLER), new Object[] { "XYX", "X X", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.MIXED_ENDIUM_INGOT), Character.valueOf('Y'), new ItemStack(ItemsRegister.ENDIUM_FRAGMENT) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\init\Crafts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */