package fr.paladium.palashop.api.common.tebex;

import fr.paladium.palashop.api.common.tebex.request.TebexBasketCreateRequest;
import fr.paladium.palashop.api.common.tebex.request.TebexBasketUpdateRequest;
import fr.paladium.palashop.common.tebex.dto.TebexBasket;
import fr.paladium.palashop.common.tebex.dto.TebexPackage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ITebexAPI {
  @GET("/api/accounts/{token}/packages/{packageId}")
  Call<TebexPackage> getPackage(@Path("token") String paramString, @Path("packageId") int paramInt);
  
  @POST("/api/accounts/{token}/baskets")
  Call<TebexBasket> createBasket(@Path("token") String paramString, @Body TebexBasketCreateRequest paramTebexBasketCreateRequest);
  
  @POST("/api/baskets/{ident}/packages")
  Call<TebexBasket> updateBasket(@Path("ident") String paramString, @Body TebexBasketUpdateRequest paramTebexBasketUpdateRequest);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\common\tebex\ITebexAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */