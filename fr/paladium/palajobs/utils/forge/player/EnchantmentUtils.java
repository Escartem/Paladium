package fr.paladium.palajobs.utils.forge.player;

import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class EnchantmentUtils {
  public static boolean containsEnchantment(ItemStack stack, Enchantment enchantment) {
    return (getEnchantmentLevel(stack, enchantment).intValue() > 0);
  }
  
  public static Integer getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
    if (stack == null)
      return Integer.valueOf(0); 
    Map<Enchantment, Integer> enchantments = EnchantmentHelper.func_82781_a(stack);
    if (enchantments.containsKey(Integer.valueOf(enchantment.field_77352_x)))
      return enchantments.get(Integer.valueOf(enchantment.field_77352_x)); 
    return Integer.valueOf(0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\player\EnchantmentUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */