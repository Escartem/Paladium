package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.item.ItemStack;

public class EnchantItemAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private ItemStack itemStack;
  
  public EnchantItemAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public EnchantItemAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public EnchantItemAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public EnchantItemAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public EnchantItemAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public EnchantItemAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public EnchantItemAchievementBuilder itemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
    return this;
  }
  
  public EnchantItemAchievement build() {
    return new EnchantItemAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack);
  }
  
  public String toString() {
    return "EnchantItemAchievement.EnchantItemAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\EnchantItemAchievement$EnchantItemAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */