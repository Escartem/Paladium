package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.utils;

import net.minecraft.item.ItemStack;

public class RandomItemStack {
  private double chance;
  
  private ItemStack stack;
  
  public void setChance(double chance) {
    this.chance = chance;
  }
  
  public void setStack(ItemStack stack) {
    this.stack = stack;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof RandomItemStack))
      return false; 
    RandomItemStack other = (RandomItemStack)o;
    if (!other.canEqual(this))
      return false; 
    if (Double.compare(getChance(), other.getChance()) != 0)
      return false; 
    Object this$stack = getStack(), other$stack = other.getStack();
    return !((this$stack == null) ? (other$stack != null) : !this$stack.equals(other$stack));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof RandomItemStack;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $chance = Double.doubleToLongBits(getChance());
    result = result * 59 + (int)($chance >>> 32L ^ $chance);
    Object $stack = getStack();
    return result * 59 + (($stack == null) ? 43 : $stack.hashCode());
  }
  
  public String toString() {
    return "RandomItemStackPicker.RandomItemStack(chance=" + getChance() + ", stack=" + getStack() + ")";
  }
  
  public static RandomItemStackBuilder builder() {
    return new RandomItemStackBuilder();
  }
  
  public static class RandomItemStackBuilder {
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
  
  public RandomItemStack(double chance, ItemStack stack) {
    this.chance = chance;
    this.stack = stack;
  }
  
  public double getChance() {
    return this.chance;
  }
  
  public ItemStack getStack() {
    return this.stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\commo\\utils\RandomItemStackPicker$RandomItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */