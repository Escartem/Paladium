package fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class LuckyStatsRewardLevel {
  private final int level;
  
  private final List<LuckyStatsReward> rewards;
  
  public int getLevel() {
    return this.level;
  }
  
  public List<LuckyStatsReward> getRewards() {
    return this.rewards;
  }
  
  public LuckyStatsRewardLevel(int level) {
    this.level = level;
    this.rewards = new ArrayList<>();
  }
  
  public void add(LuckyStatsReward reward) {
    this.rewards.add(reward);
  }
  
  public boolean isValid(EntityPlayer player) {
    for (LuckyStatsReward reward : this.rewards) {
      if (!reward.isValid(player))
        return false; 
    } 
    return true;
  }
  
  public void perform(EntityPlayer player) {
    this.rewards.forEach(reward -> reward.perform(player));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\commo\\utils\reward\LuckyStatsRewardLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */