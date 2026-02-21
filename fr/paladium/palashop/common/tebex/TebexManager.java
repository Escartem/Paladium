package fr.paladium.palashop.common.tebex;

import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.api.common.tebex.ITebexAPI;
import fr.paladium.palashop.api.common.tebex.request.TebexBasketCreateRequest;
import fr.paladium.palashop.api.common.tebex.request.TebexBasketUpdateRequest;
import fr.paladium.palashop.api.utils.CompletableCallback;
import fr.paladium.palashop.common.tebex.dto.TebexBasket;
import fr.paladium.palashop.common.tebex.dto.TebexPackage;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TebexManager {
  @NonNull
  public static CompletableFuture<TebexPackage> getPackageById(@NonNull String token, int packageId) {
    if (token == null)
      throw new NullPointerException("token is marked non-null but is null"); 
    CompletableFuture<TebexPackage> future = new CompletableFuture<>();
    PalaShopMod.proxy.getTebexAPI().getPackage(token, packageId).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @NonNull
  public static CompletableFuture<String> getCheckout(@NonNull String token, @NonNull String username, final int packageId) {
    if (token == null)
      throw new NullPointerException("token is marked non-null but is null"); 
    if (username == null)
      throw new NullPointerException("username is marked non-null but is null"); 
    final CompletableFuture<String> future = new CompletableFuture<>();
    final ITebexAPI tebexAPI = PalaShopMod.proxy.getTebexAPI();
    tebexAPI.createBasket(token, new TebexBasketCreateRequest(username)).enqueue(new Callback<TebexBasket>() {
          public void onResponse(Call<TebexBasket> call, Response<TebexBasket> response) {
            if (!response.isSuccessful() || response.body() == null) {
              future.completeExceptionally(new RuntimeException("Failed to create the basket"));
              return;
            } 
            TebexBasket basket = (TebexBasket)response.body();
            tebexAPI
              .updateBasket(basket.getData().getIdent(), new TebexBasketUpdateRequest(packageId, 1, new TebexBasketUpdateRequest.TebexBasketVariableData(basket.getData().getUsernameId())))
              .enqueue(CompletableCallback.create(future, result -> result.getData().getCheckoutLink()));
          }
          
          public void onFailure(Call<TebexBasket> call, Throwable t) {
            future.completeExceptionally(t);
          }
        });
    return future;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\tebex\TebexManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */