package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class ShopBuyAchievement extends Achievement {
  public static ShopBuyAchievementBuilder builder() {
    return new ShopBuyAchievementBuilder();
  }
  
  public static class ShopBuyAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    public ShopBuyAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public ShopBuyAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public ShopBuyAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public ShopBuyAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public ShopBuyAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public ShopBuyAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public ShopBuyAchievement build() {
      return new ShopBuyAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon);
    }
    
    public String toString() {
      return "ShopBuyAchievement.ShopBuyAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ")";
    }
  }
  
  public ShopBuyAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(ShopBuyAchievement.class)) {
      if (achievement instanceof ShopBuyAchievement) {
        ShopBuyAchievement achvnt = (ShopBuyAchievement)achievement;
        incrementStats(achvnt, p, 1);
        return;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ShopBuyAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */