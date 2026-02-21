package fr.paladium.achievement.core.profile.dto;

public class PlayerAchievement {
  private final String id;
  
  private final int progress;
  
  private final boolean completed;
  
  public PlayerAchievement(String id, int progress, boolean completed) {
    this.id = id;
    this.progress = progress;
    this.completed = completed;
  }
  
  public String getId() {
    return this.id;
  }
  
  public int getProgress() {
    return this.progress;
  }
  
  public boolean isCompleted() {
    return this.completed;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\profile\dto\PlayerAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */