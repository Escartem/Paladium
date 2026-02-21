package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.block.Block;

public class HammerDuplicateAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private Block block;
  
  public HammerDuplicateAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public HammerDuplicateAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public HammerDuplicateAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public HammerDuplicateAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public HammerDuplicateAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public HammerDuplicateAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public HammerDuplicateAchievementBuilder block(Block block) {
    this.block = block;
    return this;
  }
  
  public HammerDuplicateAchievement build() {
    return new HammerDuplicateAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.block);
  }
  
  public String toString() {
    return "HammerDuplicateAchievement.HammerDuplicateAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", block=" + this.block + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\HammerDuplicateAchievement$HammerDuplicateAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */