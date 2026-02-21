package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class UseUnclaimFinderAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  public UseUnclaimFinderAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public UseUnclaimFinderAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public UseUnclaimFinderAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public UseUnclaimFinderAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public UseUnclaimFinderAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public UseUnclaimFinderAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public UseUnclaimFinderAchievement build() {
    return new UseUnclaimFinderAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
  }
  
  public String toString() {
    return "UseUnclaimFinderAchievement.UseUnclaimFinderAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\UseUnclaimFinderAchievement$UseUnclaimFinderAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */