package fr.paladium.palacommunityevent.server.managers;

import fr.paladium.palacommunityevent.server.api.pojo.EventCommunityData;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<EventCommunityData> {
  public void onResponse(Call<EventCommunityData> callPlayerQuest, Response<EventCommunityData> response) {
    if (!response.isSuccessful()) {
      call.onFail(new EventCommunityData(), new Throwable("Unexcepted response | Code: " + response.code()));
      return;
    } 
    if (response == null || response.body() == null) {
      call.onFail(new EventCommunityData(), new Throwable("Unexcepted response | Code: " + response.code()));
      return;
    } 
    call.onSuccess(response.body());
  }
  
  public void onFailure(Call<EventCommunityData> callPlayerQuest, Throwable error) {
    call.onFail(null, error);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\server\managers\PalaCommunityEventManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */