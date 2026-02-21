package fr.paladium.palarpg.module.equipment.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PlayerHandChangedEvent extends Event {
  private final EntityPlayer player;
  
  private final ItemStack oldItemStack;
  
  private final ItemStack newItemStack;
  
  public PlayerHandChangedEvent(EntityPlayer player, ItemStack oldItemStack, ItemStack newItemStack) {
    this.player = player;
    this.oldItemStack = oldItemStack;
    this.newItemStack = newItemStack;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public ItemStack getOldItemStack() {
    return this.oldItemStack;
  }
  
  public ItemStack getNewItemStack() {
    return this.newItemStack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\event\PlayerHandChangedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */