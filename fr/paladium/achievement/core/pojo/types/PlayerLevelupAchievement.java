package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerLevelupAchievement extends Achievement {
  private int level;
  
  public void setLevel(int level) {
    this.level = level;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public static PlayerLevelupAchievementBuilder builder() {
    return new PlayerLevelupAchievementBuilder();
  }
  
  public static class PlayerLevelupAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private int level;
    
    public PlayerLevelupAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public PlayerLevelupAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public PlayerLevelupAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public PlayerLevelupAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public PlayerLevelupAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public PlayerLevelupAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public PlayerLevelupAchievementBuilder level(int level) {
      this.level = level;
      return this;
    }
    
    public PlayerLevelupAchievement build() {
      return new PlayerLevelupAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.level);
    }
    
    public String toString() {
      return "PlayerLevelupAchievement.PlayerLevelupAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", level=" + this.level + ")";
    }
  }
  
  public PlayerLevelupAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, int level) {
    super(id, category, name, description, nbToValidate, icon);
    this.level = level;
  }
  
  public static void performCheck(int lvl, EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(PlayerLevelupAchievement.class)) {
      if (achievement instanceof PlayerLevelupAchievement) {
        PlayerLevelupAchievement achvmt = (PlayerLevelupAchievement)achievement;
        if (lvl >= achvmt.level)
          Achievement.incrementStats(achvmt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\PlayerLevelupAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */