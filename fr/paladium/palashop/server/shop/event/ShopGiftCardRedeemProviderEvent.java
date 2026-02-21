package fr.paladium.palashop.server.shop.event;

import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import lombok.NonNull;

public class ShopGiftCardRedeemProviderEvent<T extends IShopItem> extends ProviderEvent<ShopGiftCardRedeemProviderEvent.ShopBuyProviderEventData<T>> {
  private ShopGiftCardRedeemProviderEvent(@NonNull ProviderEvent.Phase phase, @NonNull String uuid, @NonNull T shopItem) {
    super(phase, new ShopBuyProviderEventData<>(uuid, shopItem));
    if (phase == null)
      throw new NullPointerException("phase is marked non-null but is null"); 
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
  }
  
  public static <T extends IShopItem> ShopGiftCardRedeemProviderEvent<T> pre(@NonNull String uuid, @NonNull T shopItem) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
    return new ShopGiftCardRedeemProviderEvent<>(ProviderEvent.Phase.PRE, uuid, shopItem);
  }
  
  public static <T extends IShopItem> ShopGiftCardRedeemProviderEvent<T> post(@NonNull String uuid, @NonNull T shopItem) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
    return new ShopGiftCardRedeemProviderEvent<>(ProviderEvent.Phase.POST, uuid, shopItem);
  }
  
  public static class ShopBuyProviderEventData<T extends IShopItem> {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\event\ShopGiftCardRedeemProviderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */