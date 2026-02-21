package fr.paladium.palarpg.module.equipment.common.loader.data.impl;

public class RPGConsumableFoodStats {
  private int healAmount;
  
  private int itemUseDuration;
  
  private float saturationModifier;
  
  private boolean alwaysEdible;
  
  private boolean infinite;
  
  public RPGConsumableFoodStats(int healAmount, int itemUseDuration, float saturationModifier, boolean alwaysEdible, boolean infinite) {
    this.healAmount = 1;
    this.itemUseDuration = 32;
    this.saturationModifier = 0.6F;
    this.alwaysEdible = false;
    this.infinite = false;
    this.healAmount = healAmount;
    this.itemUseDuration = itemUseDuration;
    this.saturationModifier = saturationModifier;
    this.alwaysEdible = alwaysEdible;
    this.infinite = infinite;
  }
  
  public int getHealAmount() {
    return this.healAmount;
  }
  
  public int getItemUseDuration() {
    return this.itemUseDuration;
  }
  
  public float getSaturationModifier() {
    return this.saturationModifier;
  }
  
  public boolean isAlwaysEdible() {
    return this.alwaysEdible;
  }
  
  public boolean isInfinite() {
    return this.infinite;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\impl\RPGConsumableItemData$RPGConsumableFoodStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */