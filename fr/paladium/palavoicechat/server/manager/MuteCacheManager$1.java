package fr.paladium.palavoicechat.server.manager;

import fr.paladium.palavoicechat.server.api.pojo.PlayerPunishment;
import java.util.UUID;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<PlayerPunishment> {
  public void onResponse(Call<PlayerPunishment> call, Response<PlayerPunishment> response) {
    if (response.isSuccessful())
      MuteCacheManager.access$000().put(playerUUID, Boolean.valueOf(true)); 
  }
  
  public void onFailure(Call<PlayerPunishment> call, Throwable t) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\manager\MuteCacheManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */