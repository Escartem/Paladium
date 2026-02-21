package fr.paladium.palashop.common.blackmarket.network;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.server.blackmarket.BlackMarketManager;
import fr.paladium.palashop.server.blackmarket.dto.BlackMarketData;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetBlackMarket extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    String uuid = UUIDUtils.toString((Entity)player);
    BlackMarketManager.getBlackMarket(UUIDUtils.toString((Entity)player))
      .thenAccept(blackMarket -> {
          if (blackMarket == null) {
            (new Notification(Notification.NotificationType.ERROR, "Le marché noir n'est pas ouvert", "palashop")).send(player);
            return;
          } 
          ShopManager.User.getUser(uuid).thenAccept(()).exceptionally(());
        }).exceptionally(e -> {
          (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le marché noir", "palashop")).send(player);
          e.printStackTrace();
          return null;
        });
  }
  
  public static class BBPacketGetBlackMarketData {
    private final long expiration;
    
    private final List<ConditionalBuyableObject<IShopItem>> items;
    
    public BBPacketGetBlackMarketData(long expiration, List<ConditionalBuyableObject<IShopItem>> items) {
      this.expiration = expiration;
      this.items = items;
    }
    
    public long getExpiration() {
      return this.expiration;
    }
    
    public List<ConditionalBuyableObject<IShopItem>> getItems() {
      return this.items;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.items });
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (obj == null || getClass() != obj.getClass())
        return false; 
      BBPacketGetBlackMarketData other = (BBPacketGetBlackMarketData)obj;
      return Objects.equals(this.items, other.items);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\blackmarket\network\BBPacketGetBlackMarket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */