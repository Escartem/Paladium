package fr.paladium.palamod.modules.alchimiste.common.utils;

import net.minecraft.item.ItemStack;

public class WSEnchantUtils {
  public static boolean hasEnchantment(ItemStack stack, int id) {
    if (stack.func_77986_q() != null)
      for (int i = 0; i < stack.func_77986_q().func_74745_c(); i++) {
        if (stack.func_77986_q().func_150305_b(i).func_74765_d("id") == id)
          return true; 
      }  
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\commo\\utils\WSEnchantUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */