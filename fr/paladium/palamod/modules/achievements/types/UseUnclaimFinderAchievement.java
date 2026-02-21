package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class UseUnclaimFinderAchievement extends Achievement {
  public static UseUnclaimFinderAchievementBuilder builder() {
    return new UseUnclaimFinderAchievementBuilder();
  }
  
  public static class UseUnclaimFinderAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public UseUnclaimFinderAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public UseUnclaimFinderAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public UseUnclaimFinderAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public UseUnclaimFinderAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public UseUnclaimFinderAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public UseUnclaimFinderAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public UseUnclaimFinderAchievement build() {
      return new UseUnclaimFinderAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "UseUnclaimFinderAchievement.UseUnclaimFinderAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public UseUnclaimFinderAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance()
      .getAchievements(UseUnclaimFinderAchievement.class)) {
      if (achievement instanceof UseUnclaimFinderAchievement) {
        UseUnclaimFinderAchievement achvnt = (UseUnclaimFinderAchievement)achievement;
        incrementStats(achvnt, p, 1);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\UseUnclaimFinderAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */