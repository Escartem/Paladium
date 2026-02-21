package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class CresusWithdrawAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int quantityRequired;
  
  private String icon;
  
  public CresusWithdrawAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public CresusWithdrawAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public CresusWithdrawAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public CresusWithdrawAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public CresusWithdrawAchievementBuilder quantityRequired(int quantityRequired) {
    this.quantityRequired = quantityRequired;
    return this;
  }
  
  public CresusWithdrawAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public CresusWithdrawAchievement build() {
    return new CresusWithdrawAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon);
  }
  
  public String toString() {
    return "CresusWithdrawAchievement.CresusWithdrawAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\CresusWithdrawAchievement$CresusWithdrawAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */