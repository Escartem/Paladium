package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class WalkDistanceAchievement extends Achievement {
  public static WalkDistanceAchievementBuilder builder() {
    return new WalkDistanceAchievementBuilder();
  }
  
  public static class WalkDistanceAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public WalkDistanceAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public WalkDistanceAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public WalkDistanceAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public WalkDistanceAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public WalkDistanceAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public WalkDistanceAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public WalkDistanceAchievement build() {
      return new WalkDistanceAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "WalkDistanceAchievement.WalkDistanceAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public WalkDistanceAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p, int distance) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(WalkDistanceAchievement.class)) {
      if (achievement instanceof WalkDistanceAchievement) {
        WalkDistanceAchievement achvmt = (WalkDistanceAchievement)achievement;
        Achievement.incrementStats(achvmt, p, distance);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\WalkDistanceAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */