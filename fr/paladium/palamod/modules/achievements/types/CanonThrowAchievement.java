package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;

public class CanonThrowAchievement extends Achievement {
  public static final int QUANTITY = 1;
  
  public static CanonThrowAchievementBuilder builder() {
    return new CanonThrowAchievementBuilder();
  }
  
  public static class CanonThrowAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int quantityRequired;
    
    private String icon;
    
    public CanonThrowAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public CanonThrowAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public CanonThrowAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public CanonThrowAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public CanonThrowAchievementBuilder quantityRequired(int quantityRequired) {
      this.quantityRequired = quantityRequired;
      return this;
    }
    
    public CanonThrowAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public CanonThrowAchievement build() {
      return new CanonThrowAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon);
    }
    
    public String toString() {
      return "CanonThrowAchievement.CanonThrowAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ")";
    }
  }
  
  public CanonThrowAchievement(String id, AchievementCategory category, String name, String description, int quantityRequired, String icon) {
    super(id, category, name, description, quantityRequired, icon);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\CanonThrowAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */