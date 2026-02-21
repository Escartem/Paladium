package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class PetReachLevelAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private int requiredLevel;
  
  public PetReachLevelAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public PetReachLevelAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public PetReachLevelAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public PetReachLevelAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public PetReachLevelAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public PetReachLevelAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public PetReachLevelAchievementBuilder requiredLevel(int requiredLevel) {
    this.requiredLevel = requiredLevel;
    return this;
  }
  
  public PetReachLevelAchievement build() {
    return new PetReachLevelAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.requiredLevel);
  }
  
  public String toString() {
    return "PetReachLevelAchievement.PetReachLevelAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", requiredLevel=" + this.requiredLevel + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\PetReachLevelAchievement$PetReachLevelAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */