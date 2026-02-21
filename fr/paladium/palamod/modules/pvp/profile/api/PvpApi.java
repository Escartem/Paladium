package fr.paladium.palamod.modules.pvp.profile.api;

import fr.paladium.palamod.modules.pvp.dto.PvpStat;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PvpApi {
  @GET("player/profile/{uuid}/pvp")
  Call<List<PvpStat>> getPvp(@Path("uuid") String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pvp\profile\api\PvpApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */