package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.item.ItemStack;

public class CraftCauldronAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private ItemStack itemStack;
  
  public CraftCauldronAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public CraftCauldronAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public CraftCauldronAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public CraftCauldronAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public CraftCauldronAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public CraftCauldronAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public CraftCauldronAchievementBuilder itemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
    return this;
  }
  
  public CraftCauldronAchievement build() {
    return new CraftCauldronAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack);
  }
  
  public String toString() {
    return "CraftCauldronAchievement.CraftCauldronAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\CraftCauldronAchievement$CraftCauldronAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */