package fr.paladium.palajobs.core.achievements;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class PalaJobsHunterKillAchievement extends Achievement {
  public static PalaJobsHunterKillAchievementBuilder builder() {
    return new PalaJobsHunterKillAchievementBuilder();
  }
  
  public static class PalaJobsHunterKillAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public PalaJobsHunterKillAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public PalaJobsHunterKillAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public PalaJobsHunterKillAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public PalaJobsHunterKillAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public PalaJobsHunterKillAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public PalaJobsHunterKillAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public PalaJobsHunterKillAchievement build() {
      return new PalaJobsHunterKillAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "PalaJobsHunterKillAchievement.PalaJobsHunterKillAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public PalaJobsHunterKillAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p, int quantity) {
    AchievementManager.getInstance().getAchievements(PalaJobsHunterKillAchievement.class).stream().filter(PalaJobsHunterKillAchievement.class::isInstance).forEach(achievement -> incrementStats(achievement, p, quantity));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\achievements\PalaJobsHunterKillAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */