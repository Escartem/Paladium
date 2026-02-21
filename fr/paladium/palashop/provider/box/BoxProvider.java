package fr.paladium.palashop.provider.box;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palashop.api.utils.CompletableCallback;
import fr.paladium.palashop.common.provider.dto.ShopProvider;
import fr.paladium.palashop.common.provider.dto.impl.AShopProvider;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.provider.box.client.BoxClientProxy;
import fr.paladium.palashop.provider.box.client.render.ui.BoxShopRenderer;
import fr.paladium.palashop.provider.box.common.BoxCommonProxy;
import fr.paladium.palashop.provider.box.common.dto.shop.BoxShopItem;
import fr.paladium.palashop.provider.box.server.BoxServerProxy;
import fr.paladium.palashop.server.shop.event.ShopBuyProviderEvent;
import fr.paladium.palashop.server.shop.event.ShopItemApplyProviderEvent;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@ShopProvider(id = "box", item = BoxShopItem.class)
public class BoxProvider extends AShopProvider<BoxShopItem> {
  private static BoxProvider instance;
  
  private static BoxCommonProxy proxy;
  
  public BoxProvider() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    instance = this;
    proxy = (BoxCommonProxy)createProxy("fr.paladium.palashop.provider.box.client.BoxClientProxy", "fr.paladium.palashop.provider.box.server.BoxServerProxy");
    ProviderDispatcher.register(proxy);
  }
  
  @ProviderListener
  public void onShopBuy(ShopBuyProviderEvent<BoxShopItem> event) {
    if (event.getPhase() != ProviderEvent.Phase.PRE)
      return; 
    ItemStack item = ((BoxShopItem)((ShopBuyProviderEvent.ShopBuyProviderEventData)event.getResult()).getShopItem()).getItemStack();
    if (item != null)
      return; 
    event.setCanceled(true);
  }
  
  @ProviderListener
  public void onShopApply(ShopItemApplyProviderEvent<BoxShopItem> event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    EntityPlayer player = ((ShopItemApplyProviderEvent.ShopBuyProviderEventData)event.getResult()).getPlayer();
    ItemStack item = ((BoxShopItem)((ShopItemApplyProviderEvent.ShopBuyProviderEventData)event.getResult()).getShopItem()).getItemStack().func_77946_l();
    if (player == null || item == null)
      return; 
    PalaGiveManager.inst().give(player, item);
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<Void> update() {
    CompletableFuture<Void> future = new CompletableFuture<>();
    getServer().getApi().update().enqueue(CompletableCallback.create(future));
    return future;
  }
  
  public ShopElementRenderer getRenderer(@NonNull String type, @NonNull Object object) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (object == null)
      throw new NullPointerException("object is marked non-null but is null"); 
    if (!(object instanceof BoxShopItem))
      return null; 
    BoxShopItem shopItem = (BoxShopItem)object;
    if (shopItem.getThumbnail() != null && !shopItem.getThumbnail().isEmpty())
      return null; 
    return (ShopElementRenderer)new BoxShopRenderer();
  }
  
  public static BoxCommonProxy getProxy() {
    return proxy;
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public static BoxClientProxy getClient() {
    return (BoxClientProxy)proxy;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static BoxServerProxy getServer() {
    return (BoxServerProxy)proxy;
  }
  
  @NonNull
  public static BoxProvider getInstance() {
    if (instance == null)
      throw new IllegalStateException("BoxProvider is not initialized yet!"); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\BoxProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */