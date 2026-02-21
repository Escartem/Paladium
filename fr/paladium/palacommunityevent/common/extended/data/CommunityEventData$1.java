package fr.paladium.palacommunityevent.common.extended.data;

import fr.paladium.lib.apollon.Apollon;
import fr.paladium.lib.apollon.notification.ANotification;
import fr.paladium.lib.apollon.notification.MinecraftNotification;
import fr.paladium.palacommunityevent.common.pojo.CommonCommunityStep;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.server.api.pojo.EventCommunityData;
import fr.paladium.palaforgeutils.lib.api.callback.SilentRetrofitCallback;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

class null extends SilentRetrofitCallback<EventCommunityData> {
  public void onSuccess(EventCommunityData value) {
    int limitedProgress = Math.min(value.progress, communityEvent.totalItems);
    double currentPercent = limitedProgress / communityEvent.totalItems * 100.0D;
    if (currentPercent >= commonSteps.percentToReach) {
      CommunityEventData.this.rewarded.add(claimedId);
      Apollon.instance().getNotificationManager().sendNotification((ANotification)new MinecraftNotification("Event-Communautaire", "§aRécompense débloquée.", "paladium"), (EntityPlayerMP)p);
      for (ItemStack reward : commonSteps.rewards)
        InventoryUtils.giveOrDropitems(p, reward.func_77946_l()); 
    } else {
      Apollon.instance().getNotificationManager().sendNotification((ANotification)new MinecraftNotification("Event-Communautaire", "§eL'étape commune des §c§l" + commonSteps.percentToReach + "% §en'a pas encore été débloquée.", "paladium"), (EntityPlayerMP)p);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\extended\data\CommunityEventData$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */