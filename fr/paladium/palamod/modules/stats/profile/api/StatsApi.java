package fr.paladium.palamod.modules.stats.profile.api;

import fr.paladium.palamod.modules.stats.dto.PlayerStats;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatsApi {
  @GET("player/profile/{uuid}/stats")
  Call<PlayerStats> getStats(@Path("uuid") String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\stats\profile\api\StatsApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */