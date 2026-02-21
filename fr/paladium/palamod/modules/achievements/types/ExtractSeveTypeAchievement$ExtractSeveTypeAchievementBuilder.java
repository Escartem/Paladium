package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class ExtractSeveTypeAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private String seve;
  
  public ExtractSeveTypeAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public ExtractSeveTypeAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public ExtractSeveTypeAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public ExtractSeveTypeAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public ExtractSeveTypeAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public ExtractSeveTypeAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public ExtractSeveTypeAchievementBuilder seve(String seve) {
    this.seve = seve;
    return this;
  }
  
  public ExtractSeveTypeAchievement build() {
    return new ExtractSeveTypeAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.seve);
  }
  
  public String toString() {
    return "ExtractSeveTypeAchievement.ExtractSeveTypeAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", seve=" + this.seve + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ExtractSeveTypeAchievement$ExtractSeveTypeAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */