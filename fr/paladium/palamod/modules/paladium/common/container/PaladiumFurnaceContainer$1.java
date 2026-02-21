package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public boolean func_75214_a(ItemStack stack) {
    if (stack.func_77973_b() == ItemsRegister.FURNACE_UPGRADE)
      return false; 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\PaladiumFurnaceContainer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */