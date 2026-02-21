package fr.paladium.palamod.common.api.http;

import fr.paladium.palamod.modules.egghunt.utils.EggHuntCommonConfig;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerEggInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerNameInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntStatus;
import fr.paladium.palamod.modules.egghunt.utils.PlayerEggHuntRanked;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EGGHUNT {
  @GET("egghunt/status")
  Call<EggHuntStatus> getStatus();
  
  @GET("egghunt/config")
  Call<EggHuntCommonConfig> getConfig();
  
  @POST("egghunt/owner")
  Call<Void> setOwner(@Body EggHuntPlayerEggInput paramEggHuntPlayerEggInput);
  
  @POST("egghunt/killer")
  Call<Void> setKiller(@Body EggHuntPlayerNameInput paramEggHuntPlayerNameInput);
  
  @GET("egghunt/players/{uuid}")
  Call<PlayerEggHuntRanked> getPlayerRank(@Path("uuid") String paramString);
  
  @GET("egghunt/players/top")
  Call<List<PlayerEggHuntRanked>> getRanking();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\http\PaladiumServices$EGGHUNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */