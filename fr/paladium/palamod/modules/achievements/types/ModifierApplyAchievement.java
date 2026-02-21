package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModifierApplyAchievement extends Achievement {
  private ItemStack itemStack;
  
  private ItemStack modifier;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public void setModifier(ItemStack modifier) {
    this.modifier = modifier;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public ItemStack getModifier() {
    return this.modifier;
  }
  
  public static ModifierApplyAchievementBuilder builder() {
    return new ModifierApplyAchievementBuilder();
  }
  
  public static class ModifierApplyAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private ItemStack itemStack;
    
    private ItemStack modifier;
    
    public ModifierApplyAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public ModifierApplyAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public ModifierApplyAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public ModifierApplyAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public ModifierApplyAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public ModifierApplyAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public ModifierApplyAchievementBuilder itemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
      return this;
    }
    
    public ModifierApplyAchievementBuilder modifier(ItemStack modifier) {
      this.modifier = modifier;
      return this;
    }
    
    public ModifierApplyAchievement build() {
      return new ModifierApplyAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack, this.modifier);
    }
    
    public String toString() {
      return "ModifierApplyAchievement.ModifierApplyAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ", modifier=" + this.modifier + ")";
    }
  }
  
  public ModifierApplyAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, ItemStack itemStack, ItemStack modifier) {
    super(id, category, name, description, nbToValidate, icon);
    this.itemStack = itemStack;
    this.modifier = modifier;
  }
  
  public static void performCheck(EntityPlayer p, ItemStack is, ItemStack modifier) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(ModifierApplyAchievement.class)) {
      if (achievement instanceof ModifierApplyAchievement) {
        ModifierApplyAchievement achvnt = (ModifierApplyAchievement)achievement;
        if (achvnt.itemStack.func_77969_a(is) && achvnt.modifier.func_77969_a(modifier))
          incrementStats(achvnt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\ModifierApplyAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */