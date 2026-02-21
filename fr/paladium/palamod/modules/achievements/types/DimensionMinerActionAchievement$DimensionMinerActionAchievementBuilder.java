package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class DimensionMinerActionAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private int action;
  
  public DimensionMinerActionAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public DimensionMinerActionAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public DimensionMinerActionAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public DimensionMinerActionAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public DimensionMinerActionAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public DimensionMinerActionAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public DimensionMinerActionAchievementBuilder action(int action) {
    this.action = action;
    return this;
  }
  
  public DimensionMinerActionAchievement build() {
    return new DimensionMinerActionAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.action);
  }
  
  public String toString() {
    return "DimensionMinerActionAchievement.DimensionMinerActionAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", action=" + this.action + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\DimensionMinerActionAchievement$DimensionMinerActionAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */