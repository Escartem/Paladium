package fr.paladium.palamod.modules.achievements.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

public class BlockPlaceAchievement extends Achievement {
  private final Block block;
  
  public Block getBlock() {
    return this.block;
  }
  
  public static BlockPlaceAchievementBuilder builder() {
    return new BlockPlaceAchievementBuilder();
  }
  
  public static class BlockPlaceAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String icon;
    
    private Block block;
    
    public BlockPlaceAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public BlockPlaceAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public BlockPlaceAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public BlockPlaceAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public BlockPlaceAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public BlockPlaceAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public BlockPlaceAchievementBuilder block(Block block) {
      this.block = block;
      return this;
    }
    
    public BlockPlaceAchievement build() {
      return new BlockPlaceAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.icon, this.block);
    }
    
    public String toString() {
      return "BlockPlaceAchievement.BlockPlaceAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", icon=" + this.icon + ", block=" + this.block + ")";
    }
  }
  
  public BlockPlaceAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, Block block) {
    super(id, category, name, description, nbToValidate, icon);
    this.block = block;
  }
  
  public static void performCheck(EntityPlayer player) {
    for (Achievement value : AchievementManager.getInstance().getAchievements(BlockPlaceAchievement.class)) {
      BlockPlaceAchievement achievement = (BlockPlaceAchievement)value;
      Achievement.incrementStats(achievement, player, 1);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\types\BlockPlaceAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */