package fr.paladium.palajobs.core.achievements;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class PalaJobsFertilizedDirtHoeAchievement extends Achievement {
  public static PalaJobsFertilizedDirtHoeAchievementBuilder builder() {
    return new PalaJobsFertilizedDirtHoeAchievementBuilder();
  }
  
  public static class PalaJobsFertilizedDirtHoeAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public PalaJobsFertilizedDirtHoeAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public PalaJobsFertilizedDirtHoeAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public PalaJobsFertilizedDirtHoeAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public PalaJobsFertilizedDirtHoeAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public PalaJobsFertilizedDirtHoeAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public PalaJobsFertilizedDirtHoeAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public PalaJobsFertilizedDirtHoeAchievement build() {
      return new PalaJobsFertilizedDirtHoeAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "PalaJobsFertilizedDirtHoeAchievement.PalaJobsFertilizedDirtHoeAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public PalaJobsFertilizedDirtHoeAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p, int quantity) {
    AchievementManager.getInstance().getAchievements(PalaJobsFertilizedDirtHoeAchievement.class).stream().filter(PalaJobsFertilizedDirtHoeAchievement.class::isInstance).forEach(achievement -> incrementStats(achievement, p, quantity));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\achievements\PalaJobsFertilizedDirtHoeAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */