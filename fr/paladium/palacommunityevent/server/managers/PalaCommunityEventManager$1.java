package fr.paladium.palacommunityevent.server.managers;

import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<String> {
  public void onResponse(Call<String> callPlayerQuest, Response<String> response) {
    if (!response.isSuccessful()) {
      call.onFail("", new Throwable("Unexcepted response | Code: " + response.code()));
      return;
    } 
    if (response == null || response.body() == null) {
      call.onFail("", new Throwable("Unexcepted response | Code: " + response.code()));
      return;
    } 
    call.onSuccess(response.body());
  }
  
  public void onFailure(Call<String> callPlayerQuest, Throwable error) {
    call.onFail(null, error);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\server\managers\PalaCommunityEventManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */