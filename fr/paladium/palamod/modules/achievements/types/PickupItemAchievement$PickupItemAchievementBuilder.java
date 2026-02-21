package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.item.ItemStack;

public class PickupItemAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int quantityRequired;
  
  private String icon;
  
  private ItemStack item;
  
  public PickupItemAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public PickupItemAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public PickupItemAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public PickupItemAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public PickupItemAchievementBuilder quantityRequired(int quantityRequired) {
    this.quantityRequired = quantityRequired;
    return this;
  }
  
  public PickupItemAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public PickupItemAchievementBuilder item(ItemStack item) {
    this.item = item;
    return this;
  }
  
  public PickupItemAchievement build() {
    return new PickupItemAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon, this.item);
  }
  
  public String toString() {
    return "PickupItemAchievement.PickupItemAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ", item=" + this.item + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\PickupItemAchievement$PickupItemAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */