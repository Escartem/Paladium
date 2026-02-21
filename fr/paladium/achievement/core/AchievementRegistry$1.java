package fr.paladium.achievement.core;

import fr.paladium.achievement.AchievementLogger;
import fr.paladium.achievement.core.pojo.Achievement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class null implements Callback<Void> {
  public void onResponse(Call<Void> call, Response<Void> response) {
    if (!response.isSuccessful())
      AchievementLogger.error("Failed to publish achievement " + achievement.getId() + ": " + response.code()); 
  }
  
  public void onFailure(Call<Void> call, Throwable throwable) {
    AchievementLogger.error("Failed to publish achievement " + achievement.getId());
    throwable.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\AchievementRegistry$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */