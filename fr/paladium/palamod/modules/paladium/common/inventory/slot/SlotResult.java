package fr.paladium.palamod.modules.paladium.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotResult extends Slot {
  public SlotResult(IInventory inventory, int id, int x, int y) {
    super(inventory, id, x, y);
  }
  
  public boolean func_75214_a(ItemStack stack) {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\inventory\slot\SlotResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */