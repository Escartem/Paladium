package fr.paladium.palamod.modules.luckyblock.profile.api;

import fr.paladium.palamod.modules.luckyblock.profile.dto.LuckyBlock;
import fr.paladium.profile.server.requests.model.Paging;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LuckyBlockApi {
  @GET("player/profile/{uuid}/luckyblocks")
  Call<Paging<LuckyBlock>> getLuckyBlocks(@Path("uuid") String paramString, @Query("offset") int paramInt1, @Query("limit") int paramInt2);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\profile\api\LuckyBlockApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */