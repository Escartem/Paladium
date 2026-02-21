package fr.paladium.palamod.modules.back2future.api.brewing;

import java.lang.reflect.Method;
import net.minecraft.item.ItemStack;

public class BrewingFuelRegistryHelper {
  public static void registerFuel(ItemStack fuel, int brews) {
    try {
      Class<?> cls = Class.forName("ganymedes01.etfuturum.recipes.BrewingFuelRegistry");
      Method m = cls.getMethod("registerFuel", new Class[] { ItemStack.class, int.class });
      m.invoke(null, new Object[] { fuel, Integer.valueOf(brews) });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public static void registerAdvancedFuel(IBrewingFuel fuel) {
    try {
      Class<?> cls = Class.forName("ganymedes01.etfuturum.recipes.BrewingFuelRegistry");
      Method m = cls.getMethod("registerAdvancedFuel", new Class[] { IBrewingFuel.class });
      m.invoke(null, new Object[] { fuel });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\api\brewing\BrewingFuelRegistryHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */