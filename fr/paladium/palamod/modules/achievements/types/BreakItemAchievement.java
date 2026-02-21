package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class BreakItemAchievement extends Achievement {
  private Item item;
  
  public void setItem(Item item) {
    this.item = item;
  }
  
  public Item getItem() {
    return this.item;
  }
  
  public static BreakItemAchievementBuilder builder() {
    return new BreakItemAchievementBuilder();
  }
  
  public static class BreakItemAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private Item item;
    
    public BreakItemAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public BreakItemAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public BreakItemAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public BreakItemAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public BreakItemAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public BreakItemAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public BreakItemAchievementBuilder item(Item item) {
      this.item = item;
      return this;
    }
    
    public BreakItemAchievement build() {
      return new BreakItemAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.item);
    }
    
    public String toString() {
      return "BreakItemAchievement.BreakItemAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", item=" + this.item + ")";
    }
  }
  
  public BreakItemAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, Item item) {
    super(id, category, name, description, nbToValidate, icon);
    this.item = item;
  }
  
  public static void performCheck(EntityPlayer player, Item item) {
    for (Achievement value : AchievementManager.getInstance().getAchievements(BreakItemAchievement.class)) {
      BreakItemAchievement achievement = (BreakItemAchievement)value;
      if (achievement.item == item)
        incrementStats(achievement, player, 1); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\BreakItemAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */