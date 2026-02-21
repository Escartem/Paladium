package fr.paladium.palamod.modules.pillage.client.betternei;

import fr.paladium.betternei.api.craft.factory.ICraftFactory;
import fr.paladium.palamod.api.BlocksRegister;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class SlimeObsiRecipeFactory implements ICraftFactory {
  public List<ItemStack> parse(IRecipe recipe) {
    if (recipe instanceof fr.paladium.palamod.modules.pillage.common.recipes.RecipesSlimeObsidian) {
      List<ItemStack> matrix = new ArrayList<>();
      matrix.add(new ItemStack(Item.func_150898_a(Blocks.field_150343_Z)));
      matrix.add(new ItemStack(Item.func_150898_a(Blocks.field_150343_Z)));
      matrix.add(new ItemStack(Item.func_150898_a(Blocks.field_150343_Z)));
      matrix.add(new ItemStack(Item.func_150898_a(BlocksRegister.SLIMEPAD_BLOCK)));
      matrix.add(new ItemStack(Item.func_150898_a(BlocksRegister.SLIMEPAD_BLOCK)));
      matrix.add(new ItemStack(Item.func_150898_a(BlocksRegister.SLIMEPAD_BLOCK)));
      matrix.add(new ItemStack(Item.func_150898_a(Blocks.field_150343_Z)));
      matrix.add(new ItemStack(Item.func_150898_a(Blocks.field_150343_Z)));
      matrix.add(new ItemStack(Item.func_150898_a(Blocks.field_150343_Z)));
      return matrix;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\client\betternei\SlimeObsiRecipeFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */