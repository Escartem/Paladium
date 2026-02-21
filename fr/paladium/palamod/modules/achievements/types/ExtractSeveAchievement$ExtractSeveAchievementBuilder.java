package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class ExtractSeveAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  public ExtractSeveAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public ExtractSeveAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public ExtractSeveAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public ExtractSeveAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public ExtractSeveAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public ExtractSeveAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public ExtractSeveAchievement build() {
    return new ExtractSeveAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
  }
  
  public String toString() {
    return "ExtractSeveAchievement.ExtractSeveAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ExtractSeveAchievement$ExtractSeveAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */