package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class VisitServerAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private String serverName;
  
  public VisitServerAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public VisitServerAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public VisitServerAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public VisitServerAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public VisitServerAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public VisitServerAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public VisitServerAchievementBuilder serverName(String serverName) {
    this.serverName = serverName;
    return this;
  }
  
  public VisitServerAchievement build() {
    return new VisitServerAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.serverName);
  }
  
  public String toString() {
    return "VisitServerAchievement.VisitServerAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", serverName=" + this.serverName + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\VisitServerAchievement$VisitServerAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */