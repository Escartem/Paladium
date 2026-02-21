package fr.paladium.palajobs.server.listener;

import fr.paladium.palajobs.server.managers.JobsManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<String> {
  public void onResponse(Call<String> call, Response<String> response) {
    JobsManager.getInstance().fetchBossDataAsync();
  }
  
  public void onFailure(Call<String> call, Throwable error) {
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\listener\EventsHandler$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */