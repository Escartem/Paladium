package fr.paladium.palajobs.core.jobs.requirement;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.Requirement;
import fr.paladium.palajobs.core.utils.PlacedBlockData;
import fr.paladium.palajobs.utils.forge.location.BlockLocation;
import fr.paladium.palajobs.utils.forge.location.Location;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class BreakBlockRequirement extends Requirement<ItemStack[], BreakBlockRequirement.BreakBlockRequirementData> {
  public BreakBlockRequirement(int level, JobType type, String description, int value, ItemStack target) {
    super(level, type, description, value, new ItemStack[] { target });
  }
  
  public BreakBlockRequirement(int level, JobType type, String description, int value, ItemStack[] target) {
    super(level, type, description, value, target);
  }
  
  protected int validate(EntityPlayer player, BreakBlockRequirementData object) {
    if (object.getItemStack() == null || object.getLocation() == null)
      return 0; 
    ItemStack[] targets = (ItemStack[])getTarget();
    for (ItemStack target : targets) {
      if (target.func_77969_a(object.getItemStack())) {
        Location location = object.getLocation();
        BlockLocation blockLocation = new BlockLocation(location);
        if (((Block.func_149634_a(target.func_77973_b()) == null || Block.func_149634_a(target.func_77973_b()) != Blocks.field_150440_ba) && !object.isInclude()) || 
          !PlacedBlockData.isPlaced(location.getWorld(), blockLocation.x, blockLocation.y, blockLocation.z))
          return (object.getItemStack()).field_77994_a; 
      } 
    } 
    return 0;
  }
  
  public static class BreakBlockRequirementData {
    private final ItemStack itemStack;
    
    private final Location location;
    
    private final boolean include;
    
    public BreakBlockRequirementData(ItemStack itemStack, Location location, boolean include) {
      this.itemStack = itemStack;
      this.location = location;
      this.include = include;
    }
    
    public ItemStack getItemStack() {
      return this.itemStack;
    }
    
    public Location getLocation() {
      return this.location;
    }
    
    public boolean isInclude() {
      return this.include;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\requirement\BreakBlockRequirement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */