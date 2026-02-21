package fr.paladium.palashop.common.shop.network.item;

import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import lombok.NonNull;

public class BBPacketGetItemData {
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


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\item\BBPacketGetItem$BBPacketGetItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */