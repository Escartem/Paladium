package fr.paladium.palamod.modules.paladium.common.legendarystone.api;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LegendaryStoneApi {
  @GET("player/profile/{uuid}/legendarystone")
  Call<Map<String, Long>> getLegendaryStone(@Path("uuid") String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\legendarystone\api\LegendaryStoneApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */