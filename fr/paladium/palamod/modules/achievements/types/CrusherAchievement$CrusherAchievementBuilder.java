package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.item.ItemStack;

public class CrusherAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private ItemStack itemStack;
  
  public CrusherAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public CrusherAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public CrusherAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public CrusherAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public CrusherAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public CrusherAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public CrusherAchievementBuilder itemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
    return this;
  }
  
  public CrusherAchievement build() {
    return new CrusherAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack);
  }
  
  public String toString() {
    return "CrusherAchievement.CrusherAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\CrusherAchievement$CrusherAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */