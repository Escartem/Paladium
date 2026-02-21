package fr.paladium.palamod.modules.shop.api;

import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.palamod.modules.shop.data.TransactionHistoryInput;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IShopAPI {
  @GET("shop/items")
  Call<List<ShopItem>> getItems();
  
  @POST("shop/transaction")
  Call<Void> addTransaction(@Body TransactionHistoryInput paramTransactionHistoryInput);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\api\IShopAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */