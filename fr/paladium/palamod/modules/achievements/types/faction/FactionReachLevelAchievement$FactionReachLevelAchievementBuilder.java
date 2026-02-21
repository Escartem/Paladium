package fr.paladium.palamod.modules.achievements.types.faction;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class FactionReachLevelAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int quantityRequired;
  
  private String icon;
  
  private int requiredLevel;
  
  public FactionReachLevelAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public FactionReachLevelAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public FactionReachLevelAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public FactionReachLevelAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public FactionReachLevelAchievementBuilder quantityRequired(int quantityRequired) {
    this.quantityRequired = quantityRequired;
    return this;
  }
  
  public FactionReachLevelAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public FactionReachLevelAchievementBuilder requiredLevel(int requiredLevel) {
    this.requiredLevel = requiredLevel;
    return this;
  }
  
  public FactionReachLevelAchievement build() {
    return new FactionReachLevelAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon, this.requiredLevel);
  }
  
  public String toString() {
    return "FactionReachLevelAchievement.FactionReachLevelAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ", requiredLevel=" + this.requiredLevel + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\faction\FactionReachLevelAchievement$FactionReachLevelAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */