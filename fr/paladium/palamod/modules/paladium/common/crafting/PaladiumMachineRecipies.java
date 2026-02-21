package fr.paladium.palamod.modules.paladium.common.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PaladiumMachineRecipies {
  private static Map<ItemStack[], ItemStack> recipes = (Map)new HashMap<>();
  
  public static void add(ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4, ItemStack stack5, ItemStack stack) {
    ItemStack[] stackList = { stack1, stack2, stack3, stack4, stack5 };
    recipes.put(stackList, stack);
  }
  
  public static void add(Item item1, Block item2, Item item3, Block item4, Item item5, ItemStack stack) {
    add(new ItemStack(item1), new ItemStack(item2), new ItemStack(item3), new ItemStack(item4), new ItemStack(item5), stack);
  }
  
  public static void add(Item item1, Item item2, Item item3, Item item4, Item item5, ItemStack stack) {
    add(new ItemStack(item1), new ItemStack(item2), new ItemStack(item3), new ItemStack(item4), new ItemStack(item5), stack);
  }
  
  public static void add(Block item1, Block item2, Item item3, Block item4, Block item5, ItemStack stack) {
    add(new ItemStack(item1), new ItemStack(item2), new ItemStack(item3), new ItemStack(item4), new ItemStack(item5), stack);
  }
  
  public static void add(Item item1, Block item2, Block item3, Block item4, Block item5, ItemStack stack) {
    add(new ItemStack(item1), new ItemStack(item2), new ItemStack(item3), new ItemStack(item4), new ItemStack(item5), stack);
  }
  
  public static ItemStack getSmeltingResult(ItemStack[] stack) {
    Map.Entry<?, ?> entry;
    Iterator<?> iterator = recipes.entrySet().iterator();
    do {
      if (!iterator.hasNext())
        return null; 
      entry = (Map.Entry<?, ?>)iterator.next();
    } while (!isSameKey(stack, (ItemStack[])entry.getKey()));
    ItemStack itemstack = (ItemStack)entry.getValue();
    return itemstack;
  }
  
  private static boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2) {
    for (int i = 0; i <= 4; i++) {
      boolean itemsMatch = (stackList[i].func_77973_b() == stackList2[i].func_77973_b());
      boolean damageMatchOrSpecialCondition = (stackList[i].func_77960_j() == stackList2[i].func_77960_j() || stackList[0].func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.BaseItemRing || stackList[0].func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.BaseItemRingEndium || stackList[0].func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemStickBase || stackList[0].func_77973_b() instanceof fr.paladium.mount.core.items.horns.ItemHorn);
      if (!itemsMatch || !damageMatchOrSpecialCondition)
        return false; 
    } 
    return true;
  }
  
  public static Map<ItemStack[], ItemStack> getRecipeList() {
    return recipes;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\crafting\PaladiumMachineRecipies.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */