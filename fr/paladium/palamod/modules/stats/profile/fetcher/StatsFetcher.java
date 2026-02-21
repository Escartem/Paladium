package fr.paladium.palamod.modules.stats.profile.fetcher;

import fr.paladium.palamod.modules.stats.dto.PlayerStats;
import fr.paladium.palamod.modules.stats.profile.api.StatsApi;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import retrofit2.Call;
import retrofit2.Response;

public class StatsFetcher extends DataFetcher<StatsApi, PlayerStats> {
  public StatsFetcher(Class<StatsApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public PlayerStats fetch(String uuid) {
    Call<PlayerStats> call = ((StatsApi)getApi()).getStats(uuid);
    try {
      Response<PlayerStats> response = call.execute();
      if (response.isSuccessful()) {
        PlayerStats stats = (PlayerStats)response.body();
        return (stats != null) ? stats : new PlayerStats();
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return new PlayerStats();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\stats\profile\fetcher\StatsFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */