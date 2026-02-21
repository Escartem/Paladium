package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class CresusReachBalanceAchievement extends Achievement {
  private int money;
  
  public void setMoney(int money) {
    this.money = money;
  }
  
  public int getMoney() {
    return this.money;
  }
  
  public static CresusReachBalanceAchievementBuilder builder() {
    return new CresusReachBalanceAchievementBuilder();
  }
  
  public static class CresusReachBalanceAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private int points;
    
    public CresusReachBalanceAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public CresusReachBalanceAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public CresusReachBalanceAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public CresusReachBalanceAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public CresusReachBalanceAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public CresusReachBalanceAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public CresusReachBalanceAchievementBuilder points(int points) {
      this.points = points;
      return this;
    }
    
    public CresusReachBalanceAchievement build() {
      return new CresusReachBalanceAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.points);
    }
    
    public String toString() {
      return "CresusReachBalanceAchievement.CresusReachBalanceAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", points=" + this.points + ")";
    }
  }
  
  public CresusReachBalanceAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, int points) {
    super(id, category, name, description, nbToValidate, icon);
    this.money = points;
  }
  
  public static void performCheck(EntityPlayer p, int currentMoney) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(CresusReachBalanceAchievement.class)) {
      if (achievement instanceof CresusReachBalanceAchievement) {
        CresusReachBalanceAchievement achvnt = (CresusReachBalanceAchievement)achievement;
        if (currentMoney >= achvnt.money)
          incrementStats(achvnt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\CresusReachBalanceAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */