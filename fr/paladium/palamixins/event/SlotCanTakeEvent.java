package fr.paladium.palamixins.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@Cancelable
public class SlotCanTakeEvent extends Event {
  private final Slot slot;
  
  private final EntityPlayer player;
  
  private final ItemStack itemStack;
  
  public Slot getSlot() {
    return this.slot;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public SlotCanTakeEvent(Slot slot, EntityPlayer player, ItemStack stack) {
    this.slot = slot;
    this.player = player;
    this.itemStack = stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\SlotCanTakeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */