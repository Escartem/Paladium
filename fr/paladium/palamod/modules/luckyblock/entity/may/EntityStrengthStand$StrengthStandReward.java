package fr.paladium.palamod.modules.luckyblock.entity.may;

import net.minecraft.item.ItemStack;

public class StrengthStandReward {
  private double minDamage;
  
  private Double maxDamage;
  
  private ItemStack reward;
  
  public StrengthStandReward(double minDamage, Double maxDamage, ItemStack reward) {
    this.minDamage = minDamage;
    this.maxDamage = maxDamage;
    this.reward = reward;
  }
  
  public double getMinDamage() {
    return this.minDamage;
  }
  
  public void setMinDamage(double minDamage) {
    this.minDamage = minDamage;
  }
  
  public Double getMaxDamage() {
    return this.maxDamage;
  }
  
  public void setMaxDamage(Double maxDamage) {
    this.maxDamage = maxDamage;
  }
  
  public ItemStack getReward() {
    return this.reward;
  }
  
  public void setReward(ItemStack reward) {
    this.reward = reward;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\may\EntityStrengthStand$StrengthStandReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */