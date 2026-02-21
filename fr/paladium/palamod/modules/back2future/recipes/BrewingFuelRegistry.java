package fr.paladium.palamod.modules.back2future.recipes;

import fr.paladium.palamod.modules.back2future.api.brewing.IBrewingFuel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BrewingFuelRegistry {
  private static Map<ItemStack, Integer> fuels = new HashMap<>();
  
  private static List<IBrewingFuel> advFuels = new ArrayList<>();
  
  static {
    fuels.put(new ItemStack(Items.field_151065_br), Integer.valueOf(30));
  }
  
  public static void registerFuel(ItemStack fuel, int brews) {
    fuels.put(fuel, Integer.valueOf(brews));
  }
  
  public static void registerAdvancedFuel(IBrewingFuel fuel) {
    advFuels.add(fuel);
  }
  
  public static boolean isFuel(ItemStack fuel) {
    return (getBrewAmount(fuel) > 0);
  }
  
  public static int getBrewAmount(ItemStack fuel) {
    for (IBrewingFuel advFuel : advFuels) {
      int time = advFuel.getBrewingAmount(fuel);
      if (time > 0)
        return time; 
    } 
    for (Map.Entry<ItemStack, Integer> entry : fuels.entrySet()) {
      if (OreDictionary.itemMatches(entry.getKey(), fuel, false))
        return ((Integer)entry.getValue()).intValue(); 
    } 
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\recipes\BrewingFuelRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */