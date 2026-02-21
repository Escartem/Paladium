package fr.paladium.palamod.modules.achievements.types.faction;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class OwnFactionTownAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int quantityRequired;
  
  private String icon;
  
  public OwnFactionTownAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public OwnFactionTownAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public OwnFactionTownAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public OwnFactionTownAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public OwnFactionTownAchievementBuilder quantityRequired(int quantityRequired) {
    this.quantityRequired = quantityRequired;
    return this;
  }
  
  public OwnFactionTownAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public OwnFactionTownAchievement build() {
    return new OwnFactionTownAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon);
  }
  
  public String toString() {
    return "OwnFactionTownAchievement.OwnFactionTownAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\faction\OwnFactionTownAchievement$OwnFactionTownAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */