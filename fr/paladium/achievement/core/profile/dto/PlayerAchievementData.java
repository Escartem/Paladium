package fr.paladium.achievement.core.profile.dto;

import java.util.List;

public class PlayerAchievementData {
  private final List<PlayerAchievement> achievements;
  
  public PlayerAchievementData(List<PlayerAchievement> achievements) {
    this.achievements = achievements;
  }
  
  public List<PlayerAchievement> getAchievements() {
    return this.achievements;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\profile\dto\PlayerAchievementData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */