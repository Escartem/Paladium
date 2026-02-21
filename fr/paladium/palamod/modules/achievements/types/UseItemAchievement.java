package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class UseItemAchievement extends Achievement {
  private ItemStack itemStack;
  
  private String discriminator;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public void setDiscriminator(String discriminator) {
    this.discriminator = discriminator;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public String getDiscriminator() {
    return this.discriminator;
  }
  
  public static UseItemAchievementBuilder builder() {
    return new UseItemAchievementBuilder();
  }
  
  public static class UseItemAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private ItemStack itemStack;
    
    private String discriminator;
    
    public UseItemAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public UseItemAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public UseItemAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public UseItemAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public UseItemAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public UseItemAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public UseItemAchievementBuilder itemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
      return this;
    }
    
    public UseItemAchievementBuilder discriminator(String discriminator) {
      this.discriminator = discriminator;
      return this;
    }
    
    public UseItemAchievement build() {
      return new UseItemAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack, this.discriminator);
    }
    
    public String toString() {
      return "UseItemAchievement.UseItemAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ", discriminator=" + this.discriminator + ")";
    }
  }
  
  public UseItemAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, ItemStack itemStack, String discriminator) {
    super(id, category, name, description, nbToValidate, icon);
    this.itemStack = itemStack;
    this.discriminator = discriminator;
  }
  
  public static void performCheck(EntityPlayer p, ItemStack is, String discriminator) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(UseItemAchievement.class)) {
      if (achievement instanceof UseItemAchievement) {
        UseItemAchievement achvnt = (UseItemAchievement)achievement;
        if (achvnt.itemStack == null) {
          if (discriminator.contains(achvnt.discriminator))
            incrementStats(achvnt, p, 1); 
          continue;
        } 
        if (is != null && achvnt.itemStack.func_77969_a(is))
          incrementStats(achvnt, p, 1); 
      } 
    } 
  }
  
  public static void performCheck(EntityPlayer p, ItemStack is, String discriminator, int quantity) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(UseItemAchievement.class)) {
      if (achievement instanceof UseItemAchievement) {
        UseItemAchievement achvnt = (UseItemAchievement)achievement;
        if (achvnt.itemStack == null) {
          if (discriminator.contains(achvnt.discriminator))
            incrementStats(achvnt, p, quantity); 
          continue;
        } 
        if (achvnt.itemStack.func_77969_a(is))
          incrementStats(achvnt, p, quantity); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\UseItemAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */