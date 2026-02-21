package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class ShopSellAchievement extends Achievement {
  public static ShopSellAchievementBuilder builder() {
    return new ShopSellAchievementBuilder();
  }
  
  public static class ShopSellAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public ShopSellAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public ShopSellAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public ShopSellAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public ShopSellAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public ShopSellAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public ShopSellAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public ShopSellAchievement build() {
      return new ShopSellAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "ShopSellAchievement.ShopSellAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public ShopSellAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(ShopSellAchievement.class)) {
      if (achievement instanceof ShopSellAchievement) {
        ShopSellAchievement achvnt = (ShopSellAchievement)achievement;
        incrementStats(achvnt, p, 1);
        return;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ShopSellAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */