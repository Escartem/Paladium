package fr.paladium.palashop.server.shop;

import fr.paladium.palashop.api.server.shop.response.user.ShopBuyResponse;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.server.shop.dto.BuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.event.ShopBuyProviderEvent;
import java.util.AbstractMap;
import net.minecraft.entity.player.EntityPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<ShopBuyResponse> {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\ShopManager$User$3$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */