package fr.paladium.palashop.api.server.blackmarket;

import fr.paladium.palashop.server.blackmarket.dto.BlackMarketConfig;
import fr.paladium.palashop.server.blackmarket.dto.BlackMarketData;
import lombok.NonNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IBlackMarketAPI {
  @GET("{uuid}")
  Call<BlackMarketData> getBlackMarket(@Path("uuid") @NonNull String paramString);
  
  @GET("config")
  Call<BlackMarketConfig> getConfig();
  
  @POST("update")
  Call<Void> update();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\blackmarket\IBlackMarketAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */