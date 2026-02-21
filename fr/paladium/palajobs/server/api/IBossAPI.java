package fr.paladium.palajobs.server.api;

import fr.paladium.palajobs.core.pojo.boss.BossData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IBossAPI {
  @GET("events/jobs")
  Call<BossData> getBossData();
  
  @GET("events/jobs/add/{amount}")
  Call<String> add(@Path("amount") int paramInt);
  
  @GET("events/jobs/spawn")
  Call<String> spawn();
  
  @GET("events/jobs/finish")
  Call<String> finish();
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\api\IBossAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */