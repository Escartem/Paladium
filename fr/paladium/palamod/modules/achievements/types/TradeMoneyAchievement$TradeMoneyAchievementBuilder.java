package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class TradeMoneyAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int quantityRequired;
  
  private String icon;
  
  public TradeMoneyAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public TradeMoneyAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public TradeMoneyAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public TradeMoneyAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public TradeMoneyAchievementBuilder quantityRequired(int quantityRequired) {
    this.quantityRequired = quantityRequired;
    return this;
  }
  
  public TradeMoneyAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public TradeMoneyAchievement build() {
    return new TradeMoneyAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon);
  }
  
  public String toString() {
    return "TradeMoneyAchievement.TradeMoneyAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\TradeMoneyAchievement$TradeMoneyAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */