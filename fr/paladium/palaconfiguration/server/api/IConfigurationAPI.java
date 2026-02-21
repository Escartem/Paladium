package fr.paladium.palaconfiguration.server.api;

import fr.paladium.palaconfiguration.server.dto.Environment;
import fr.paladium.palaconfiguration.server.dto.config.Config;
import fr.paladium.palaconfiguration.server.dto.config.request.ConfigReloadRequest;
import fr.paladium.palaconfiguration.server.dto.config.response.ConfigRefreshResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IConfigurationAPI {
  @GET("/api/config/find/")
  Call<Config> findOne(@Query("path") String paramString, @Query("environment") Environment paramEnvironment);
  
  @GET("/api/config/find-all")
  Call<List<Config>> findAllConfigs();
  
  @POST("/api/config/reload")
  Call<Config> reloadConfig(@Body ConfigReloadRequest paramConfigReloadRequest);
  
  @POST("/api/config/reload-all")
  Call<List<Config>> reloadAllConfigs();
  
  @POST("/api/config/refresh-all")
  Call<List<ConfigRefreshResponse>> refreshAll(@Query("environment") Environment paramEnvironment);
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\api\IConfigurationAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */