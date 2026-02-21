package fr.paladium.achievement.core.profile.fetcher;

import fr.paladium.achievement.core.profile.api.AchievementApi;
import fr.paladium.achievement.core.profile.dto.PlayerAchievement;
import fr.paladium.profile.server.fetcher.DataFetcher;
import fr.paladium.profile.server.requests.model.Paging;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class PlayerAchievementFetcher extends DataFetcher<AchievementApi, List<PlayerAchievement>> {
  private static final int LIMIT = 500;
  
  public PlayerAchievementFetcher(Class<AchievementApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public List<PlayerAchievement> fetch(String uuid) {
    List<PlayerAchievement> achievements = new ArrayList<>();
    int offset = 0;
    int totalCount = -1;
    while (totalCount == -1 || achievements.size() < totalCount) {
      Call<Paging<PlayerAchievement>> call = ((AchievementApi)getApi()).getAchievements(uuid, offset, 500);
      try {
        Response<Paging<PlayerAchievement>> response = call.execute();
        if (response.isSuccessful()) {
          Paging<PlayerAchievement> paging = (Paging<PlayerAchievement>)response.body();
          if (paging == null || paging.getData() == null || paging.getData().isEmpty())
            break; 
          achievements.addAll(paging.getData());
          totalCount = paging.getTotalCount();
          offset += 500;
        } 
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    return achievements;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\profile\fetcher\PlayerAchievementFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */