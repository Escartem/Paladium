package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

public class BreakBlockAchievement extends Achievement {
  private Block block;
  
  public void setBlock(Block block) {
    this.block = block;
  }
  
  public Block getBlock() {
    return this.block;
  }
  
  public static BreakBlockAchievementBuilder builder() {
    return new BreakBlockAchievementBuilder();
  }
  
  public static class BreakBlockAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private Block block;
    
    public BreakBlockAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public BreakBlockAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public BreakBlockAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public BreakBlockAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public BreakBlockAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public BreakBlockAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public BreakBlockAchievementBuilder block(Block block) {
      this.block = block;
      return this;
    }
    
    public BreakBlockAchievement build() {
      return new BreakBlockAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.block);
    }
    
    public String toString() {
      return "BreakBlockAchievement.BreakBlockAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", block=" + this.block + ")";
    }
  }
  
  public BreakBlockAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, Block block) {
    super(id, category, name, description, nbToValidate, icon);
    this.block = block;
  }
  
  public static void performCheck(Block block, EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(BreakBlockAchievement.class)) {
      if (achievement instanceof BreakBlockAchievement) {
        BreakBlockAchievement achvmt = (BreakBlockAchievement)achievement;
        if (achvmt.getBlock() == block)
          Achievement.incrementStats(achvmt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\BreakBlockAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */