package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.api;

import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.dto.EmoteCosmetic;
import java.util.Set;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IEmoteCosmeticAPI {
  @GET("list")
  Call<Set<EmoteCosmetic>> cosmetics();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\api\IEmoteCosmeticAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */