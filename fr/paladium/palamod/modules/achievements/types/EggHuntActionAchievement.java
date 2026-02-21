package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class EggHuntActionAchievement extends Achievement {
  private int action;
  
  public void setAction(int action) {
    this.action = action;
  }
  
  public int getAction() {
    return this.action;
  }
  
  public static EggHuntActionAchievementBuilder builder() {
    return new EggHuntActionAchievementBuilder();
  }
  
  public static class EggHuntActionAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private int action;
    
    public EggHuntActionAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public EggHuntActionAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public EggHuntActionAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public EggHuntActionAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public EggHuntActionAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public EggHuntActionAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public EggHuntActionAchievementBuilder action(int action) {
      this.action = action;
      return this;
    }
    
    public EggHuntActionAchievement build() {
      return new EggHuntActionAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.action);
    }
    
    public String toString() {
      return "EggHuntActionAchievement.EggHuntActionAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", action=" + this.action + ")";
    }
  }
  
  public EggHuntActionAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, int action) {
    super(id, category, name, description, nbToValidate, icon);
    this.action = action;
  }
  
  public static void performCheck(EntityPlayer p, int action) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(EggHuntActionAchievement.class)) {
      if (achievement instanceof EggHuntActionAchievement) {
        EggHuntActionAchievement achvnt = (EggHuntActionAchievement)achievement;
        if (action == achvnt.action)
          incrementStats(achvnt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\EggHuntActionAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */