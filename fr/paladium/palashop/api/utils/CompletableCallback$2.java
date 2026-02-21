package fr.paladium.palashop.api.utils;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class null implements Callback<T> {
  public void onResponse(Call<T> call, Response<T> response) {
    if (!response.isSuccessful()) {
      future.completeExceptionally(new RuntimeException("Invalid response " + response.code() + " " + response.message()));
      return;
    } 
    future.complete(function.apply(response.body()));
  }
  
  public void onFailure(Call<T> call, Throwable t) {
    future.completeExceptionally(t);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\ap\\utils\CompletableCallback$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */