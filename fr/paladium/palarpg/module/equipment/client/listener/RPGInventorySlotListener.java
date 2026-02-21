package fr.paladium.palarpg.module.equipment.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palavanillagui.client.event.InventorySlotItemValidEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class RPGInventorySlotListener {
  @SubscribeEvent
  public void onInventorySlotValidItem(InventorySlotItemValidEvent event) {
    if (IRPGItem.is(event.getItemStack())) {
      RPGProfilePlayerData profile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)(Minecraft.func_71410_x()).field_71439_g, "profile");
      if (profile == null || ((IRPGItem)IRPGItem.get(event.getItemStack()).get()).getItemData() == null)
        return; 
      if (profile.getLevel() < ((IRPGItem)IRPGItem.get(event.getItemStack()).get()).getItemData().getRequiredLevel())
        event.setCanceled(true); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\client\listener\RPGInventorySlotListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */