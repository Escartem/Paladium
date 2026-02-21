package fr.paladium.palajobs.core.jobs;

import fr.paladium.palajobs.api.blacklist.IBlackListedItem;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.pojo.rewards.Reward;
import fr.paladium.palajobs.utils.JobsUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BlackListedItem implements IBlackListedItem {
  private final int requiredLevel;
  
  private final JobType type;
  
  private final ItemStack stack;
  
  public BlackListedItem(int requiredLevel, JobType type, ItemStack stack, List<String> hovers) {
    this.requiredLevel = requiredLevel;
    this.type = type;
    this.stack = stack;
    this.hovers = hovers;
  }
  
  public int getRequiredLevel() {
    return this.requiredLevel;
  }
  
  public JobType getType() {
    return this.type;
  }
  
  public ItemStack getStack() {
    return this.stack;
  }
  
  private List<String> hovers = new ArrayList<>();
  
  public List<String> getHovers() {
    return this.hovers;
  }
  
  public BlackListedItem(int requiredLevel, JobType type, ItemStack stack) {
    this.requiredLevel = requiredLevel;
    this.type = type;
    this.stack = stack;
  }
  
  public boolean canUse(EntityPlayer player, IJobsPlayer data, ItemStack target) {
    if (this.stack == null || target == null)
      return false; 
    if (!JobsUtils.isItemEqual(this.stack, target))
      return true; 
    int level = JobExpUtils.getLevel(data.getExperience(this.type));
    if (level < this.requiredLevel)
      return false; 
    return true;
  }
  
  public IReward buildReward(RewardType type) {
    return (IReward)Reward.builder().type(type).logo(this.stack).level(this.requiredLevel).hover(this.hovers).build();
  }
  
  public boolean is(ItemStack stack) {
    return JobsUtils.isItemEqual(this.stack, stack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\BlackListedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */