package fr.paladium.palarpg.module.equipment.common.loader.data.impl;

import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import java.io.File;
import java.util.List;
import java.util.Map;

public class RPGConsumableItemData extends RPGBasicItemData {
  private RPGConsumableFoodStats foodStats;
  
  private int durability;
  
  private Map<RPGStatApplyType, List<ItemStatMutator>> mutators;
  
  public RPGConsumableFoodStats getFoodStats() {
    return this.foodStats;
  }
  
  public int getDurability() {
    return this.durability;
  }
  
  public Map<RPGStatApplyType, List<ItemStatMutator>> getMutators() {
    return this.mutators;
  }
  
  public void createDefault(File dir) {
    super.createDefault(dir);
    this.foodStats = new RPGConsumableFoodStats(1, 32, 0.6F, true, false);
  }
  
  public void onLoad(File dir) {
    super.onLoad(dir);
    this.mutators.forEach((applyType, mutators) -> mutators.forEach(()));
  }
  
  public static class RPGConsumableFoodStats {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\impl\RPGConsumableItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */