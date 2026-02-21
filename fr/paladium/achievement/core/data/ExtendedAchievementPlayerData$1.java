package fr.paladium.achievement.core.data;

import fr.paladium.achievement.core.pojo.Achievement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<Void> {
  public void onResponse(Call<Void> call, Response<Void> response) {
    if (!response.isSuccessful())
      System.out.println("Failed to complete achievement " + achievement.getId() + ": " + response.code()); 
  }
  
  public void onFailure(Call<Void> call, Throwable throwable) {
    System.out.println("Failed to complete achievement " + achievement.getId() + ": " + throwable.getMessage());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\data\ExtendedAchievementPlayerData$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */