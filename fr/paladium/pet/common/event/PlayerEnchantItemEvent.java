package fr.paladium.pet.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PlayerEnchantItemEvent extends Event {
  private final EntityPlayer player;
  
  private final ItemStack stack;
  
  private final int cost;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public ItemStack getStack() {
    return this.stack;
  }
  
  public int getCost() {
    return this.cost;
  }
  
  public PlayerEnchantItemEvent(EntityPlayer player, ItemStack stack, int cost) {
    this.player = player;
    this.stack = stack;
    this.cost = cost;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\PlayerEnchantItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */