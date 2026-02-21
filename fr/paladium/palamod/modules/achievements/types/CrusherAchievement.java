package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CrusherAchievement extends Achievement {
  private ItemStack itemStack;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public static CrusherAchievementBuilder builder() {
    return new CrusherAchievementBuilder();
  }
  
  public static class CrusherAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private ItemStack itemStack;
    
    public CrusherAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public CrusherAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public CrusherAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public CrusherAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public CrusherAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public CrusherAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public CrusherAchievementBuilder itemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
      return this;
    }
    
    public CrusherAchievement build() {
      return new CrusherAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack);
    }
    
    public String toString() {
      return "CrusherAchievement.CrusherAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ")";
    }
  }
  
  public CrusherAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, ItemStack itemStack) {
    super(id, category, name, description, nbToValidate, icon);
    this.itemStack = itemStack;
  }
  
  public static void performCheck(EntityPlayer p, ItemStack itemStack) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(CrusherAchievement.class)) {
      if (achievement instanceof CrusherAchievement) {
        CrusherAchievement achvnt = (CrusherAchievement)achievement;
        if (achvnt.itemStack.func_77969_a(itemStack))
          incrementStats(achvnt, p, itemStack.field_77994_a); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\CrusherAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */