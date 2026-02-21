package fr.paladium.palamod.modules.paladium.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotChestExplorer extends Slot {
  public SlotChestExplorer(IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }
  
  public boolean func_75214_a(ItemStack itemstack) {
    return true;
  }
  
  public ItemStack func_75209_a(int p_75209_1_) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\inventory\slot\SlotChestExplorer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */