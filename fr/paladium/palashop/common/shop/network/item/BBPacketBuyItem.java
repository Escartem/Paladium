package fr.paladium.palashop.common.shop.network.item;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.api.server.shop.response.user.ShopBuyResponse;
import fr.paladium.palashop.common.pb.network.SCPacketBuyPB;
import fr.paladium.palashop.server.shop.ShopManager;
import java.util.ArrayList;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketBuyItem extends ForgePacket {
  @PacketData
  private String item;
  
  @PacketData
  private boolean offer;
  
  public BBPacketBuyItem() {}
  
  public BBPacketBuyItem(String item, boolean offer) {
    this.item = item;
    this.offer = offer;
  }
  
  public void processServer(EntityPlayerMP player) {
    ShopManager.User.buy((EntityPlayer)player, this.item, this.offer).thenAccept(result -> {
          if (((ShopBuyResponse)result.getKey()).getStatus() == ShopBuyResponse.Status.NOT_ENOUGH_MONEY)
            (new SCPacketBuyPB(Long.parseLong(((ShopBuyResponse)result.getKey()).getError()))).send(player); 
          reply(result.getKey());
        }).exceptionally(throwable -> {
          (new Notification(Notification.NotificationType.ERROR, "Impossible d'acheter " + (this.offer ? "cette offre" : "ce produit"), "palashop")).send(player);
          reply(new ShopBuyResponse(UUIDUtils.toString((Entity)player), this.item, this.offer, -1L, ShopBuyResponse.Status.ERROR, new ArrayList(), null));
          throwable.printStackTrace();
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\item\BBPacketBuyItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */