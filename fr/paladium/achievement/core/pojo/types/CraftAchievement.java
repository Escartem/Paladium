package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CraftAchievement extends Achievement {
  private ItemStack itemStack;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public static CraftAchievementBuilder builder() {
    return new CraftAchievementBuilder();
  }
  
  public static class CraftAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private ItemStack itemStack;
    
    private String icon;
    
    public CraftAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public CraftAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public CraftAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public CraftAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public CraftAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public CraftAchievementBuilder itemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
      return this;
    }
    
    public CraftAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public CraftAchievement build() {
      return new CraftAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.itemStack, this.icon);
    }
    
    public String toString() {
      return "CraftAchievement.CraftAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", itemStack=" + this.itemStack + ", icon=" + this.icon + ")";
    }
  }
  
  public CraftAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, ItemStack itemStack, String icon) {
    super(id, category, name, description, nbToValidate, icon);
    this.itemStack = itemStack;
  }
  
  public static void performCheck(ItemStack itemStack, int quantity, EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(CraftAchievement.class)) {
      if (achievement instanceof CraftAchievement) {
        CraftAchievement craftAchvmt = (CraftAchievement)achievement;
        if (craftAchvmt.getItemStack().func_77969_a(itemStack))
          incrementStats(craftAchvmt, p, quantity); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\CraftAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */