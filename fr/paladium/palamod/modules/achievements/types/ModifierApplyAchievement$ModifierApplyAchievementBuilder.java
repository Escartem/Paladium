package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.item.ItemStack;

public class ModifierApplyAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int nbToValidate;
  
  private String icon;
  
  private ItemStack itemStack;
  
  private ItemStack modifier;
  
  public ModifierApplyAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public ModifierApplyAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public ModifierApplyAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public ModifierApplyAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public ModifierApplyAchievementBuilder nbToValidate(int nbToValidate) {
    this.nbToValidate = nbToValidate;
    return this;
  }
  
  public ModifierApplyAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public ModifierApplyAchievementBuilder itemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
    return this;
  }
  
  public ModifierApplyAchievementBuilder modifier(ItemStack modifier) {
    this.modifier = modifier;
    return this;
  }
  
  public ModifierApplyAchievement build() {
    return new ModifierApplyAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack, this.modifier);
  }
  
  public String toString() {
    return "ModifierApplyAchievement.ModifierApplyAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ", modifier=" + this.modifier + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ModifierApplyAchievement$ModifierApplyAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */