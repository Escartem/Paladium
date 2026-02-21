package fr.paladium.palaforgeutils.lib.container;

import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public interface IForgeInventory extends IInventory {
  void addSlot(Slot paramSlot);
  
  Slot getSlot(int paramInt);
  
  List<Slot> getSlots();
  
  ItemStack[] getContent();
  
  void setContent(ItemStack[] paramArrayOfItemStack);
  
  default boolean shouldDropOnClose() {
    return true;
  }
  
  default boolean shouldReturnOnClose() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\container\IForgeInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */