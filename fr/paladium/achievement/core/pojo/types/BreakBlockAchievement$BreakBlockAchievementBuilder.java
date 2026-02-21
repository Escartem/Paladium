package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.block.Block;

public class BreakBlockAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private Block block;
  
  public BreakBlockAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public BreakBlockAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public BreakBlockAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public BreakBlockAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public BreakBlockAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public BreakBlockAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public BreakBlockAchievementBuilder block(Block block) {
    this.block = block;
    return this;
  }
  
  public BreakBlockAchievement build() {
    return new BreakBlockAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.block);
  }
  
  public String toString() {
    return "BreakBlockAchievement.BreakBlockAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", block=" + this.block + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\BreakBlockAchievement$BreakBlockAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */