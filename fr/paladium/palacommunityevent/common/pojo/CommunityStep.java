package fr.paladium.palacommunityevent.common.pojo;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CommunityStep {
  public int percentToReach;
  
  public List<ItemStack> rewards = new ArrayList<>();
  
  public CommunityStep(int percentToReach) {
    this.percentToReach = percentToReach;
  }
  
  public CommunityStep addReward(ItemStack isReward) {
    this.rewards.add(isReward);
    return this;
  }
  
  public NBTTagCompound writeToNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74768_a("percentToReach", this.percentToReach);
    NBTTagList rewardsList = new NBTTagList();
    for (ItemStack reward : this.rewards) {
      NBTTagCompound itemCompound = new NBTTagCompound();
      reward.func_77955_b(itemCompound);
      rewardsList.func_74742_a((NBTBase)itemCompound);
    } 
    compound.func_74782_a("rewards", (NBTBase)rewardsList);
    return compound;
  }
  
  public void readFromNBT(NBTTagCompound compound) {
    this.percentToReach = compound.func_74762_e("percentToReach");
    this.rewards.clear();
    NBTTagList rewardsList = compound.func_150295_c("rewards", 10);
    for (int i = 0; i < rewardsList.func_74745_c(); i++) {
      NBTTagCompound itemCompound = rewardsList.func_150305_b(i);
      ItemStack reward = ItemStack.func_77949_a(itemCompound);
      this.rewards.add(reward);
    } 
  }
  
  public CommunityStep() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\pojo\CommunityStep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */