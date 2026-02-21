package fr.paladium.palajobs.core.profile.fetcher;

import fr.paladium.palajobs.core.profile.api.JobApi;
import fr.paladium.palajobs.core.profile.dto.PlayerJobs;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import retrofit2.Call;
import retrofit2.Response;

public class JobsFetcher extends DataFetcher<JobApi, PlayerJobs> {
  public JobsFetcher(Class<JobApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public PlayerJobs fetch(String uuid) {
    Call<PlayerJobs> call = ((JobApi)getApi()).getJobs(uuid);
    try {
      Response<PlayerJobs> response = call.execute();
      if (response.isSuccessful())
        return (PlayerJobs)response.body(); 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\profile\fetcher\JobsFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */