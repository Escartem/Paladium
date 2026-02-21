package fr.paladium.palarpg.module.equipment.common.loader.cache;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGCraftData;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class RPGCraftCache {
  private static final Map<ItemStack, RPGCraftData> CRAFT_CACHE = new HashMap<>();
  
  public static boolean hasRPGLevelRequirement(String stringStack) {
    ItemStack stack = ItemUtils.parse(stringStack);
    if (stack == null)
      return false; 
    return hasRPGLevelRequirement(stack);
  }
  
  public static boolean hasRPGLevelRequirement(ItemStack stack) {
    boolean result = false;
    for (Map.Entry<ItemStack, RPGCraftData> entry : CRAFT_CACHE.entrySet()) {
      if (((ItemStack)entry.getKey()).func_77969_a(stack) && ItemStack.func_77970_a(entry.getKey(), stack)) {
        result = true;
        break;
      } 
    } 
    return result;
  }
  
  public static RPGCraftData getRPGCraftData(String stringStack) {
    ItemStack stack = ItemUtils.parse(stringStack);
    if (stack == null)
      return null; 
    return getRPGCraftData(stack);
  }
  
  public static RPGCraftData getRPGCraftData(ItemStack stack) {
    if (!hasRPGLevelRequirement(stack))
      return null; 
    RPGCraftData result = null;
    for (Map.Entry<ItemStack, RPGCraftData> entry : CRAFT_CACHE.entrySet()) {
      if (((ItemStack)entry.getKey()).func_77969_a(stack) && ItemStack.func_77970_a(entry.getKey(), stack)) {
        result = entry.getValue();
        break;
      } 
    } 
    return result;
  }
  
  public static void registerCraftData(RPGCraftData data) {
    CRAFT_CACHE.put(data.getResultItem(), data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\cache\RPGCraftCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */