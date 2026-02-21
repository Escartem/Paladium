package fr.paladium.palamod.modules.shop.api;

import fr.paladium.palamod.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<Void> {
  public void onResponse(Call<Void> call, Response<Void> response) {
    if (!response.isSuccessful())
      Constants.logger.error("[AdminShop] Failed to add transaction to history: " + response.message()); 
  }
  
  public void onFailure(Call<Void> call, Throwable throwable) {
    Constants.logger.error("[AdminShop] Failed to add transaction to history:");
    throwable.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\api\ShopManager$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */