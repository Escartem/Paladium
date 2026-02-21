package fr.paladium.palamod.modules.paladium.client.betternei.craft.factory;

import fr.paladium.betternei.api.craft.factory.ICraftFactory;
import fr.paladium.betternei.client.impl.craft.WorkbenchCraft;
import fr.paladium.palamod.modules.paladium.common.crafting.EndiumGauntletRecipe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class EndiumGauntletRecipeFactory implements ICraftFactory {
  public List<ItemStack> parse(IRecipe recipe) {
    if (recipe instanceof EndiumGauntletRecipe) {
      List<ItemStack> matrix = new ArrayList<>();
      EndiumGauntletRecipe shaped = (EndiumGauntletRecipe)recipe;
      ItemStack[] input = new ItemStack[shaped.func_77570_a()];
      int width = shaped.recipeWidth;
      int height = shaped.recipeHeight;
      int i;
      for (i = 0; i < input.length; i++) {
        if (shaped.recipeItems[i] == null) {
          input[i] = null;
        } else {
          ItemStack stack = shaped.recipeItems[i].func_77946_l();
          if (stack.field_77994_a > 1)
            stack.field_77994_a = 1; 
          input[i] = stack;
        } 
      } 
      if (input.length == 1) {
        for (i = 0; i < 9; i++)
          matrix.add(null); 
        matrix.set(4, input[0]);
      } else if (input.length == 4) {
        for (i = 0; i < 9; i++)
          matrix.add(null); 
        matrix.set(0, WorkbenchCraft.getSafeStack(input, 0));
        matrix.set(1, WorkbenchCraft.getSafeStack(input, 1));
        matrix.set(3, WorkbenchCraft.getSafeStack(input, 2));
        matrix.set(4, WorkbenchCraft.getSafeStack(input, 3));
      } else if (input.length == 9) {
        for (i = 0; i < 9; i++)
          matrix.add(WorkbenchCraft.getSafeStack(input, i)); 
      } else if (width == 1 && height == 2) {
        matrix.add(WorkbenchCraft.getSafeStack(input, 0));
        matrix.add(null);
        matrix.add(null);
        matrix.add(WorkbenchCraft.getSafeStack(input, 1));
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
      } else if (width == 1 && height == 3) {
        matrix.add(WorkbenchCraft.getSafeStack(input, 0));
        matrix.add(null);
        matrix.add(null);
        matrix.add(WorkbenchCraft.getSafeStack(input, 1));
        matrix.add(null);
        matrix.add(null);
        matrix.add(WorkbenchCraft.getSafeStack(input, 2));
        matrix.add(null);
        matrix.add(null);
      } else if (width == 2 && height == 3) {
        matrix.add(WorkbenchCraft.getSafeStack(input, 0));
        matrix.add(WorkbenchCraft.getSafeStack(input, 1));
        matrix.add(null);
        matrix.add(WorkbenchCraft.getSafeStack(input, 2));
        matrix.add(WorkbenchCraft.getSafeStack(input, 3));
        matrix.add(null);
        matrix.add(WorkbenchCraft.getSafeStack(input, 5));
        matrix.add(WorkbenchCraft.getSafeStack(input, 6));
        matrix.add(null);
      } else if (width == 2 && height == 1) {
        matrix.add(WorkbenchCraft.getSafeStack(input, 0));
        matrix.add(WorkbenchCraft.getSafeStack(input, 1));
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
      } else if (width == 3 && height == 2) {
        matrix.add(WorkbenchCraft.getSafeStack(input, 0));
        matrix.add(WorkbenchCraft.getSafeStack(input, 1));
        matrix.add(WorkbenchCraft.getSafeStack(input, 2));
        matrix.add(WorkbenchCraft.getSafeStack(input, 3));
        matrix.add(WorkbenchCraft.getSafeStack(input, 4));
        matrix.add(WorkbenchCraft.getSafeStack(input, 5));
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
      } else if (width == 3 && height == 1) {
        matrix.add(WorkbenchCraft.getSafeStack(input, 0));
        matrix.add(WorkbenchCraft.getSafeStack(input, 1));
        matrix.add(WorkbenchCraft.getSafeStack(input, 2));
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
        matrix.add(null);
      } 
      return matrix;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\craft\factory\EndiumGauntletRecipeFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */