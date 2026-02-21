package fr.paladium.palarpg.module.equipment.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PlayerArmorChangedEvent extends Event {
  private final EntityPlayer player;
  
  private final int slotId;
  
  private final ItemStack oldArmor;
  
  private final ItemStack newArmor;
  
  public PlayerArmorChangedEvent(EntityPlayer player, int slotId, ItemStack oldArmor, ItemStack newArmor) {
    this.player = player;
    this.slotId = slotId;
    this.oldArmor = oldArmor;
    this.newArmor = newArmor;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public int getSlotId() {
    return this.slotId;
  }
  
  public ItemStack getOldArmor() {
    return this.oldArmor;
  }
  
  public ItemStack getNewArmor() {
    return this.newArmor;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\event\PlayerArmorChangedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */