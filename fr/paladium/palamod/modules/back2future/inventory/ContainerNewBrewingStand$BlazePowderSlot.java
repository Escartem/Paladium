package fr.paladium.palamod.modules.back2future.inventory;

import fr.paladium.palamod.modules.back2future.recipes.BrewingFuelRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class BlazePowderSlot extends Slot {
  public BlazePowderSlot(IInventory inventory, int index, int x, int y) {
    super(inventory, index, x, y);
  }
  
  public boolean func_75214_a(ItemStack stack) {
    return (stack != null && BrewingFuelRegistry.isFuel(stack));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\inventory\ContainerNewBrewingStand$BlazePowderSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */