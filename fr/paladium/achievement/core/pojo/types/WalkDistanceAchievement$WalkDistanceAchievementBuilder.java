package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class WalkDistanceAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  public WalkDistanceAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public WalkDistanceAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public WalkDistanceAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public WalkDistanceAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public WalkDistanceAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public WalkDistanceAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public WalkDistanceAchievement build() {
    return new WalkDistanceAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
  }
  
  public String toString() {
    return "WalkDistanceAchievement.WalkDistanceAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\WalkDistanceAchievement$WalkDistanceAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */