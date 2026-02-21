package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class CobbleBreakerAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  public CobbleBreakerAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public CobbleBreakerAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public CobbleBreakerAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public CobbleBreakerAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public CobbleBreakerAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public CobbleBreakerAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public CobbleBreakerAchievement build() {
    return new CobbleBreakerAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
  }
  
  public String toString() {
    return "CobbleBreakerAchievement.CobbleBreakerAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\CobbleBreakerAchievement$CobbleBreakerAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */