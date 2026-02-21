package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class EggHuntActionAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private int action;
  
  public EggHuntActionAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public EggHuntActionAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public EggHuntActionAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public EggHuntActionAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public EggHuntActionAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public EggHuntActionAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public EggHuntActionAchievementBuilder action(int action) {
    this.action = action;
    return this;
  }
  
  public EggHuntActionAchievement build() {
    return new EggHuntActionAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.action);
  }
  
  public String toString() {
    return "EggHuntActionAchievement.EggHuntActionAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", action=" + this.action + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\EggHuntActionAchievement$EggHuntActionAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */