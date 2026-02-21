package fr.paladium.palashop.server.shop;

import java.util.concurrent.CompletableFuture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class null implements Callback<Void> {
  public void onResponse(Call<Void> call, Response<Void> response) {
    if (!response.isSuccessful()) {
      future.completeExceptionally(new RuntimeException("Invalid response"));
      return;
    } 
    (new Thread(() -> {
          synchronized (ShopManager.LOCK) {
            try {
              ShopManager.load();
            } catch (Exception e) {
              future.completeExceptionally(e);
              return;
            } 
            future.complete(null);
          } 
        }"ShopManager/Update")).start();
  }
  
  public void onFailure(Call<Void> call, Throwable t) {
    future.completeExceptionally(t);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\ShopManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */