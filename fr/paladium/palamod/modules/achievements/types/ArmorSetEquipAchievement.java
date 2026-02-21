package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArmorSetEquipAchievement extends Achievement {
  public static final int QUANTITY = 1;
  
  private Item helmet;
  
  private Item chestplate;
  
  private Item leggings;
  
  private Item boots;
  
  public void setHelmet(Item helmet) {
    this.helmet = helmet;
  }
  
  public void setChestplate(Item chestplate) {
    this.chestplate = chestplate;
  }
  
  public void setLeggings(Item leggings) {
    this.leggings = leggings;
  }
  
  public void setBoots(Item boots) {
    this.boots = boots;
  }
  
  public Item getHelmet() {
    return this.helmet;
  }
  
  public Item getChestplate() {
    return this.chestplate;
  }
  
  public Item getLeggings() {
    return this.leggings;
  }
  
  public Item getBoots() {
    return this.boots;
  }
  
  public static ArmorSetEquipAchievementBuilder builder() {
    return new ArmorSetEquipAchievementBuilder();
  }
  
  public static class ArmorSetEquipAchievementBuilder {
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
  
  public ArmorSetEquipAchievement(String id, AchievementCategory category, String name, String description, int quantityRequired, String icon, Item helmet, Item chestplate, Item leggings, Item boots) {
    super(id, category, name, description, quantityRequired, icon);
    this.helmet = helmet;
    this.chestplate = chestplate;
    this.leggings = leggings;
    this.boots = boots;
  }
  
  private static boolean isItemCorrect(ItemStack playerArmorStack, Item compareArmor) {
    if (compareArmor == null)
      return true; 
    if (playerArmorStack == null)
      return false; 
    Item playerArmor = playerArmorStack.func_77973_b();
    return (playerArmor == compareArmor);
  }
  
  public static void performCheck(EntityPlayer player) {
    for (Achievement value : AchievementManager.getInstance().getAchievements(ArmorSetEquipAchievement.class)) {
      ArmorSetEquipAchievement achievement = (ArmorSetEquipAchievement)value;
      if (isItemCorrect(player.field_71071_by.func_70440_f(3), achievement.getHelmet()) && 
        isItemCorrect(player.field_71071_by.func_70440_f(2), achievement.getChestplate()) && 
        isItemCorrect(player.field_71071_by.func_70440_f(1), achievement.getLeggings()) && 
        isItemCorrect(player.field_71071_by.func_70440_f(0), achievement.getBoots()))
        incrementStats(achievement, player, 1); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ArmorSetEquipAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */