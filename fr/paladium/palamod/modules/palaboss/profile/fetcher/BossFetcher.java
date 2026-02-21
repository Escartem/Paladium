package fr.paladium.palamod.modules.palaboss.profile.fetcher;

import fr.paladium.palamod.modules.palaboss.profile.api.BossApi;
import fr.paladium.palamod.modules.palaboss.profile.dto.BossStats;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import retrofit2.Call;
import retrofit2.Response;

public class BossFetcher extends DataFetcher<BossApi, BossStats> {
  public BossFetcher(Class<BossApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public BossStats fetch(String uuid) {
    Call<BossStats> call = ((BossApi)getApi()).getBoss(uuid);
    try {
      Response<BossStats> response = call.execute();
      if (response.isSuccessful()) {
        BossStats bossStats = (BossStats)response.body();
        return (bossStats != null) ? bossStats : new BossStats();
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return new BossStats();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\profile\fetcher\BossFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */