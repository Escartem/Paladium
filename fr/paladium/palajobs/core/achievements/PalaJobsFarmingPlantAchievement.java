package fr.paladium.palajobs.core.achievements;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class PalaJobsFarmingPlantAchievement extends Achievement {
  public static PalaJobsFarmingPlantAchievementBuilder builder() {
    return new PalaJobsFarmingPlantAchievementBuilder();
  }
  
  public static class PalaJobsFarmingPlantAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public PalaJobsFarmingPlantAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public PalaJobsFarmingPlantAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public PalaJobsFarmingPlantAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public PalaJobsFarmingPlantAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public PalaJobsFarmingPlantAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public PalaJobsFarmingPlantAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public PalaJobsFarmingPlantAchievement build() {
      return new PalaJobsFarmingPlantAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "PalaJobsFarmingPlantAchievement.PalaJobsFarmingPlantAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public PalaJobsFarmingPlantAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p, int quantity) {
    AchievementManager.getInstance().getAchievements(PalaJobsFarmingPlantAchievement.class).stream().filter(PalaJobsFarmingPlantAchievement.class::isInstance).forEach(achievement -> incrementStats(achievement, p, quantity));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\achievements\PalaJobsFarmingPlantAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */