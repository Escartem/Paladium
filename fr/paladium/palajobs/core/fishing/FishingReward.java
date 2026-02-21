package fr.paladium.palajobs.core.fishing;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FishingReward {
  private final float percentage;
  
  private final ItemStack item;
  
  public FishingReward(float percentage, ItemStack item) {
    this.percentage = percentage;
    this.item = item;
  }
  
  public float getPercentage() {
    return this.percentage;
  }
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public FishingReward(float percentage, Item item) {
    this.percentage = percentage;
    this.item = new ItemStack(item);
  }
  
  public FishingReward(float percentage, Block block) {
    this.percentage = percentage;
    this.item = new ItemStack(block);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\fishing\FishingReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */