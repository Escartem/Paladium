package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class DimensionMinerActionAchievement extends Achievement {
  private int action;
  
  public void setAction(int action) {
    this.action = action;
  }
  
  public int getAction() {
    return this.action;
  }
  
  public static DimensionMinerActionAchievementBuilder builder() {
    return new DimensionMinerActionAchievementBuilder();
  }
  
  public static class DimensionMinerActionAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private int action;
    
    public DimensionMinerActionAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public DimensionMinerActionAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public DimensionMinerActionAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public DimensionMinerActionAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public DimensionMinerActionAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public DimensionMinerActionAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public DimensionMinerActionAchievementBuilder action(int action) {
      this.action = action;
      return this;
    }
    
    public DimensionMinerActionAchievement build() {
      return new DimensionMinerActionAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.action);
    }
    
    public String toString() {
      return "DimensionMinerActionAchievement.DimensionMinerActionAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", action=" + this.action + ")";
    }
  }
  
  public DimensionMinerActionAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, int action) {
    super(id, category, name, description, nbToValidate, icon);
    this.action = action;
  }
  
  public static void performCheck(EntityPlayer p, int action) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(DimensionMinerActionAchievement.class)) {
      if (achievement instanceof DimensionMinerActionAchievement) {
        DimensionMinerActionAchievement achvnt = (DimensionMinerActionAchievement)achievement;
        if (action == achvnt.action)
          incrementStats(achvnt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\DimensionMinerActionAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */