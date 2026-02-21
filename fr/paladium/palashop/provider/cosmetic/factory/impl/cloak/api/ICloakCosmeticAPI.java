package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.api;

import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.server.dto.CloakCosmetic;
import java.util.Set;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ICloakCosmeticAPI {
  @GET("list")
  Call<Set<CloakCosmetic>> cosmetics();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\api\ICloakCosmeticAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */