package fr.paladium.palacommunityevent.common.manager;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class AdventCalendarManager {
  private static Map<Integer, ItemStack[][]> data = (Map)new HashMap<>();
  
  public static void register(int year, ItemStack[]... items) {
    data.put(Integer.valueOf(year), items);
  }
  
  public static boolean isValid(int year) {
    return (data.containsKey(Integer.valueOf(year)) && ((ItemStack[][])data.get(Integer.valueOf(year))).length == 24);
  }
  
  public static ItemStack[] getItems(int year, int day) {
    return isValid(year) ? ((ItemStack[][])data.get(Integer.valueOf(year)))[day - 1] : null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\manager\AdventCalendarManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */