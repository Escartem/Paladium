package fr.paladium.palamod.modules.achievements.types.faction;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class FactionReachEloAchievement extends Achievement {
  public static final int QUANTITY = 1;
  
  private final int requiredElo;
  
  public int getRequiredElo() {
    return this.requiredElo;
  }
  
  public static FactionReachEloAchievementBuilder builder() {
    return new FactionReachEloAchievementBuilder();
  }
  
  public static class FactionReachEloAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int quantityRequired;
    
    private String icon;
    
    private int requiredElo;
    
    public FactionReachEloAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public FactionReachEloAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public FactionReachEloAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public FactionReachEloAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public FactionReachEloAchievementBuilder quantityRequired(int quantityRequired) {
      this.quantityRequired = quantityRequired;
      return this;
    }
    
    public FactionReachEloAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public FactionReachEloAchievementBuilder requiredElo(int requiredElo) {
      this.requiredElo = requiredElo;
      return this;
    }
    
    public FactionReachEloAchievement build() {
      return new FactionReachEloAchievement(this.id, this.category, this.name, this.description, this.quantityRequired, this.icon, this.requiredElo);
    }
    
    public String toString() {
      return "FactionReachEloAchievement.FactionReachEloAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", quantityRequired=" + this.quantityRequired + ", icon=" + this.icon + ", requiredElo=" + this.requiredElo + ")";
    }
  }
  
  public FactionReachEloAchievement(String id, AchievementCategory category, String name, String description, int quantityRequired, String icon, int requiredElo) {
    super(id, category, name, description, quantityRequired, icon);
    this.requiredElo = requiredElo;
  }
  
  public static void performCheck(EntityPlayer player, int elo) {
    for (Achievement value : AchievementManager.getInstance().getAchievements(FactionReachEloAchievement.class)) {
      FactionReachEloAchievement achievement = (FactionReachEloAchievement)value;
      if (elo >= achievement.getRequiredElo())
        incrementStats(achievement, player, 1); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\faction\FactionReachEloAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */