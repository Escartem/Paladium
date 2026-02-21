package fr.paladium.palashop.server.shop;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class null implements Callback<List<String>> {
  public void onResponse(Call<List<String>> call, Response<List<String>> response) {
    if (!response.isSuccessful() || response.body() == null) {
      future.completeExceptionally(new RuntimeException("Invalid response"));
      return;
    } 
    future.complete(response.body());
  }
  
  public void onFailure(Call<List<String>> call, Throwable t) {
    future.completeExceptionally(t);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\ShopManager$User$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */