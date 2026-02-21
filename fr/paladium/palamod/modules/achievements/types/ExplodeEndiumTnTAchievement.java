package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;

public class ExplodeEndiumTnTAchievement extends Achievement {
  public static final int QUANTITY = 1;
  
  public static ExplodeEndiumTnTAchievementBuilder builder() {
    return new ExplodeEndiumTnTAchievementBuilder();
  }
  
  public static class ExplodeEndiumTnTAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int quantityRequired;
    
    private String icon;
    
    public ExplodeEndiumTnTAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public ExplodeEndiumTnTAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public ExplodeEndiumTnTAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public ExplodeEndiumTnTAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public ExplodeEndiumTnTAchievementBuilder quantityRequired(int quantityRequired) {
      this.quantityRequired = quantityRequired;
      return this;
    }
    
    public ExplodeEndiumTnTAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public ExplodeEndiumTnTAchievement build() {
      return new ExplodeEndiumTnTAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon);
    }
    
    public String toString() {
      return "ExplodeEndiumTnTAchievement.ExplodeEndiumTnTAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ")";
    }
  }
  
  public ExplodeEndiumTnTAchievement(String id, AchievementCategory category, String name, String description, int quantityRequired, String icon) {
    super(id, category, name, description, quantityRequired, icon);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ExplodeEndiumTnTAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */