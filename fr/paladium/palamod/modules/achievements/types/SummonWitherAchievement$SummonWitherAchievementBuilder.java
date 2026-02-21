package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class SummonWitherAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int quantityRequired;
  
  private String icon;
  
  public SummonWitherAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public SummonWitherAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public SummonWitherAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public SummonWitherAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public SummonWitherAchievementBuilder quantityRequired(int quantityRequired) {
    this.quantityRequired = quantityRequired;
    return this;
  }
  
  public SummonWitherAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public SummonWitherAchievement build() {
    return new SummonWitherAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon);
  }
  
  public String toString() {
    return "SummonWitherAchievement.SummonWitherAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\SummonWitherAchievement$SummonWitherAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */