package fr.paladium.palashop.common.shop.network.item;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetItem extends ForgePacket {
  @PacketData
  private String item;
  
  @PacketData
  private boolean offer;
  
  public BBPacketGetItem() {}
  
  public BBPacketGetItem(String item, boolean offer) {
    this.item = item;
    this.offer = offer;
  }
  
  public BBPacketGetItem(@NonNull IShopItem item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    this.item = item.getUniqueId();
    this.offer = false;
  }
  
  public BBPacketGetItem(@NonNull ShopOffer offer) {
    if (offer == null)
      throw new NullPointerException("offer is marked non-null but is null"); 
    this.item = offer.getUniqueId();
    this.offer = true;
  }
  
  public void processServer(EntityPlayerMP player) {
    ShopManager.User.getUser(UUIDUtils.toString((Entity)player))
      .thenAccept(user -> {
          if (this.offer) {
            Optional<ShopOffer> optionalOffer = ShopManager.getOffer(this.item);
            if (!optionalOffer.isPresent()) {
              reply(BBPacketGetItemData.empty());
              return;
            } 
            ShopOffer offer = ((ShopOffer)optionalOffer.get()).copy();
            List<ConditionalBuyableObject<IShopItem>> offerItems = new ArrayList<>();
            for (IShopItem item : offer.getShopItems()) {
              Optional<IShopItem> optional = ShopManager.getItem(item.getUniqueId());
              if (!optional.isPresent() || !((IShopItem)optional.get()).isAvailable().booleanValue())
                continue; 
              offerItems.add(ConditionalBuyableObject.from(user, (EntityPlayer)player, optional.get()));
            } 
            offer.setConditionalShopItems(offerItems);
            reply(BBPacketGetItemData.offer(ConditionalBuyableObject.from(user, (EntityPlayer)player, offer)));
          } else {
            Optional<IShopItem> optional = ShopManager.getItem(this.item);
            if (!optional.isPresent()) {
              reply(BBPacketGetItemData.empty());
              return;
            } 
            reply(BBPacketGetItemData.item(ConditionalBuyableObject.from(user, (EntityPlayer)player, optional.get())));
          } 
        }).exceptionally(e -> {
          (new Notification(Notification.NotificationType.ERROR, "Impossible de charger les données", "palashop")).send(player);
          e.printStackTrace();
          return null;
        });
  }
  
  public static class BBPacketGetItemData {
    private final ConditionalBuyableObject<ShopOffer> offer;
    
    private final ConditionalBuyableObject<IShopItem> item;
    
    private final boolean empty;
    
    public BBPacketGetItemData(ConditionalBuyableObject<ShopOffer> offer, ConditionalBuyableObject<IShopItem> item, boolean empty) {
      this.offer = offer;
      this.item = item;
      this.empty = empty;
    }
    
    public ConditionalBuyableObject<ShopOffer> getOffer() {
      return this.offer;
    }
    
    public ConditionalBuyableObject<IShopItem> getItem() {
      return this.item;
    }
    
    public boolean isEmpty() {
      return this.empty;
    }
    
    @NonNull
    public static BBPacketGetItemData offer(@NonNull ConditionalBuyableObject<ShopOffer> offer) {
      if (offer == null)
        throw new NullPointerException("offer is marked non-null but is null"); 
      return new BBPacketGetItemData(offer, null, false);
    }
    
    @NonNull
    public static BBPacketGetItemData item(@NonNull ConditionalBuyableObject<IShopItem> item) {
      if (item == null)
        throw new NullPointerException("item is marked non-null but is null"); 
      return new BBPacketGetItemData(null, item, false);
    }
    
    @NonNull
    public static BBPacketGetItemData empty() {
      return new BBPacketGetItemData(null, null, true);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\item\BBPacketGetItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */