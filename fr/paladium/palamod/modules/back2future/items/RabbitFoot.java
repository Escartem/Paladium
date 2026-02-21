package fr.paladium.palamod.modules.back2future.items;

import cpw.mods.fml.relauncher.ReflectionHelper;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;

public class RabbitFoot extends Item implements IConfigurable {
  public RabbitFoot() {
    func_111206_d("rabbit_foot");
    func_77655_b(Utils.getUnlocalisedName("rabbit_foot"));
    func_77637_a(Back2Future.enableRabbit ? Back2Future.creativeTab : null);
    if (Back2Future.enableRabbit)
      try {
        Field f = ReflectionHelper.findField(PotionHelper.class, new String[] { "potionRequirements", "field_77927_l" });
        f.setAccessible(true);
        HashMap<Integer, String> potionRequirements = (HashMap<Integer, String>)f.get((Object)null);
        potionRequirements.put(Integer.valueOf(Potion.field_76430_j.func_76396_c()), "0 & 1 & !2 & 3");
        Field f2 = ReflectionHelper.findField(PotionHelper.class, new String[] { "potionAmplifiers", "field_77928_m" });
        f2.setAccessible(true);
        HashMap<Integer, String> potionAmplifiers = (HashMap<Integer, String>)f2.get((Object)null);
        potionAmplifiers.put(Integer.valueOf(Potion.field_76430_j.func_76396_c()), "5");
        Field f3 = ReflectionHelper.findField(Potion.class, new String[] { "liquidColor", "field_76414_N" });
        f3.setAccessible(true);
        f3.set(Potion.field_76430_j, Integer.valueOf(2293580));
      } catch (Exception exception) {} 
  }
  
  public String func_150896_i(ItemStack stack) {
    return "+0+1-2+3&4-4+13";
  }
  
  public boolean func_150892_m(ItemStack stack) {
    return true;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableRabbit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\RabbitFoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */