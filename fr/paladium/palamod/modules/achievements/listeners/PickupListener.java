package fr.paladium.palamod.modules.achievements.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.achievements.types.PickupItemAchievement;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class PickupListener {
  @SubscribeEvent
  public void onPickup(EntityItemPickupEvent event) {
    if (event.isCanceled())
      return; 
    EntityItem entityItem = event.item;
    if (entityItem == null)
      return; 
    ItemStack itemStack = entityItem.func_92059_d();
    PickupItemAchievement.performCheck(event.entityPlayer, itemStack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\listeners\PickupListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */