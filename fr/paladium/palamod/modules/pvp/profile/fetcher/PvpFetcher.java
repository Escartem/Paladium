package fr.paladium.palamod.modules.pvp.profile.fetcher;

import fr.paladium.palamod.modules.pvp.dto.PvpStat;
import fr.paladium.palamod.modules.pvp.profile.api.PvpApi;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;

public class PvpFetcher extends DataFetcher<PvpApi, Map<String, PvpStat>> {
  public PvpFetcher(Class<PvpApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public Map<String, PvpStat> fetch(String uuid) {
    Call<List<PvpStat>> call = ((PvpApi)getApi()).getPvp(uuid);
    Map<String, PvpStat> pvpStats = new HashMap<>();
    try {
      Response<List<PvpStat>> response = call.execute();
      if (response.isSuccessful()) {
        List<PvpStat> statList = (List<PvpStat>)response.body();
        if (statList != null)
          statList.forEach(stat -> (PvpStat)pvpStats.put(stat.getScope(), stat)); 
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return pvpStats;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pvp\profile\fetcher\PvpFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */