package fr.paladium.palashop.common.tebex;

import fr.paladium.palashop.api.common.tebex.ITebexAPI;
import fr.paladium.palashop.api.common.tebex.request.TebexBasketUpdateRequest;
import fr.paladium.palashop.api.utils.CompletableCallback;
import fr.paladium.palashop.common.tebex.dto.TebexBasket;
import java.util.concurrent.CompletableFuture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class null implements Callback<TebexBasket> {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\tebex\TebexManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */