package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class PlayerLevelupAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private int level;
  
  public PlayerLevelupAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public PlayerLevelupAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public PlayerLevelupAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public PlayerLevelupAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public PlayerLevelupAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public PlayerLevelupAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public PlayerLevelupAchievementBuilder level(int level) {
    this.level = level;
    return this;
  }
  
  public PlayerLevelupAchievement build() {
    return new PlayerLevelupAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.level);
  }
  
  public String toString() {
    return "PlayerLevelupAchievement.PlayerLevelupAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", level=" + this.level + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\PlayerLevelupAchievement$PlayerLevelupAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */