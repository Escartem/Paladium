package fr.paladium.palamod.modules.paladium.common.legendarystone.fetcher;

import fr.paladium.palamod.modules.paladium.common.legendarystone.api.LegendaryStoneApi;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;

public class LegendaryStoneFetcher extends DataFetcher<LegendaryStoneApi, Map<String, Long>> {
  public LegendaryStoneFetcher(Class<LegendaryStoneApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public Map<String, Long> fetch(String uuid) {
    Call<Map<String, Long>> call = ((LegendaryStoneApi)getApi()).getLegendaryStone(uuid);
    try {
      Response<Map<String, Long>> response = call.execute();
      if (response.isSuccessful()) {
        Map<String, Long> legendaryStones = (Map<String, Long>)response.body();
        if (legendaryStones == null)
          return new HashMap<>(); 
        return legendaryStones;
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return new HashMap<>();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\legendarystone\fetcher\LegendaryStoneFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */