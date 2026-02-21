package fr.paladium.palajobs.core.achievements;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class PalaJobsFarmingPlantAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  public PalaJobsFarmingPlantAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public PalaJobsFarmingPlantAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public PalaJobsFarmingPlantAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public PalaJobsFarmingPlantAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public PalaJobsFarmingPlantAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public PalaJobsFarmingPlantAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public PalaJobsFarmingPlantAchievement build() {
    return new PalaJobsFarmingPlantAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
  }
  
  public String toString() {
    return "PalaJobsFarmingPlantAchievement.PalaJobsFarmingPlantAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\achievements\PalaJobsFarmingPlantAchievement$PalaJobsFarmingPlantAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */