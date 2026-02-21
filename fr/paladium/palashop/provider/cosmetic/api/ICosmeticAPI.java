package fr.paladium.palashop.provider.cosmetic.api;

import fr.paladium.palashop.provider.cosmetic.api.request.CosmeticMutationRequest;
import fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic.ShopCosmeticData;
import java.util.Set;
import lombok.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICosmeticAPI {
  @GET("{uuid}")
  Call<Set<ShopCosmeticData>> getCosmetics(@Path("uuid") @NonNull String paramString);
  
  @GET("{uuid}/has/{cosmeticId}")
  Call<Boolean> hasCosmetic(@Path("uuid") @NonNull String paramString1, @Path("cosmeticId") @NonNull String paramString2);
  
  @GET("{uuid}/list/{factory}")
  Call<Set<ShopCosmeticData>> getCosmetics(@Path("uuid") @NonNull String paramString1, @Path("factory") @NonNull String paramString2);
  
  @POST("{uuid}")
  Call<ShopCosmeticData> addCosmetic(@Path("uuid") @NonNull String paramString, @Body @NonNull CosmeticMutationRequest paramCosmeticMutationRequest);
  
  @POST("update")
  Call<Void> update();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\api\ICosmeticAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */