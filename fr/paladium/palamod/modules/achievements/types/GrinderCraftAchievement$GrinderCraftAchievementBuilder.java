package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.item.ItemStack;

public class GrinderCraftAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private ItemStack itemStack;
  
  public GrinderCraftAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public GrinderCraftAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public GrinderCraftAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public GrinderCraftAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public GrinderCraftAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public GrinderCraftAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public GrinderCraftAchievementBuilder itemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
    return this;
  }
  
  public GrinderCraftAchievement build() {
    return new GrinderCraftAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack);
  }
  
  public String toString() {
    return "GrinderCraftAchievement.GrinderCraftAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\GrinderCraftAchievement$GrinderCraftAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */