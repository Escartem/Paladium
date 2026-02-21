package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.item.ItemStack;

public class UseItemAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private ItemStack itemStack;
  
  private String discriminator;
  
  public UseItemAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public UseItemAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public UseItemAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public UseItemAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public UseItemAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public UseItemAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public UseItemAchievementBuilder itemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
    return this;
  }
  
  public UseItemAchievementBuilder discriminator(String discriminator) {
    this.discriminator = discriminator;
    return this;
  }
  
  public UseItemAchievement build() {
    return new UseItemAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack, this.discriminator);
  }
  
  public String toString() {
    return "UseItemAchievement.UseItemAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ", discriminator=" + this.discriminator + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\UseItemAchievement$UseItemAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */