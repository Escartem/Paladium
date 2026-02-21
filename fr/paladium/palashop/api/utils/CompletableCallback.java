package fr.paladium.palashop.api.utils;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletableCallback {
  @NonNull
  public static <T> Callback<T> create(@NonNull final CompletableFuture<T> future) {
    if (future == null)
      throw new NullPointerException("future is marked non-null but is null"); 
    return new Callback<T>() {
        public void onResponse(Call<T> call, Response<T> response) {
          if (!response.isSuccessful()) {
            future.completeExceptionally(new RuntimeException("Invalid response " + response.code() + " " + response.message()));
            return;
          } 
          future.complete(response.body());
        }
        
        public void onFailure(Call<T> call, Throwable t) {
          future.completeExceptionally(t);
        }
      };
  }
  
  @NonNull
  public static <T, R> Callback<T> create(@NonNull final CompletableFuture<R> future, @NonNull final Function<T, R> function) {
    if (future == null)
      throw new NullPointerException("future is marked non-null but is null"); 
    if (function == null)
      throw new NullPointerException("function is marked non-null but is null"); 
    return new Callback<T>() {
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
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\ap\\utils\CompletableCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */