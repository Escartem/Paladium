package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class MultiAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private String icon;
  
  public MultiAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public MultiAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public MultiAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public MultiAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public MultiAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public MultiAchievement build() {
    return new MultiAchievement(this.id, this.category, this.name, this.description, this.icon);
  }
  
  public String toString() {
    return "MultiAchievement.MultiAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\MultiAchievement$MultiAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */