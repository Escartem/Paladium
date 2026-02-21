package fr.paladium.palajobs.core.profile.fetcher;

import fr.paladium.palajobs.core.profile.api.JobApi;
import fr.paladium.palajobs.core.profile.dto.PlayerJobRank;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;

public class JobRankFetcher extends DataFetcher<JobApi, PlayerJobRank> {
  public JobRankFetcher(Class<JobApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public PlayerJobRank fetch(String uuid) {
    Call<Map<String, Long>> call = ((JobApi)getApi()).getRankPositions(uuid);
    try {
      Response<Map<String, Long>> response = call.execute();
      if (response.isSuccessful() && response.body() != null)
        return new PlayerJobRank((Map)response.body()); 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\profile\fetcher\JobRankFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */