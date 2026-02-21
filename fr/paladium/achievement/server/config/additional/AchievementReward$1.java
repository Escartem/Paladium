package fr.paladium.achievement.server.config.additional;

import fr.paladium.achievement.AchievementLogger;
import net.minecraft.entity.player.EntityPlayerMP;
import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class null implements Callback<Void> {
  @EverythingIsNonNull
  public void onResponse(Call<Void> call, Response<Void> response) {}
  
  @EverythingIsNonNull
  public void onFailure(Call<Void> call, Throwable throwable) {
    AchievementLogger.error("Failed to give badge to player " + player.func_70005_c_() + " with badge " + AchievementReward.access$000(AchievementReward.this));
    throwable.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\server\config\additional\AchievementReward$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */