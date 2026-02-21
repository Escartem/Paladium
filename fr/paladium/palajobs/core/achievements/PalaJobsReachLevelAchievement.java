package fr.paladium.palajobs.core.achievements;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import fr.paladium.palajobs.api.type.JobType;
import net.minecraft.entity.player.EntityPlayer;

public class PalaJobsReachLevelAchievement extends Achievement {
  private int level;
  
  private JobType jobType;
  
  public void setLevel(int level) {
    this.level = level;
  }
  
  public void setJobType(JobType jobType) {
    this.jobType = jobType;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public JobType getJobType() {
    return this.jobType;
  }
  
  public static PalaJobsReachLevelAchievementBuilder builder() {
    return new PalaJobsReachLevelAchievementBuilder();
  }
  
  public static class PalaJobsReachLevelAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private int level;
    
    private JobType jobType;
    
    public PalaJobsReachLevelAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public PalaJobsReachLevelAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public PalaJobsReachLevelAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public PalaJobsReachLevelAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public PalaJobsReachLevelAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public PalaJobsReachLevelAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public PalaJobsReachLevelAchievementBuilder level(int level) {
      this.level = level;
      return this;
    }
    
    public PalaJobsReachLevelAchievementBuilder jobType(JobType jobType) {
      this.jobType = jobType;
      return this;
    }
    
    public PalaJobsReachLevelAchievement build() {
      return new PalaJobsReachLevelAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.level, this.jobType);
    }
    
    public String toString() {
      return "PalaJobsReachLevelAchievement.PalaJobsReachLevelAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", level=" + this.level + ", jobType=" + this.jobType + ")";
    }
  }
  
  public PalaJobsReachLevelAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, int level, JobType jobType) {
    super(id, category, name, description, nbToValidate, icon);
    this.level = level;
    this.jobType = jobType;
  }
  
  public static void performCheck(EntityPlayer p, JobType lvledJob, int reachedLvl) {
    AchievementManager.getInstance().getAchievements(PalaJobsReachLevelAchievement.class).stream().filter(PalaJobsReachLevelAchievement.class::isInstance).forEach(achievement -> {
          PalaJobsReachLevelAchievement achvnt = (PalaJobsReachLevelAchievement)achievement;
          if (achvnt.jobType.equals(lvledJob) && reachedLvl >= achvnt.level)
            incrementStats(achievement, p, 1); 
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\achievements\PalaJobsReachLevelAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */