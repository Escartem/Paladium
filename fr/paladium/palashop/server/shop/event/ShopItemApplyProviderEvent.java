package fr.paladium.palashop.server.shop.event;

import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class ShopItemApplyProviderEvent<T extends IShopItem> extends ProviderEvent<ShopItemApplyProviderEvent.ShopBuyProviderEventData<T>> {
  private ShopItemApplyProviderEvent(@NonNull ProviderEvent.Phase phase, @NonNull EntityPlayer player, @NonNull T shopItem) {
    super(phase, new ShopBuyProviderEventData<>(player, shopItem));
    if (phase == null)
      throw new NullPointerException("phase is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
  }
  
  public static <T extends IShopItem> ShopItemApplyProviderEvent<T> pre(@NonNull EntityPlayer player, @NonNull T shopItem) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
    return new ShopItemApplyProviderEvent<>(ProviderEvent.Phase.PRE, player, shopItem);
  }
  
  public static <T extends IShopItem> ShopItemApplyProviderEvent<T> post(@NonNull EntityPlayer player, @NonNull T shopItem) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
    return new ShopItemApplyProviderEvent<>(ProviderEvent.Phase.POST, player, shopItem);
  }
  
  public static class ShopBuyProviderEventData<T extends IShopItem> {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\event\ShopItemApplyProviderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */