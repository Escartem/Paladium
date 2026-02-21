package fr.paladium.palashop.server.giftcard;

import fr.paladium.palashop.api.server.giftcard.request.GiftCardRedeemRequest;
import fr.paladium.palashop.api.server.giftcard.response.GiftCardRedeemResponse;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.server.giftcard.dto.GiftCard;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import fr.paladium.palashop.server.shop.event.ShopGiftCardRedeemProviderEvent;
import java.util.Optional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<GiftCard> {
  public void onResponse(Call giftcardCall, Response giftcardResponse) {
    if (!giftcardResponse.isSuccessful() || giftcardResponse.body() == null) {
      future.completeExceptionally(new RuntimeException("Failed to get gift card: " + giftcardResponse.message()));
      return;
    } 
    final Optional<IShopItem> optional = ShopManager.getItem(((GiftCard)giftcardResponse.body()).getItemId());
    if (!optional.isPresent()) {
      future.complete(GiftCardRedeemResponse.status(GiftCardRedeemResponse.Status.ITEM_NOT_FOUND));
      return;
    } 
    ConditionalBuyableObject conditional = ConditionalBuyableObject.from(user, player, optional.get());
    if (!conditional.isBuyable()) {
      future.complete(GiftCardRedeemResponse.error(conditional.getError()));
      return;
    } 
    if (((IShopItem)optional.get()).getProviderInstance() != null && ProviderDispatcher.dispatch(((IShopItem)optional.get()).getProviderInstance(), (ProviderEvent)ShopGiftCardRedeemProviderEvent.pre(uuid, optional.get()))) {
      future.complete(GiftCardRedeemResponse.status(GiftCardRedeemResponse.Status.ITEM_NOT_FOUND));
      return;
    } 
    giftcardAPI.redeem(code, new GiftCardRedeemRequest(code, uuid)).enqueue(new Callback<GiftCardRedeemResponse>() {
          public void onResponse(Call call, Response response) {
            if (!response.isSuccessful() || response.body() == null) {
              future.completeExceptionally(new RuntimeException("Failed to redeem gift card: " + response.message()));
              return;
            } 
            if (((GiftCardRedeemResponse)response.body()).getStatus() == GiftCardRedeemResponse.Status.SUCCESS) {
              ShopManager.User.apply(player, ((GiftCardRedeemResponse)response.body()).getItem());
              if (((IShopItem)optional.get()).getProviderInstance() != null)
                ProviderDispatcher.dispatch(((IShopItem)optional.get()).getProviderInstance(), (ProviderEvent)ShopGiftCardRedeemProviderEvent.post(uuid, optional.get())); 
            } 
            future.complete(response.body());
          }
          
          public void onFailure(Call call, Throwable t) {
            future.completeExceptionally(t);
          }
        });
  }
  
  public void onFailure(Call giftcardCall, Throwable giftcardThrowable) {
    future.completeExceptionally(giftcardThrowable);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\giftcard\GiftCardManager$1$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */