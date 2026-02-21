package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CraftCauldronAchievement extends Achievement {
  private ItemStack itemStack;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public static CraftCauldronAchievementBuilder builder() {
    return new CraftCauldronAchievementBuilder();
  }
  
  public static class CraftCauldronAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private ItemStack itemStack;
    
    public CraftCauldronAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public CraftCauldronAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public CraftCauldronAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public CraftCauldronAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public CraftCauldronAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public CraftCauldronAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public CraftCauldronAchievementBuilder itemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
      return this;
    }
    
    public CraftCauldronAchievement build() {
      return new CraftCauldronAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack);
    }
    
    public String toString() {
      return "CraftCauldronAchievement.CraftCauldronAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ")";
    }
  }
  
  public CraftCauldronAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, ItemStack itemStack) {
    super(id, category, name, description, nbToValidate, icon);
    this.itemStack = itemStack;
  }
  
  public static void performCheck(EntityPlayer p, ItemStack itemStack) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(CraftCauldronAchievement.class)) {
      if (achievement instanceof CraftCauldronAchievement) {
        CraftCauldronAchievement achvnt = (CraftCauldronAchievement)achievement;
        if (achvnt.itemStack.func_77969_a(itemStack))
          incrementStats(achvnt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\CraftCauldronAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */