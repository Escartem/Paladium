package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.api;

import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto.KillAnimationCosmetic;
import java.util.Set;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IKillAnimationCosmeticAPI {
  @GET("list")
  Call<Set<KillAnimationCosmetic>> cosmetics();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\api\IKillAnimationCosmeticAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */