package fr.paladium.palamod.modules.achievements.types.faction;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class FactionClaimAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int quantityRequired;
  
  private String icon;
  
  public FactionClaimAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public FactionClaimAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public FactionClaimAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public FactionClaimAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public FactionClaimAchievementBuilder quantityRequired(int quantityRequired) {
    this.quantityRequired = quantityRequired;
    return this;
  }
  
  public FactionClaimAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public FactionClaimAchievement build() {
    return new FactionClaimAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon);
  }
  
  public String toString() {
    return "FactionClaimAchievement.FactionClaimAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\faction\FactionClaimAchievement$FactionClaimAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */