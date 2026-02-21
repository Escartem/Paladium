package fr.paladium.palamod.modules.smeltery.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GrinderRecipe {
  private static final GrinderRecipe instance = new GrinderRecipe();
  
  private final Map<ItemStack, Integer> smeltingAmmount = new HashMap<>();
  
  private final Map<ItemStack[], ItemStack> smeltingList = (Map)new HashMap<>();
  
  private final Map<Item, Item> upgradeList = new HashMap<>();
  
  public Map<Item, Item> getUpgradeList() {
    return this.upgradeList;
  }
  
  private final Map<Item, Integer> upgradeResult = new HashMap<>();
  
  public Map<Item, Integer> getUpgradeResult() {
    return this.upgradeResult;
  }
  
  public void add(ItemStack stack1, ItemStack stack2, ItemStack stack, int ammount) {
    ItemStack[] stackList = { stack1, stack2 };
    this.smeltingAmmount.put(stack, Integer.valueOf(ammount));
    this.smeltingList.put(stackList, stack);
  }
  
  public void addUpgrade(Item tool, Item upgrade, int type) {
    this.upgradeList.put(upgrade, tool);
    this.upgradeResult.put(upgrade, Integer.valueOf(type));
  }
  
  public Item getUpgrade(int id) {
    for (Map.Entry<Item, Integer> i : this.upgradeResult.entrySet()) {
      if (((Integer)i.getValue()).equals(Integer.valueOf(id)))
        return i.getKey(); 
    } 
    return null;
  }
  
  public ItemStack getSmeltingResult(ItemStack[] stack) {
    Map.Entry<?, ?> entry;
    Iterator<?> iterator = this.smeltingList.entrySet().iterator();
    do {
      if (!iterator.hasNext())
        return null; 
      entry = (Map.Entry<?, ?>)iterator.next();
    } while (!isSameKey(stack, (ItemStack[])entry.getKey()));
    return (ItemStack)entry.getValue();
  }
  
  private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2) {
    boolean isSame = false;
    for (int i = 0; i <= 1; i++) {
      if (stackList[i].func_77973_b() != stackList2[i].func_77973_b())
        return false; 
      isSame = true;
    } 
    return isSame;
  }
  
  public boolean isUpgradable(Item tool, Item upgrade) {
    return this.upgradeList.containsKey(upgrade);
  }
  
  public int getUpgrade(Item upgrade) {
    return ((Integer)this.upgradeResult.get(upgrade)).intValue();
  }
  
  public Map<ItemStack[], ItemStack> getSmeltingList() {
    return this.smeltingList;
  }
  
  public static GrinderRecipe getManager() {
    return instance;
  }
  
  public int getSmeltingAmmount(ItemStack stack) {
    return ((Integer)this.smeltingAmmount.get(stack)).intValue();
  }
  
  public Map<ItemStack, Integer> getSmeltingAmmount() {
    return this.smeltingAmmount;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\crafting\GrinderRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */