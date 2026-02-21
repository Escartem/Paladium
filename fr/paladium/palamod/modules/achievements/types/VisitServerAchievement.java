package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import fr.paladium.common.CommonModule;
import net.minecraft.entity.player.EntityPlayer;

public class VisitServerAchievement extends Achievement {
  private String serverName;
  
  public void setServerName(String serverName) {
    this.serverName = serverName;
  }
  
  public String getServerName() {
    return this.serverName;
  }
  
  public static VisitServerAchievementBuilder builder() {
    return new VisitServerAchievementBuilder();
  }
  
  public static class VisitServerAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private String serverName;
    
    public VisitServerAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public VisitServerAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public VisitServerAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public VisitServerAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public VisitServerAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public VisitServerAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public VisitServerAchievementBuilder serverName(String serverName) {
      this.serverName = serverName;
      return this;
    }
    
    public VisitServerAchievement build() {
      return new VisitServerAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.serverName);
    }
    
    public String toString() {
      return "VisitServerAchievement.VisitServerAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", serverName=" + this.serverName + ")";
    }
  }
  
  public VisitServerAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, String serverName) {
    super(id, category, name, description, nbToValidate, icon);
    this.serverName = serverName;
  }
  
  public static void performCheck(EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(VisitServerAchievement.class)) {
      if (achievement instanceof VisitServerAchievement) {
        VisitServerAchievement achvnt = (VisitServerAchievement)achievement;
        if (CommonModule.getInstance().getConfig().getServerName().toUpperCase().contains(achvnt.serverName.toUpperCase()))
          incrementStats(achvnt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\VisitServerAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */