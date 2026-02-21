package fr.paladium.palamod.util;

import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;

public class ItemStackHelper {
  @Nullable
  public static ItemStack getAndSplit(ItemStack[] stacks, int index, int amount) {
    if (index < 0 || index >= stacks.length || stacks[index] == null || amount <= 0)
      return null; 
    ItemStack itemstack = stacks[index].func_77979_a(amount);
    if ((stacks[index]).field_77994_a == 0)
      stacks[index] = null; 
    return itemstack;
  }
  
  @Nullable
  public static ItemStack getAndRemove(ItemStack[] stacks, int index) {
    if (index >= 0 && index < stacks.length) {
      ItemStack itemstack = stacks[index];
      stacks[index] = null;
      return itemstack;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\ItemStackHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */