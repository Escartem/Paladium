package fr.paladium.palashop.provider.item;

import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palashop.common.provider.dto.ShopProvider;
import fr.paladium.palashop.common.provider.dto.impl.AShopProvider;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.provider.item.dto.ItemShopItem;
import fr.paladium.palashop.provider.item.render.ui.ItemShopRenderer;
import fr.paladium.palashop.server.shop.event.ShopBuyProviderEvent;
import fr.paladium.palashop.server.shop.event.ShopItemApplyProviderEvent;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@ShopProvider(id = "item", item = ItemShopItem.class)
public class ItemProvider extends AShopProvider<ItemShopItem> {
  private static ItemProvider instance;
  
  public ItemProvider() {
    instance = this;
  }
  
  @ProviderListener
  public void onShopBuy(ShopBuyProviderEvent<ItemShopItem> event) {
    if (event.getPhase() != ProviderEvent.Phase.PRE)
      return; 
    ItemStack item = ((ItemShopItem)((ShopBuyProviderEvent.ShopBuyProviderEventData)event.getResult()).getShopItem()).getItemStack();
    if (item != null)
      return; 
    event.setCanceled(true);
  }
  
  @ProviderListener
  public void onShopApply(ShopItemApplyProviderEvent<ItemShopItem> event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    EntityPlayer player = ((ShopItemApplyProviderEvent.ShopBuyProviderEventData)event.getResult()).getPlayer();
    ItemStack item = ((ItemShopItem)((ShopItemApplyProviderEvent.ShopBuyProviderEventData)event.getResult()).getShopItem()).getItemStack().func_77946_l();
    if (player == null || item == null)
      return; 
    PalaGiveManager.inst().give(player, item);
  }
  
  public ShopElementRenderer getRenderer(@NonNull String type, @NonNull Object object) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (object == null)
      throw new NullPointerException("object is marked non-null but is null"); 
    if (!(object instanceof ItemShopItem))
      return null; 
    ItemShopItem shopItem = (ItemShopItem)object;
    if (shopItem.getThumbnail() != null && !shopItem.getThumbnail().isEmpty())
      return null; 
    return (ShopElementRenderer)new ItemShopRenderer();
  }
  
  @NonNull
  public static ItemProvider getInstance() {
    if (instance == null)
      throw new IllegalStateException("ItemProvider is not initialized yet!"); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\item\ItemProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */