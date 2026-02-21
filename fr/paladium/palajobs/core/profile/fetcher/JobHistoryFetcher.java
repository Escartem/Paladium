package fr.paladium.palajobs.core.profile.fetcher;

import fr.paladium.palajobs.core.profile.api.JobApi;
import fr.paladium.palajobs.core.profile.dto.PlayerJob;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;

public class JobHistoryFetcher extends DataFetcher<JobApi, Map<String, Map<String, PlayerJob>>> {
  public JobHistoryFetcher(Class<JobApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public Map<String, Map<String, PlayerJob>> fetch(String uuid) {
    Call<Map<String, Map<String, PlayerJob>>> call = ((JobApi)getApi()).getJobHistory(uuid);
    try {
      Response<Map<String, Map<String, PlayerJob>>> response = call.execute();
      if (response.isSuccessful())
        return (Map<String, Map<String, PlayerJob>>)response.body(); 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\profile\fetcher\JobHistoryFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */