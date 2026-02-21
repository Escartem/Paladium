package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class SpellActionAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private int action;
  
  public SpellActionAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public SpellActionAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public SpellActionAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public SpellActionAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public SpellActionAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public SpellActionAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public SpellActionAchievementBuilder action(int action) {
    this.action = action;
    return this;
  }
  
  public SpellActionAchievement build() {
    return new SpellActionAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.action);
  }
  
  public String toString() {
    return "SpellActionAchievement.SpellActionAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", action=" + this.action + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\SpellActionAchievement$SpellActionAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */