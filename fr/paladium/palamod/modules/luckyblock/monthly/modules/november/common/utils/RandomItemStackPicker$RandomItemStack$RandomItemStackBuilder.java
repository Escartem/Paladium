package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.utils;

import net.minecraft.item.ItemStack;

public class RandomItemStackBuilder {
  private double chance;
  
  private ItemStack stack;
  
  public RandomItemStackBuilder chance(double chance) {
    this.chance = chance;
    return this;
  }
  
  public RandomItemStackBuilder stack(ItemStack stack) {
    this.stack = stack;
    return this;
  }
  
  public RandomItemStackPicker.RandomItemStack build() {
    return new RandomItemStackPicker.RandomItemStack(this.chance, this.stack);
  }
  
  public String toString() {
    return "RandomItemStackPicker.RandomItemStack.RandomItemStackBuilder(chance=" + this.chance + ", stack=" + this.stack + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\commo\\utils\RandomItemStackPicker$RandomItemStack$RandomItemStackBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */