package fr.paladium.palamod.modules.back2future.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class IngredientSlot extends Slot {
  public IngredientSlot(IInventory inventory, int index, int x, int y) {
    super(inventory, index, x, y);
  }
  
  public boolean func_75214_a(ItemStack stack) {
    return (stack != null && stack.func_77973_b().func_150892_m(stack));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\inventory\ContainerNewBrewingStand$IngredientSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */