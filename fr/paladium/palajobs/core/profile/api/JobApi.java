package fr.paladium.palajobs.core.profile.api;

import fr.paladium.palajobs.core.profile.dto.PlayerJob;
import fr.paladium.palajobs.core.profile.dto.PlayerJobs;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JobApi {
  @GET("player/profile/{uuid}/jobs")
  Call<PlayerJobs> getJobs(@Path("uuid") String paramString);
  
  @GET("player/profile/{uuid}/jobs/history")
  Call<Map<String, Map<String, PlayerJob>>> getJobHistory(@Path("uuid") String paramString);
  
  @GET("ranking/position/{uuid}")
  Call<Map<String, Long>> getRankPositions(@Path("uuid") String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\profile\api\JobApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */