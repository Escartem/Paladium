package fr.paladium.palamod.modules.paladium.common.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class AlchemyCreatorPotionRecipies {
  private static final AlchemyCreatorPotionRecipies instance = new AlchemyCreatorPotionRecipies();
  
  private Map smeltingList = new HashMap<>();
  
  public void add(ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4, ItemStack stack) {
    ItemStack[] stackList = { stack1, stack2, stack3, stack4 };
    this.smeltingList.put(stackList, stack);
  }
  
  public ItemStack getSmeltingResult(ItemStack[] stack) {
    Map.Entry entry;
    Iterator<Map.Entry> iterator = this.smeltingList.entrySet().iterator();
    do {
      if (!iterator.hasNext())
        return null; 
      entry = iterator.next();
    } while (!isSameKey(stack, (ItemStack[])entry.getKey()));
    return (ItemStack)entry.getValue();
  }
  
  private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2) {
    boolean isSame = false;
    for (int i = 0; i <= 3; i++) {
      if (stackList[i].func_77973_b() == stackList2[i].func_77973_b()) {
        isSame = true;
      } else {
        return false;
      } 
    } 
    return isSame;
  }
  
  public static AlchemyCreatorPotionRecipies getManager() {
    return instance;
  }
  
  public Map<ItemStack[], ItemStack> getSmeltingList() {
    return this.smeltingList;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\crafting\AlchemyCreatorPotionRecipies.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */