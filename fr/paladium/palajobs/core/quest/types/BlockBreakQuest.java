package fr.paladium.palajobs.core.quest.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.core.utils.PlacedBlockData;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.forge.location.BlockLocation;
import fr.paladium.palajobs.utils.forge.location.Location;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class BlockBreakQuest extends AbstractQuest {
  private ItemStack stack;
  
  public ItemStack getStack() {
    return this.stack;
  }
  
  public static BlockBreakQuestBuilder builder() {
    return new BlockBreakQuestBuilder();
  }
  
  public static class BlockBreakQuestBuilder {
    private String id;
    
    private JobType job;
    
    private String name;
    
    private String description;
    
    private String icon;
    
    private QuestType type;
    
    private int quantity;
    
    private double earnedExperience;
    
    private ItemStack stack;
    
    public BlockBreakQuestBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public BlockBreakQuestBuilder job(JobType job) {
      this.job = job;
      return this;
    }
    
    public BlockBreakQuestBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public BlockBreakQuestBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public BlockBreakQuestBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public BlockBreakQuestBuilder type(QuestType type) {
      this.type = type;
      return this;
    }
    
    public BlockBreakQuestBuilder quantity(int quantity) {
      this.quantity = quantity;
      return this;
    }
    
    public BlockBreakQuestBuilder earnedExperience(double earnedExperience) {
      this.earnedExperience = earnedExperience;
      return this;
    }
    
    public BlockBreakQuestBuilder stack(ItemStack stack) {
      this.stack = stack;
      return this;
    }
    
    public BlockBreakQuest build() {
      return new BlockBreakQuest(this.id, this.job, this.name, this.description, this.icon, this.type, this.quantity, this.earnedExperience, this.stack);
    }
    
    public String toString() {
      return "BlockBreakQuest.BlockBreakQuestBuilder(id=" + this.id + ", job=" + this.job + ", name=" + this.name + ", description=" + this.description + ", icon=" + this.icon + ", type=" + this.type + ", quantity=" + this.quantity + ", earnedExperience=" + this.earnedExperience + ", stack=" + this.stack + ")";
    }
  }
  
  public BlockBreakQuest(String id, JobType job, String name, String description, String icon, QuestType type, int quantity, double earnedExperience, ItemStack stack) {
    super(id, job, name, description, icon, type, quantity, earnedExperience);
    this.stack = stack;
  }
  
  public static void performCheck(EntityPlayer player, Location location, ItemStack stack, boolean include) {
    BlockLocation blockLocation = new BlockLocation(location);
    for (AbstractQuest quest : JobsManager.getInstance().getQuests(QuestType.BLOCK_BREAK)) {
      if (!(quest instanceof BlockBreakQuest))
        continue; 
      BlockBreakQuest currentQuest = (BlockBreakQuest)quest;
      ItemStack currentStack = currentQuest.getStack();
      if (currentStack.func_77969_a(stack)) {
        if (Block.func_149634_a(currentStack.func_77973_b()) != null && Block.func_149634_a(currentStack.func_77973_b()) == Blocks.field_150440_ba) {
          if (PlacedBlockData.isPlaced(location.getWorld(), blockLocation.x, blockLocation.y, blockLocation.z))
            return; 
        } else if (include && 
          PlacedBlockData.isPlaced(location.getWorld(), blockLocation.x, blockLocation.y, blockLocation.z)) {
          return;
        } 
        incrementStats(currentQuest, player, 1);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\types\BlockBreakQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */