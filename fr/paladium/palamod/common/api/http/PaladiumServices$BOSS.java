package fr.paladium.palamod.common.api.http;

import fr.paladium.palamod.modules.palaboss.common.utils.BossCommonConfig;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BOSS {
  @GET("boss/config")
  Call<BossCommonConfig> getConfig();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\http\PaladiumServices$BOSS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */