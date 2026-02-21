package fr.paladium.palamod.modules.achievements.types.faction;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class FactionReachEloAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int quantityRequired;
  
  private String icon;
  
  private int requiredElo;
  
  public FactionReachEloAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public FactionReachEloAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public FactionReachEloAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public FactionReachEloAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public FactionReachEloAchievementBuilder quantityRequired(int quantityRequired) {
    this.quantityRequired = quantityRequired;
    return this;
  }
  
  public FactionReachEloAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public FactionReachEloAchievementBuilder requiredElo(int requiredElo) {
    this.requiredElo = requiredElo;
    return this;
  }
  
  public FactionReachEloAchievement build() {
    return new FactionReachEloAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon, this.requiredElo);
  }
  
  public String toString() {
    return "FactionReachEloAchievement.FactionReachEloAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ", requiredElo=" + this.requiredElo + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\faction\FactionReachEloAchievement$FactionReachEloAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */