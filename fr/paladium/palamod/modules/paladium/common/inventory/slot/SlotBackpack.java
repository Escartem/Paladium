package fr.paladium.palamod.modules.paladium.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBackpack extends Slot {
  public SlotBackpack(IInventory inventory, int id, int x, int y) {
    super(inventory, id, x, y);
  }
  
  public boolean func_75214_a(ItemStack stack) {
    if (!(stack.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemBackpack))
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\inventory\slot\SlotBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */