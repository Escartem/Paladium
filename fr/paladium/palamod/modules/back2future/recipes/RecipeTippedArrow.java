package fr.paladium.palamod.modules.back2future.recipes;

import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.items.LingeringPotion;
import fr.paladium.palamod.modules.back2future.items.TippedArrow;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeTippedArrow extends ShapedOreRecipe {
  public RecipeTippedArrow(ItemStack result, Object... recipe) {
    super(result, recipe);
  }
  
  public ItemStack func_77572_b(InventoryCrafting grid) {
    ItemStack potion = grid.func_70463_b(1, 1);
    List<PotionEffect> effects = ((LingeringPotion)ModItems.lingering_potion).func_77832_l(potion);
    ItemStack stack = new ItemStack(ModItems.tipped_arrow, 8);
    if (!effects.isEmpty()) {
      PotionEffect effect = effects.get(0);
      TippedArrow.setEffect(stack, Potion.field_76425_a[effect.func_76456_a()], effect.func_76459_b(), effect
          .func_76458_c());
    } 
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\recipes\RecipeTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */