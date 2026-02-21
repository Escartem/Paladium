package fr.paladium.palashop.common.shop.network.subscription;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palashop.api.server.shop.response.user.SubscriptionMutationResponse;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.item.Subscription;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketCancelSubscription extends ForgePacket {
  @PacketData
  private String itemId;
  
  public BBPacketCancelSubscription() {}
  
  public BBPacketCancelSubscription(String itemId) {
    this.itemId = itemId;
  }
  
  public void processServer(EntityPlayerMP player) {
    ShopManager.User.mutateSubscription((EntityPlayer)player, this.itemId, Subscription.Status.CANCELED)
      .thenAccept(response -> reply(response.getSubscription()))
      
      .exceptionally(e -> {
          (new Notification(Notification.NotificationType.ERROR, "Impossible de résilier l'abonnement", "palashop")).send(player);
          e.printStackTrace();
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\subscription\BBPacketCancelSubscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */