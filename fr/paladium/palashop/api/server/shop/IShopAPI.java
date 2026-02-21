package fr.paladium.palashop.api.server.shop;

import fr.paladium.palashop.api.server.shop.request.user.ShopBuyRequest;
import fr.paladium.palashop.api.server.shop.request.user.SubscriptionMutationRequest;
import fr.paladium.palashop.api.server.shop.response.user.ShopBuyResponse;
import fr.paladium.palashop.api.server.shop.response.user.SubscriptionMutationResponse;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.Subscription;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.palashop.server.shop.dto.page.ShopPage;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.List;
import lombok.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IShopAPI {
  @POST("update")
  Call<Void> update();
  
  @GET("offers/monthly")
  Call<ShopOffer> getMonthlyOffer();
  
  @GET("offers/current")
  Call<ShopOffer> getCurrentOffer();
  
  @GET("items")
  Call<List<IShopItem>> getItems();
  
  @GET("offers")
  Call<List<ShopOffer>> getOffers();
  
  @GET("items/{id}")
  Call<IShopItem> getItem(@Path("id") @NonNull String paramString);
  
  @GET("offers/{id}")
  Call<ShopOffer> getOffer(@Path("id") @NonNull String paramString);
  
  @GET("pages")
  Call<List<ShopPage>> getPages();
  
  @GET("pages/{id}")
  Call<ShopPage> getPage(@Path("id") @NonNull String paramString);
  
  @GET("user/{uuid}")
  Call<ShopUser> getUser(@Path("uuid") @NonNull String paramString);
  
  @GET("user/{uuid}/items")
  Call<List<String>> getUserItems(@Path("uuid") @NonNull String paramString);
  
  @GET("user/{uuid}/daily")
  Call<List<String>> getUserDailyItems(@Path("uuid") @NonNull String paramString);
  
  @GET("user/{uuid}/has/{item}")
  Call<Boolean> hasUserItem(@Path("uuid") @NonNull String paramString1, @Path("item") @NonNull String paramString2);
  
  @GET("user/{uuid}/subscriptions")
  Call<List<Subscription>> getUserSubscriptions(@Path("uuid") @NonNull String paramString);
  
  @POST("user/{uuid}/buy/{item}")
  Call<ShopBuyResponse> buyUserItem(@Path("uuid") @NonNull String paramString1, @Path("item") @NonNull String paramString2, @Body @NonNull ShopBuyRequest paramShopBuyRequest);
  
  @POST("user/{uuid}/subscription/{item}")
  Call<SubscriptionMutationResponse> mutateUserSubscription(@Path("uuid") @NonNull String paramString1, @Path("item") @NonNull String paramString2, @Body @NonNull SubscriptionMutationRequest paramSubscriptionMutationRequest);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\shop\IShopAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */