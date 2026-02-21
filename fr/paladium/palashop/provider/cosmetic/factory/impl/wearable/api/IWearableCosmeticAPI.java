package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.api;

import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import java.util.Set;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IWearableCosmeticAPI {
  @GET("list")
  Call<Set<WearableCosmetic>> cosmetics();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\api\IWearableCosmeticAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */