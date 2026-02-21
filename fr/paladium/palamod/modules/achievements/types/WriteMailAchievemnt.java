package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class WriteMailAchievemnt extends Achievement {
  public static WriteMailAchievemntBuilder builder() {
    return new WriteMailAchievemntBuilder();
  }
  
  public static class WriteMailAchievemntBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private ItemStack itemStack;
    
    public WriteMailAchievemntBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public WriteMailAchievemntBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public WriteMailAchievemntBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public WriteMailAchievemntBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public WriteMailAchievemntBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public WriteMailAchievemntBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public WriteMailAchievemntBuilder itemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
      return this;
    }
    
    public WriteMailAchievemnt build() {
      return new WriteMailAchievemnt(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.itemStack);
    }
    
    public String toString() {
      return "WriteMailAchievemnt.WriteMailAchievemntBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", itemStack=" + this.itemStack + ")";
    }
  }
  
  public WriteMailAchievemnt(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, ItemStack itemStack) {
    super(id, category, name, description, nbToValidate, icon);
  }
  
  public static void performCheck(EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(WriteMailAchievemnt.class)) {
      if (achievement instanceof WriteMailAchievemnt) {
        WriteMailAchievemnt achvnt = (WriteMailAchievemnt)achievement;
        incrementStats(achvnt, p, 1);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\WriteMailAchievemnt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */