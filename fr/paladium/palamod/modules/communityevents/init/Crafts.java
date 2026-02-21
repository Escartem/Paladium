package fr.paladium.palamod.modules.communityevents.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.communityevents.init.recipes.RecipesBarrel;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class Crafts {
  public static void register() {
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BARREL_WOOD, 2), new Object[] { "XXX", " X ", "XXX", 
          Character.valueOf('X'), new ItemStack(Blocks.field_150344_f) });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BARREL_WOOD, 1), new Object[] { "XXX", " X ", "XXX", 
          Character.valueOf('X'), new ItemStack(BlocksRegister.PLANKS_DEAD_WOOD) });
    GameRegistry.addRecipe((IRecipe)new RecipesBarrel(BlocksRegister.BARREL_WOOD));
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.GALETTE, 1), new Object[] { "BSB", "SFS", "BBB", 
          Character.valueOf('B'), new ItemStack(Items.field_151015_O, 1), Character.valueOf('S'), new ItemStack(Items.field_151102_aT, 1), Character.valueOf('F'), new ItemStack((Item)Items.field_151068_bn, 1) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\init\Crafts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */