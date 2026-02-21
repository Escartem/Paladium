package fr.paladium.palapass.common.achievement;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class PalapassReachPointsAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private int points;
  
  public PalapassReachPointsAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public PalapassReachPointsAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public PalapassReachPointsAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public PalapassReachPointsAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public PalapassReachPointsAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public PalapassReachPointsAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public PalapassReachPointsAchievementBuilder points(int points) {
    this.points = points;
    return this;
  }
  
  public PalapassReachPointsAchievement build() {
    return new PalapassReachPointsAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.points);
  }
  
  public String toString() {
    return "PalapassReachPointsAchievement.PalapassReachPointsAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", points=" + this.points + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\achievement\PalapassReachPointsAchievement$PalapassReachPointsAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */