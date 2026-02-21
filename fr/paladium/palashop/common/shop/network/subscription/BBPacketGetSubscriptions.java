package fr.paladium.palashop.common.shop.network.subscription;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.item.Subscription;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetSubscriptions extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    ShopManager.User.getSubscriptions(UUIDUtils.toString((Entity)player))
      .thenAccept(subscriptionList -> reply(new BBPacketGetSubscriptionsData(subscriptionList)))
      
      .exceptionally(e -> {
          (new Notification(Notification.NotificationType.ERROR, "Impossible de charger les abonnements", "palashop")).send(player);
          e.printStackTrace();
          return null;
        });
  }
  
  public class BBPacketGetSubscriptionsData {
    private final List<Subscription> subscriptionList;
    
    public BBPacketGetSubscriptionsData(List<Subscription> subscriptionList) {
      this.subscriptionList = subscriptionList;
    }
    
    public List<Subscription> getSubscriptionList() {
      return this.subscriptionList;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\subscription\BBPacketGetSubscriptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */