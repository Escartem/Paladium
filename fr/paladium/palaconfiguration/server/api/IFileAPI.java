package fr.paladium.palaconfiguration.server.api;

import fr.paladium.palaconfiguration.server.dto.Environment;
import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IFileAPI {
  @GET("/api/file/list/")
  Call<Map<String, RemoteObject>> getFiles(@Query("environment") Environment paramEnvironment);
  
  @GET("/api/file/get/")
  Call<RemoteObject> getFile(@Query("environment") Environment paramEnvironment, @Query("path") String paramString);
  
  @GET("/api/file/find/")
  Call<RemoteObject> findFile(@Query("environment") Environment paramEnvironment, @Query("path") String paramString);
  
  @GET("/api/file/download/")
  Call<ResponseBody> downloadFile(@Query("environment") Environment paramEnvironment, @Query("path") String paramString);
  
  @POST("/api/file/reload/")
  Call<Void> reload();
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\api\IFileAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */