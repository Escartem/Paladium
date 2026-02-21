package fr.paladium.palamod.modules.shop.task;

import fr.paladium.Constants;
import fr.paladium.palamod.modules.shop.data.ShopItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<List<ShopItem>> {
  public void onResponse(Call<List<ShopItem>> call, Response<List<ShopItem>> response) {
    if (response.isSuccessful()) {
      List<ShopItem> items = (List<ShopItem>)response.body();
      Map<String, ShopItem> itemMap = new HashMap<>();
      assert items != null;
      items.forEach(item -> (ShopItem)itemMap.put(item.getItem(), item));
      ShopDataFetcherTask.access$000(ShopDataFetcherTask.this).setItems(itemMap);
    } else {
      Constants.logger.error("[AdminShop] Error while fetching shop items.");
      ShopDataFetcherTask.access$000(ShopDataFetcherTask.this).setItems(null);
    } 
  }
  
  public void onFailure(Call<List<ShopItem>> call, Throwable throwable) {
    Constants.logger.error("[AdminShop] Error while fetching shop items: ");
    throwable.printStackTrace();
    ShopDataFetcherTask.access$000(ShopDataFetcherTask.this).setItems(null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\task\ShopDataFetcherTask$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */