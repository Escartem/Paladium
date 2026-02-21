package fr.paladium.palamod.modules.paladium.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotSize extends Slot {
  int size;
  
  public SlotSize(IInventory inventory, int id, int x, int y, int size) {
    super(inventory, id, x, y);
    this.size = size;
  }
  
  public int func_75219_a() {
    return this.size;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\inventory\slot\SlotSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */