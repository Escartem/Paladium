package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.item.ItemStack;

public class RandomItemStackPicker {
  private Random random;
  
  private List<RandomItemStack> items;
  
  public void setRandom(Random random) {
    this.random = random;
  }
  
  public void setItems(List<RandomItemStack> items) {
    this.items = items;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof RandomItemStackPicker))
      return false; 
    RandomItemStackPicker other = (RandomItemStackPicker)o;
    if (!other.canEqual(this))
      return false; 
    Object this$random = getRandom(), other$random = other.getRandom();
    if ((this$random == null) ? (other$random != null) : !this$random.equals(other$random))
      return false; 
    Object<RandomItemStack> this$items = (Object<RandomItemStack>)getItems(), other$items = (Object<RandomItemStack>)other.getItems();
    return !((this$items == null) ? (other$items != null) : !this$items.equals(other$items));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof RandomItemStackPicker;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $random = getRandom();
    result = result * 59 + (($random == null) ? 43 : $random.hashCode());
    Object<RandomItemStack> $items = (Object<RandomItemStack>)getItems();
    return result * 59 + (($items == null) ? 43 : $items.hashCode());
  }
  
  public String toString() {
    return "RandomItemStackPicker(random=" + getRandom() + ", items=" + getItems() + ")";
  }
  
  public Random getRandom() {
    return this.random;
  }
  
  public List<RandomItemStack> getItems() {
    return this.items;
  }
  
  public RandomItemStackPicker(List<RandomItemStack> items) {
    this.items = items;
    this.random = new Random();
    this.items.sort((o1, o2) -> Double.compare(o1.getChance(), o2.getChance()));
  }
  
  public ItemStack pick() {
    double randomValue = Math.random() * 100.0D;
    double itemChance = 0.0D;
    for (RandomItemStack item : this.items) {
      if (randomValue <= item.getChance()) {
        itemChance = item.getChance();
        break;
      } 
    } 
    if (itemChance == 0.0D)
      return ((RandomItemStack)this.items.get(this.items.size() - 1)).getStack().func_77946_l(); 
    List<RandomItemStack> resultList = new ArrayList<>();
    for (RandomItemStack item : this.items) {
      if (item.getChance() == itemChance)
        resultList.add(item); 
    } 
    return ((RandomItemStack)resultList.get(this.random.nextInt(resultList.size()))).getStack().func_77946_l();
  }
  
  public static class RandomItemStack {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\commo\\utils\RandomItemStackPicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */