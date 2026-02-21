package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class BreakSpawnerFinderAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  public BreakSpawnerFinderAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public BreakSpawnerFinderAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public BreakSpawnerFinderAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public BreakSpawnerFinderAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public BreakSpawnerFinderAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public BreakSpawnerFinderAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public BreakSpawnerFinderAchievement build() {
    return new BreakSpawnerFinderAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
  }
  
  public String toString() {
    return "BreakSpawnerFinderAchievement.BreakSpawnerFinderAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\BreakSpawnerFinderAchievement$BreakSpawnerFinderAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */