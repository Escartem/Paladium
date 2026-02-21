package fr.paladium.achievement.core.profile;

import fr.paladium.achievement.client.profile.gui.UIProfileAchievements;
import fr.paladium.achievement.core.profile.api.AchievementApi;
import fr.paladium.achievement.core.profile.dto.PlayerAchievementData;
import fr.paladium.achievement.core.profile.fetcher.PlayerAchievementFetcher;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModuleAchievement extends AModule<PlayerAchievementData> {
  public static final String NAME = "achievements";
  
  private PlayerAchievementFetcher playerAchievementFetcher;
  
  public ModuleAchievement() {
    super("achievements");
  }
  
  public boolean hasVisibilitySettings() {
    return true;
  }
  
  public void registerFetchers() {
    this.playerAchievementFetcher = new PlayerAchievementFetcher(AchievementApi.class);
  }
  
  public CompletableFuture<PlayerAchievementData> fetch(String uuid) {
    CompletableFuture<PlayerAchievementData> future = new CompletableFuture<>();
    this.playerAchievementFetcher
      .fetchAsync(uuid)
      .thenAccept(list -> future.complete(new PlayerAchievementData(list)))
      .exceptionally(throwable -> {
          throwable.printStackTrace();
          return null;
        });
    return future;
  }
  
  public Class<? extends UI> getUI() {
    return (Class)UIProfileAchievements.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\profile\ModuleAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */