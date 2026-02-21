package fr.paladium.palarpg.module.equipment.common.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamixins.event.SlotCanTakeEvent;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGCraftCache;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGCraftData;
import fr.paladium.palarpg.module.equipment.common.playerdata.RPGCraftPlayerData;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class EquipmentCraftEventListener {
  @SubscribeEvent
  public void onSlotCanTake(SlotCanTakeEvent event) {
    if (event.getItemStack() == null || event.getSlot() == null || event.getPlayer() == null)
      return; 
    if (event.getSlot() instanceof net.minecraft.inventory.SlotCrafting && !event.isCanceled()) {
      RPGCraftData craftData = RPGCraftCache.getRPGCraftData(event.getItemStack());
      if (craftData == null)
        return; 
      if (craftData.getRequiredLevel() > 0) {
        RPGProfilePlayerData playerProfileData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)event.getPlayer(), "profile");
        if (playerProfileData != null && playerProfileData.getLevel() < craftData.getRequiredLevel()) {
          if (!(event.getPlayer()).field_70170_p.field_72995_K)
            (new Notification(Notification.NotificationType.ERROR, "Vous devez être niveau " + craftData.getRequiredLevel() + " RPG pour crafter cet item", "RPG")).send((EntityPlayerMP)event.getPlayer()); 
          event.setCanceled(true);
          return;
        } 
      } 
      if (craftData.isRequiredParchment()) {
        RPGCraftPlayerData playerCraftData = (RPGCraftPlayerData)RPGPlayer.getData((Entity)event.getPlayer(), "craft");
        if (playerCraftData != null && !playerCraftData.isCraftUnlocked(event.getItemStack())) {
          if (!(event.getPlayer()).field_70170_p.field_72995_K)
            (new Notification(Notification.NotificationType.ERROR, "Vous n'avez pas débloqué ce craft avec un parchemin de craft", "RPG")).send((EntityPlayerMP)event.getPlayer()); 
          event.setCanceled(true);
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\listener\EquipmentCraftEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */