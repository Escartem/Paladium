package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class InitCauldronAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  public InitCauldronAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public InitCauldronAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public InitCauldronAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public InitCauldronAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public InitCauldronAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public InitCauldronAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public InitCauldronAchievement build() {
    return new InitCauldronAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
  }
  
  public String toString() {
    return "InitCauldronAchievement.InitCauldronAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\InitCauldronAchievement$InitCauldronAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */