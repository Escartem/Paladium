package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class HurtBossAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  public HurtBossAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public HurtBossAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public HurtBossAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public HurtBossAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public HurtBossAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public HurtBossAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public HurtBossAchievement build() {
    return new HurtBossAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
  }
  
  public String toString() {
    return "HurtBossAchievement.HurtBossAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\HurtBossAchievement$HurtBossAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */