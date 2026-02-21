package fr.paladium.palashop.common.shop.network.home;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetOffers extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    ShopManager.User.getUser(UUIDUtils.toString((Entity)player))
      .thenAccept(user -> {
          ShopOffer monthlyOffer = ShopManager.getMonthlyOffer().copy();
          List<ConditionalBuyableObject<IShopItem>> monthlyOfferItems = new ArrayList<>();
          for (IShopItem item : monthlyOffer.getShopItems()) {
            Optional<IShopItem> optional = ShopManager.getItem(item.getUniqueId());
            if (!optional.isPresent() || !((IShopItem)optional.get()).isAvailable().booleanValue())
              continue; 
            monthlyOfferItems.add(ConditionalBuyableObject.from(user, (EntityPlayer)player, optional.get()));
          } 
          monthlyOffer.setConditionalShopItems(monthlyOfferItems);
          ShopOffer currentOffer = ShopManager.getCurrentOffer().copy();
          List<ConditionalBuyableObject<IShopItem>> currentOfferItems = new ArrayList<>();
          for (IShopItem item : currentOffer.getShopItems()) {
            Optional<IShopItem> optional = ShopManager.getItem(item.getUniqueId());
            if (!optional.isPresent() || !((IShopItem)optional.get()).isAvailable().booleanValue())
              continue; 
            currentOfferItems.add(ConditionalBuyableObject.from(user, (EntityPlayer)player, optional.get()));
          } 
          currentOffer.setConditionalShopItems(currentOfferItems);
          reply(new BBPacketGetOffersData(ConditionalBuyableObject.from(user, (EntityPlayer)player, monthlyOffer), ConditionalBuyableObject.from(user, (EntityPlayer)player, currentOffer)));
        }).exceptionally(e -> {
          (new Notification(Notification.NotificationType.ERROR, "Impossible de charger les offres", "palashop")).send(player);
          e.printStackTrace();
          return null;
        });
  }
  
  public class BBPacketGetOffersData {
    private final ConditionalBuyableObject<ShopOffer> monthlyOffer;
    
    private final ConditionalBuyableObject<ShopOffer> currentOffer;
    
    public BBPacketGetOffersData(ConditionalBuyableObject<ShopOffer> monthlyOffer, ConditionalBuyableObject<ShopOffer> currentOffer) {
      this.monthlyOffer = monthlyOffer;
      this.currentOffer = currentOffer;
    }
    
    public ConditionalBuyableObject<ShopOffer> getMonthlyOffer() {
      return this.monthlyOffer;
    }
    
    public ConditionalBuyableObject<ShopOffer> getCurrentOffer() {
      return this.currentOffer;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\home\BBPacketGetOffers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */