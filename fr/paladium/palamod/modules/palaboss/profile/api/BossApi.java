package fr.paladium.palamod.modules.palaboss.profile.api;

import fr.paladium.palamod.modules.palaboss.profile.dto.BossStats;
import fr.paladium.palamod.modules.palaboss.profile.dto.JobBossStats;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BossApi {
  @GET("player/profile/{uuid}/boss")
  Call<BossStats> getBoss(@Path("uuid") String paramString);
  
  @GET("player/profile/{uuid}/boss/jobs")
  Call<JobBossStats> getJobBoss(@Path("uuid") String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\profile\api\BossApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */