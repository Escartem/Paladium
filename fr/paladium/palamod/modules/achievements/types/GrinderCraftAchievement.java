package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GrinderCraftAchievement extends Achievement {
  private ItemStack itemStack;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public static GrinderCraftAchievementBuilder builder() {
    return new GrinderCraftAchievementBuilder();
  }
  
  public static class GrinderCraftAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private ItemStack itemStack;
    
    public GrinderCraftAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public GrinderCraftAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public GrinderCraftAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public GrinderCraftAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public GrinderCraftAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public GrinderCraftAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public GrinderCraftAchievementBuilder itemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
      return this;
    }
    
    public GrinderCraftAchievement build() {
      return new GrinderCraftAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack);
    }
    
    public String toString() {
      return "GrinderCraftAchievement.GrinderCraftAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ")";
    }
  }
  
  public GrinderCraftAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, ItemStack itemStack) {
    super(id, category, name, description, nbToValidate, icon);
    this.itemStack = itemStack;
  }
  
  public static void performCheck(EntityPlayer p, ItemStack is) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(GrinderCraftAchievement.class)) {
      if (achievement instanceof GrinderCraftAchievement) {
        GrinderCraftAchievement achvnt = (GrinderCraftAchievement)achievement;
        if (achvnt.itemStack.func_77969_a(is))
          incrementStats(achvnt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\GrinderCraftAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */