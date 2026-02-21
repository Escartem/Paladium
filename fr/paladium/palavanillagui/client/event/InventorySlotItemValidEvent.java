package fr.paladium.palavanillagui.client.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.item.ItemStack;

@Cancelable
public class InventorySlotItemValidEvent extends Event {
  private final ItemStack itemStack;
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public InventorySlotItemValidEvent(ItemStack stack) {
    this.itemStack = stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\client\event\InventorySlotItemValidEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */