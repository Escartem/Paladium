package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class HurtBossAchievement extends Achievement {
  public static HurtBossAchievementBuilder builder() {
    return new HurtBossAchievementBuilder();
  }
  
  public static class HurtBossAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public HurtBossAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public HurtBossAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public HurtBossAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public HurtBossAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public HurtBossAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public HurtBossAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public HurtBossAchievement build() {
      return new HurtBossAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "HurtBossAchievement.HurtBossAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public HurtBossAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(HurtBossAchievement.class)) {
      if (achievement instanceof HurtBossAchievement) {
        HurtBossAchievement achvnt = (HurtBossAchievement)achievement;
        incrementStats(achvnt, p, 1);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\HurtBossAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */