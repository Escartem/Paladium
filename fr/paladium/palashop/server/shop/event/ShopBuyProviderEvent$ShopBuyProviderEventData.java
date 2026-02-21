package fr.paladium.palashop.server.shop.event;

import fr.paladium.palashop.server.shop.dto.item.IShopItem;

public class ShopBuyProviderEventData<T extends IShopItem> {
  private final String uuid;
  
  private final T shopItem;
  
  public ShopBuyProviderEventData(String uuid, T shopItem) {
    this.uuid = uuid;
    this.shopItem = shopItem;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public T getShopItem() {
    return this.shopItem;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\event\ShopBuyProviderEvent$ShopBuyProviderEventData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */