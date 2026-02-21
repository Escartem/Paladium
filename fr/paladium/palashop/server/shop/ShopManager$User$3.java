package fr.paladium.palashop.server.shop;

import fr.paladium.palashop.api.server.shop.IShopAPI;
import fr.paladium.palashop.api.server.shop.request.user.ShopBuyRequest;
import fr.paladium.palashop.api.server.shop.response.user.ShopBuyResponse;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.utils.server.ServerUtils;
import fr.paladium.palashop.server.shop.dto.BuyableObject;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.IBuyable;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import fr.paladium.palashop.server.shop.event.ShopBuyProviderEvent;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import net.minecraft.entity.player.EntityPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class null implements Callback<ShopUser> {
  public void onResponse(Call<ShopUser> shopUserCall, Response<ShopUser> shopUserResponse) {
    if (!shopUserResponse.isSuccessful() || shopUserResponse.body() == null) {
      future.completeExceptionally(new RuntimeException("Invalid response"));
      return;
    } 
    ShopUser shopUser = (ShopUser)shopUserResponse.body();
    Optional<BuyableObject<? extends IBuyable>> optional = Optional.empty();
    if (offer) {
      Optional<ShopOffer> shopOffer = ShopManager.getOffer(item);
      if (shopOffer.isPresent())
        optional = Optional.of(BuyableObject.of(shopUser, shopOffer.get())); 
    } else {
      Optional<IShopItem> shopItem = ShopManager.getItem(item);
      if (shopItem.isPresent())
        optional = Optional.of(BuyableObject.of(shopUser, shopItem.get())); 
    } 
    if (!optional.isPresent()) {
      future.complete(new AbstractMap.SimpleEntry<>(new ShopBuyResponse(uuid, item, offer, 0L, ShopBuyResponse.Status.ITEM_NOT_FOUND, null, null), null));
      return;
    } 
    final BuyableObject<? extends IBuyable> buyable = optional.get();
    if (!buyable.getItem().isAvailable().booleanValue() || (buyable.getItem() instanceof IShopItem && !((IShopItem)buyable.getItem()).isBuyable().booleanValue())) {
      future.complete(new AbstractMap.SimpleEntry<>(new ShopBuyResponse(uuid, item, offer, 0L, ShopBuyResponse.Status.ITEM_NOT_AVAILABLE, null, null), buyable));
      return;
    } 
    List<IShopItem> items = new ArrayList<>();
    if (buyable.getItem() instanceof ShopOffer) {
      items.addAll(((ShopOffer)buyable.getItem()).getShopItems());
    } else {
      items.add((IShopItem)buyable.getItem());
    } 
    for (IShopItem shopItem : items) {
      if (shopItem.getProviderInstance() == null)
        continue; 
      ShopBuyProviderEvent event = ShopBuyProviderEvent.pre(uuid, shopItem);
      ProviderDispatcher.dispatch(shopItem.getProviderInstance(), (ProviderEvent)event);
      if (event.isCanceled() || event.getResult().equals(shopItem)) {
        future.complete(new AbstractMap.SimpleEntry<>(new ShopBuyResponse(uuid, item, offer, 0L, ShopBuyResponse.Status.ITEM_NOT_AVAILABLE, null, null), buyable));
        return;
      } 
    } 
    ConditionalBuyableObject<? extends IBuyable> conditionalBuyable = ConditionalBuyableObject.from(shopUser, player, buyable);
    if ((buyable.isSubscription() && !conditionalBuyable.isBuyable() && conditionalBuyable.getErrorType() != ConditionalBuyableObject.ErrorType.OWNED) || (!buyable.isSubscription() && !conditionalBuyable.isBuyable())) {
      future.complete(new AbstractMap.SimpleEntry<>(new ShopBuyResponse(uuid, item, offer, 0L, ShopBuyResponse.Status.CONDITIONS_FAILED, null, conditionalBuyable.getError()), buyable));
      return;
    } 
    ShopBuyRequest request = new ShopBuyRequest(uuid, item, ServerUtils.getServerFullName(), offer);
    api.buyUserItem(request.getUuid(), request.getItem(), request).enqueue(new Callback<ShopBuyResponse>() {
          public void onResponse(Call<ShopBuyResponse> call, Response<ShopBuyResponse> response) {
            if (!response.isSuccessful() || response.body() == null) {
              future.completeExceptionally(new RuntimeException("Invalid response"));
              return;
            } 
            if (((ShopBuyResponse)response.body()).getStatus() == ShopBuyResponse.Status.SUCCESS && ((ShopBuyResponse)response.body()).getItems() != null && !((ShopBuyResponse)response.body()).getItems().isEmpty()) {
              ((ShopBuyResponse)response.body()).getItems().forEach(shopItem -> ShopManager.User.apply(player, shopItem));
              for (IShopItem shopItem : ((ShopBuyResponse)response.body()).getItems()) {
                if (shopItem.getProviderInstance() == null)
                  continue; 
                ProviderDispatcher.dispatch(shopItem.getProviderInstance(), (ProviderEvent)ShopBuyProviderEvent.post(uuid, shopItem));
              } 
            } 
            future.complete(new AbstractMap.SimpleEntry<>(response.body(), buyable));
          }
          
          public void onFailure(Call<ShopBuyResponse> call, Throwable t) {
            future.completeExceptionally(t);
          }
        });
  }
  
  public void onFailure(Call<ShopUser> shopUserCall, Throwable shopUserThrowable) {
    future.completeExceptionally(shopUserThrowable);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\ShopManager$User$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */