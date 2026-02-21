package fr.paladium.palamod.modules.palaboss.profile.fetcher;

import fr.paladium.palamod.modules.palaboss.profile.api.BossApi;
import fr.paladium.palamod.modules.palaboss.profile.dto.JobBossStats;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import retrofit2.Call;
import retrofit2.Response;

public class JobBossFetcher extends DataFetcher<BossApi, JobBossStats> {
  public JobBossFetcher(Class<BossApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public JobBossStats fetch(String uuid) {
    Call<JobBossStats> call = ((BossApi)getApi()).getJobBoss(uuid);
    try {
      Response<JobBossStats> response = call.execute();
      if (response.isSuccessful()) {
        JobBossStats bossStats = (JobBossStats)response.body();
        return (bossStats != null) ? bossStats : new JobBossStats();
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return new JobBossStats();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\profile\fetcher\JobBossFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */