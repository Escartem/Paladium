package fr.paladium.palajobs.server.managers;

import fr.paladium.palajobs.core.pojo.boss.BossData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<BossData> {
  public void onResponse(Call<BossData> call, Response<BossData> response) {
    JobsManager.access$002(JobsManager.this, (BossData)response.body());
  }
  
  public void onFailure(Call<BossData> call, Throwable error) {
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\managers\JobsManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */