package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import net.minecraft.item.Item;

public class ArmorSetEquipAchievementBuilder {
  private String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  private int quantityRequired;
  
  private String icon;
  
  private Item helmet;
  
  private Item chestplate;
  
  private Item leggings;
  
  private Item boots;
  
  public ArmorSetEquipAchievementBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public ArmorSetEquipAchievementBuilder category(AchievementCategory category) {
    this.category = category;
    return this;
  }
  
  public ArmorSetEquipAchievementBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public ArmorSetEquipAchievementBuilder description(String description) {
    this.description = description;
    return this;
  }
  
  public ArmorSetEquipAchievementBuilder quantityRequired(int quantityRequired) {
    this.quantityRequired = quantityRequired;
    return this;
  }
  
  public ArmorSetEquipAchievementBuilder icon(String icon) {
    this.icon = icon;
    return this;
  }
  
  public ArmorSetEquipAchievementBuilder helmet(Item helmet) {
    this.helmet = helmet;
    return this;
  }
  
  public ArmorSetEquipAchievementBuilder chestplate(Item chestplate) {
    this.chestplate = chestplate;
    return this;
  }
  
  public ArmorSetEquipAchievementBuilder leggings(Item leggings) {
    this.leggings = leggings;
    return this;
  }
  
  public ArmorSetEquipAchievementBuilder boots(Item boots) {
    this.boots = boots;
    return this;
  }
  
  public ArmorSetEquipAchievement build() {
    return new ArmorSetEquipAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon, this.helmet, this.chestplate, this.leggings, this.boots);
  }
  
  public String toString() {
    return "ArmorSetEquipAchievement.ArmorSetEquipAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ", helmet=" + this.helmet + ", chestplate=" + this.chestplate + ", leggings=" + this.leggings + ", boots=" + this.boots + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ArmorSetEquipAchievement$ArmorSetEquipAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */