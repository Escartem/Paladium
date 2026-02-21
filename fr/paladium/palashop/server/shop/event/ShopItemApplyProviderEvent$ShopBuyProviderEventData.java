package fr.paladium.palashop.server.shop.event;

import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import net.minecraft.entity.player.EntityPlayer;

public class ShopBuyProviderEventData<T extends IShopItem> {
  private final EntityPlayer player;
  
  private final T shopItem;
  
  public ShopBuyProviderEventData(EntityPlayer player, T shopItem) {
    this.player = player;
    this.shopItem = shopItem;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public T getShopItem() {
    return this.shopItem;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\event\ShopItemApplyProviderEvent$ShopBuyProviderEventData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */