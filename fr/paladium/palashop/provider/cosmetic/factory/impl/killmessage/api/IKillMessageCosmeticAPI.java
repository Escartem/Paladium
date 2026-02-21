package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.api;

import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server.dto.KillMessageCosmetic;
import java.util.Set;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IKillMessageCosmeticAPI {
  @GET("list")
  Call<Set<KillMessageCosmetic>> cosmetics();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\api\IKillMessageCosmeticAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */