package fr.paladium.palajobs.core.achievements;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class PalaJobsMinerOreAchievement extends Achievement {
  public static PalaJobsMinerOreAchievementBuilder builder() {
    return new PalaJobsMinerOreAchievementBuilder();
  }
  
  public static class PalaJobsMinerOreAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public PalaJobsMinerOreAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public PalaJobsMinerOreAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public PalaJobsMinerOreAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public PalaJobsMinerOreAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public PalaJobsMinerOreAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public PalaJobsMinerOreAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public PalaJobsMinerOreAchievement build() {
      return new PalaJobsMinerOreAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "PalaJobsMinerOreAchievement.PalaJobsMinerOreAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public PalaJobsMinerOreAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p, int currentMoney) {
    AchievementManager.getInstance().getAchievements(PalaJobsMinerOreAchievement.class).stream().filter(PalaJobsMinerOreAchievement.class::isInstance).forEach(achievement -> incrementStats(achievement, p, 1));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\achievements\PalaJobsMinerOreAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */