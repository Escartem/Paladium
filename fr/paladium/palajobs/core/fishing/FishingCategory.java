package fr.paladium.palajobs.core.fishing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FishingCategory {
  private final String name;
  
  private final long duration;
  
  private final String rarity;
  
  private final List<FishingSection> sections;
  
  private final Set<FishingReward> rewards;
  
  public String getName() {
    return this.name;
  }
  
  public long getDuration() {
    return this.duration;
  }
  
  public String getRarity() {
    return this.rarity;
  }
  
  public List<FishingSection> getSections() {
    return this.sections;
  }
  
  public Set<FishingReward> getRewards() {
    return this.rewards;
  }
  
  public FishingCategory(String name, long duration, String rarity) {
    this.name = name;
    this.duration = duration;
    this.rarity = rarity;
    this.sections = new LinkedList<>();
    this.rewards = new HashSet<>();
  }
  
  public FishingCategory addSection(FishingSection section) {
    this.sections.add(section);
    return this;
  }
  
  public FishingCategory addReward(FishingReward reward) {
    this.rewards.add(reward);
    return this;
  }
  
  public FishingCategory addRewards(FishingReward... rewards) {
    this.rewards.addAll(new ArrayList<>(Arrays.asList(rewards)));
    return this;
  }
  
  public FishingCategory addRewards(List<FishingReward> rewards) {
    this.rewards.addAll(new ArrayList<>(rewards));
    return this;
  }
  
  public FishingCategory addReward(Item item, float percentage) {
    this.rewards.add(new FishingReward(percentage, new ItemStack(item)));
    return this;
  }
  
  public ItemStack getRandomReward(Random random) {
    if (this.rewards.isEmpty())
      return null; 
    float randomValue = random.nextFloat() * 100.0F;
    float cumulativePercentage = 0.0F;
    for (FishingReward reward : this.rewards) {
      cumulativePercentage += reward.getPercentage();
      if (randomValue <= cumulativePercentage)
        return reward.getItem().func_77946_l(); 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\fishing\FishingCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */