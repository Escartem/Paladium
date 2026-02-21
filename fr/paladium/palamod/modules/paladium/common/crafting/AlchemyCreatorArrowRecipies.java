package fr.paladium.palamod.modules.paladium.common.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class AlchemyCreatorArrowRecipies {
  private static final AlchemyCreatorArrowRecipies instance = new AlchemyCreatorArrowRecipies();
  
  private final Map<ItemStack[], ItemStack> smeltingList = (Map)new HashMap<>();
  
  public void add(ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4, ItemStack stack) {
    ItemStack[] stackList = { stack1, stack2, stack3, stack4 };
    this.smeltingList.put(stackList, stack);
  }
  
  public ItemStack getSmeltingResult(ItemStack[] stack) {
    Map.Entry<ItemStack[], ItemStack> entry;
    Iterator<Map.Entry<ItemStack[], ItemStack>> iterator = this.smeltingList.entrySet().iterator();
    do {
      if (!iterator.hasNext())
        return null; 
      entry = iterator.next();
    } while (!isSameKey(stack, entry.getKey()));
    return entry.getValue();
  }
  
  private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2) {
    boolean isSame = false;
    for (int i = 0; i <= 3; i++) {
      if (stackList[i].func_77973_b() != stackList2[i].func_77973_b())
        return false; 
      isSame = true;
    } 
    return isSame;
  }
  
  public static AlchemyCreatorArrowRecipies getManager() {
    return instance;
  }
  
  public Map<ItemStack[], ItemStack> getSmeltingList() {
    return this.smeltingList;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\crafting\AlchemyCreatorArrowRecipies.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */