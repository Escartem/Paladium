package fr.paladium.palamod.common.api.http;

import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumClaimReward;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumDeposit;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumRanking;
import fr.paladium.palamod.modules.trixium.api.TrixiumFactionProfile;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TRIXIUM {
  @POST("trixium/players/{uuid}/get")
  Call<TrixiumProfile> getPlayerTrixium(@Path("uuid") String paramString, @Body PlayerTrixiumDeposit paramPlayerTrixiumDeposit);
  
  @POST("trixium/players/{uuid}/deposit")
  Call<Void> deposit(@Path("uuid") String paramString, @Body PlayerTrixiumDeposit paramPlayerTrixiumDeposit);
  
  @POST("trixium/players/{uuid}/claim")
  Call<Void> claim(@Path("uuid") String paramString, @Body PlayerTrixiumClaimReward paramPlayerTrixiumClaimReward);
  
  @POST("trixium/players/ranking")
  Call<List<TrixiumProfile>> getPlayerRanking(@Body PlayerTrixiumRanking paramPlayerTrixiumRanking);
  
  @GET("trixium/factions/{uuid}")
  Call<TrixiumFactionProfile> getFactionTrixium(@Path("uuid") String paramString);
  
  @POST("trixium/factions/ranking")
  Call<List<TrixiumFactionProfile>> getFactionRanking(@Body PlayerTrixiumRanking paramPlayerTrixiumRanking);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\http\PaladiumServices$TRIXIUM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */