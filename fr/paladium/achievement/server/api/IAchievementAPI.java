package fr.paladium.achievement.server.api;

import fr.paladium.achievement.core.pojo.Achievement;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAchievementAPI {
  @POST("/achievements")
  Call<Void> publishAchievement(@Body Achievement paramAchievement);
  
  @POST("/achievements/complete/{id}/{playerUUID}")
  Call<Void> completeAchievement(@Path("id") String paramString1, @Path("playerUUID") String paramString2);
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\server\api\IAchievementAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */