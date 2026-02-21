package fr.paladium.palarpg.module.equipment.common.loader.data.impl;

import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;

public class RPGBasicRuneItemData extends RPGBasicItemData {
  public void setTier(int tier) {
    this.tier = tier;
  }
  
  private int tier = 0;
  
  public int getTier() {
    return this.tier;
  }
  
  public static RPGBasicRuneItemData create(String id, int tier, RPGItemRarity rarity, int stackSize) {
    RPGBasicRuneItemData data = new RPGBasicRuneItemData();
    data.setTier(tier);
    data.setId(id + "_" + tier);
    data.setMaxStackSize(stackSize);
    data.setRarity(rarity);
    return data;
  }
  
  public String getDescription() {
    return "Fragment de rune.";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\impl\RPGBasicRuneItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */