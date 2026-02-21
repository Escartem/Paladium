package fr.paladium.achievement.core.profile.api;

import fr.paladium.achievement.core.profile.dto.PlayerAchievement;
import fr.paladium.profile.server.requests.model.Paging;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AchievementApi {
  @GET("player/profile/{uuid}/achievements")
  Call<Paging<PlayerAchievement>> getAchievements(@Path("uuid") String paramString, @Query("offset") int paramInt1, @Query("limit") int paramInt2);
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\profile\api\AchievementApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */