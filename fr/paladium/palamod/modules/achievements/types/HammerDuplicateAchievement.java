package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

public class HammerDuplicateAchievement extends Achievement {
  private Block block;
  
  public void setBlock(Block block) {
    this.block = block;
  }
  
  public Block getBlock() {
    return this.block;
  }
  
  public static HammerDuplicateAchievementBuilder builder() {
    return new HammerDuplicateAchievementBuilder();
  }
  
  public static class HammerDuplicateAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private Block block;
    
    public HammerDuplicateAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public HammerDuplicateAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public HammerDuplicateAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public HammerDuplicateAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public HammerDuplicateAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public HammerDuplicateAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public HammerDuplicateAchievementBuilder block(Block block) {
      this.block = block;
      return this;
    }
    
    public HammerDuplicateAchievement build() {
      return new HammerDuplicateAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.block);
    }
    
    public String toString() {
      return "HammerDuplicateAchievement.HammerDuplicateAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", block=" + this.block + ")";
    }
  }
  
  public HammerDuplicateAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, Block block) {
    super(id, category, name, description, nbToValidate, icon);
    this.block = block;
  }
  
  public static void performCheck(EntityPlayer p, Block b) {
    for (Achievement achievement : AchievementManager.getInstance()
      .getAchievements(HammerDuplicateAchievement.class)) {
      if (achievement instanceof HammerDuplicateAchievement) {
        HammerDuplicateAchievement achvnt = (HammerDuplicateAchievement)achievement;
        if (achvnt.block == b || achvnt.block == null)
          incrementStats(achvnt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\HammerDuplicateAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */