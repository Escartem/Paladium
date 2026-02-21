package fr.paladium.palajobs.api.blacklist;

import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.RewardType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IBlackListedItem {
  int getRequiredLevel();
  
  ItemStack getStack();
  
  JobType getType();
  
  boolean is(ItemStack paramItemStack);
  
  boolean canUse(EntityPlayer paramEntityPlayer, IJobsPlayer paramIJobsPlayer, ItemStack paramItemStack);
  
  IReward buildReward(RewardType paramRewardType);
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\blacklist\IBlackListedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */