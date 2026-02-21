package fr.paladium.palapass.common.achievement;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import fr.paladium.palaoracle.core.pojo.PalaOracleEvent;
import net.minecraft.entity.player.EntityPlayer;

public class PalapassReachPointsAchievement extends Achievement {
  private int points;
  
  public void setPoints(int points) {
    this.points = points;
  }
  
  public int getPoints() {
    return this.points;
  }
  
  public static PalapassReachPointsAchievementBuilder builder() {
    return new PalapassReachPointsAchievementBuilder();
  }
  
  public static class PalapassReachPointsAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private int points;
    
    public PalapassReachPointsAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public PalapassReachPointsAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public PalapassReachPointsAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public PalapassReachPointsAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public PalapassReachPointsAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public PalapassReachPointsAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public PalapassReachPointsAchievementBuilder points(int points) {
      this.points = points;
      return this;
    }
    
    public PalapassReachPointsAchievement build() {
      return new PalapassReachPointsAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.points);
    }
    
    public String toString() {
      return "PalapassReachPointsAchievement.PalapassReachPointsAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", points=" + this.points + ")";
    }
  }
  
  public PalapassReachPointsAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, int points) {
    super(id, category, name, description, nbToValidate, icon);
    this.points = points;
  }
  
  public static void performCheck(EntityPlayer p, int currenPoints) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(PalapassReachPointsAchievement.class)) {
      if (achievement instanceof PalapassReachPointsAchievement) {
        PalapassReachPointsAchievement achvnt = (PalapassReachPointsAchievement)achievement;
        if (currenPoints >= achvnt.points) {
          PalaOracleEvent palaOracleEventInput = new PalaOracleEvent(p.func_110124_au().toString(), "Palapass Completion");
          palaOracleEventInput.addField("timestamp", Long.valueOf(System.currentTimeMillis()));
          palaOracleEventInput.addField("points", Integer.valueOf(currenPoints));
          palaOracleEventInput.capture();
          incrementStats(achvnt, p, 1);
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\achievement\PalapassReachPointsAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */