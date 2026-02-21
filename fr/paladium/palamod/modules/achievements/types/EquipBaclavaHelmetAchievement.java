package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;

public class EquipBaclavaHelmetAchievement extends Achievement {
  public static final int QUANTITY = 1;
  
  public static EquipBaclavaHelmetAchievementBuilder builder() {
    return new EquipBaclavaHelmetAchievementBuilder();
  }
  
  public static class EquipBaclavaHelmetAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int quantityRequired;
    
    private String icon;
    
    public EquipBaclavaHelmetAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public EquipBaclavaHelmetAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public EquipBaclavaHelmetAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public EquipBaclavaHelmetAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public EquipBaclavaHelmetAchievementBuilder quantityRequired(int quantityRequired) {
      this.quantityRequired = quantityRequired;
      return this;
    }
    
    public EquipBaclavaHelmetAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public EquipBaclavaHelmetAchievement build() {
      return new EquipBaclavaHelmetAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon);
    }
    
    public String toString() {
      return "EquipBaclavaHelmetAchievement.EquipBaclavaHelmetAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ")";
    }
  }
  
  public EquipBaclavaHelmetAchievement(String id, AchievementCategory category, String name, String description, int quantityRequired, String icon) {
    super(id, category, name, description, quantityRequired, icon);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\EquipBaclavaHelmetAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */