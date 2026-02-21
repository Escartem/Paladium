package fr.paladium.palashop.common.shop.network.home;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetDaily extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    String uuid = UUIDUtils.toString((Entity)player);
    ShopManager.User.getUser(uuid)
      .thenAccept(user -> ShopManager.User.getDailyItems(UUIDUtils.toString((Entity)player)).thenAccept(()).exceptionally(()))
      
      .exceptionally(e -> {
          (new Notification(Notification.NotificationType.ERROR, "Impossible de charger votre séléction du jour", "palashop")).send(player);
          reply(new BBPacketGetDailyData(new ArrayList<>()));
          return null;
        });
  }
  
  public class BBPacketGetDailyData {
    private final List<ConditionalBuyableObject<IShopItem>> itemList;
    
    public BBPacketGetDailyData(List<ConditionalBuyableObject<IShopItem>> itemList) {
      this.itemList = itemList;
    }
    
    public List<ConditionalBuyableObject<IShopItem>> getItemList() {
      return this.itemList;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\home\BBPacketGetDaily.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */