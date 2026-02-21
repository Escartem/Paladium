package fr.paladium.palacommunityevent.server.api;

import fr.paladium.palacommunityevent.server.api.inputs.ProgressInput;
import fr.paladium.palacommunityevent.server.api.pojo.EventCommunityData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICommunityEventAPI {
  @GET("events/communityevent/{eventId}")
  Call<EventCommunityData> getEventDataById(@Path("eventId") String paramString);
  
  @POST("events/communityevent/progress")
  Call<String> progressEvent(@Body ProgressInput paramProgressInput);
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\server\api\ICommunityEventAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */