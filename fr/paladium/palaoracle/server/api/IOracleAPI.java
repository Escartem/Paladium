package fr.paladium.palaoracle.server.api;

import fr.paladium.palaoracle.core.pojo.PalaOracleEvent;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IOracleAPI {
  @POST("oracle/events/general/capture")
  Call<String> generalCaptureEvent(@Body PalaOracleEvent paramPalaOracleEvent);
}


/* Location:              E:\Paladium\!\fr\paladium\palaoracle\server\api\IOracleAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */