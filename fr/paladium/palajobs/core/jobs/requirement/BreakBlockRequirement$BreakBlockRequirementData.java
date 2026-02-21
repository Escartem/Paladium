package fr.paladium.palajobs.core.jobs.requirement;

import fr.paladium.palajobs.utils.forge.location.Location;
import net.minecraft.item.ItemStack;

public class BreakBlockRequirementData {
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


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\requirement\BreakBlockRequirement$BreakBlockRequirementData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */