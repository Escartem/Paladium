package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class ExtractSeveAchievement extends Achievement {
  public static ExtractSeveAchievementBuilder builder() {
    return new ExtractSeveAchievementBuilder();
  }
  
  public static class ExtractSeveAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public ExtractSeveAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public ExtractSeveAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public ExtractSeveAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public ExtractSeveAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public ExtractSeveAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public ExtractSeveAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public ExtractSeveAchievement build() {
      return new ExtractSeveAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "ExtractSeveAchievement.ExtractSeveAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public ExtractSeveAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(ExtractSeveAchievement.class)) {
      if (achievement instanceof ExtractSeveAchievement) {
        ExtractSeveAchievement achvnt = (ExtractSeveAchievement)achievement;
        incrementStats(achvnt, p, 1000);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ExtractSeveAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */