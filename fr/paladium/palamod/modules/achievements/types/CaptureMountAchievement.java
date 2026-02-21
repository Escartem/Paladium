package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;

public class CaptureMountAchievement extends Achievement {
  public static final int QUANTITY = 1;
  
  public static CaptureMountAchievementBuilder builder() {
    return new CaptureMountAchievementBuilder();
  }
  
  public static class CaptureMountAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int quantityRequired;
    
    private String icon;
    
    public CaptureMountAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public CaptureMountAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public CaptureMountAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public CaptureMountAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public CaptureMountAchievementBuilder quantityRequired(int quantityRequired) {
      this.quantityRequired = quantityRequired;
      return this;
    }
    
    public CaptureMountAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public CaptureMountAchievement build() {
      return new CaptureMountAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon);
    }
    
    public String toString() {
      return "CaptureMountAchievement.CaptureMountAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ")";
    }
  }
  
  public CaptureMountAchievement(String id, AchievementCategory category, String name, String description, int quantityRequired, String icon) {
    super(id, category, name, description, quantityRequired, icon);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\CaptureMountAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */