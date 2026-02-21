package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class ShopSellCountAchievement extends Achievement {
  public static ShopSellCountAchievementBuilder builder() {
    return new ShopSellCountAchievementBuilder();
  }
  
  public static class ShopSellCountAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public ShopSellCountAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public ShopSellCountAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public ShopSellCountAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public ShopSellCountAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public ShopSellCountAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public ShopSellCountAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public ShopSellCountAchievement build() {
      return new ShopSellCountAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "ShopSellCountAchievement.ShopSellCountAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public ShopSellCountAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p, double amount) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(ShopSellCountAchievement.class)) {
      if (achievement instanceof ShopSellCountAchievement) {
        ShopSellCountAchievement achvnt = (ShopSellCountAchievement)achievement;
        incrementStats(achvnt, p, (int)amount);
        return;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ShopSellCountAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */