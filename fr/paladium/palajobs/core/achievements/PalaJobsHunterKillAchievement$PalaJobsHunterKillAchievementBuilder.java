package fr.paladium.palajobs.core.achievements;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class PalaJobsHunterKillAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  public PalaJobsHunterKillAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public PalaJobsHunterKillAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public PalaJobsHunterKillAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public PalaJobsHunterKillAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public PalaJobsHunterKillAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public PalaJobsHunterKillAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public PalaJobsHunterKillAchievement build() {
    return new PalaJobsHunterKillAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
  }
  
  public String toString() {
    return "PalaJobsHunterKillAchievement.PalaJobsHunterKillAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\achievements\PalaJobsHunterKillAchievement$PalaJobsHunterKillAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */