package fr.paladium.palamod.modules.paladium.utils;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.common.items.ItemArrowBase;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BowHelper {
  public static final int RANGE = 10;
  
  public static final int SPEED = 11;
  
  public static int[] getModifiers(ItemStack stack) {
    if (stack == null || !(stack.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.weapons.ItemPaladiumBow))
      return null; 
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
      stack.func_77978_p().func_74768_a("modifiersammount", 0);
    } 
    NBTTagCompound tag = stack.func_77978_p();
    if (!tag.func_74764_b("modifiersarray")) {
      tag.func_74783_a("modifiersarray", new int[68]);
      return null;
    } 
    ArrayList<Integer> integerArray = new ArrayList<>();
    for (int i : tag.func_74759_k("modifiersarray")) {
      if (i != 0)
        integerArray.add(Integer.valueOf(i)); 
    } 
    return convertIntegers(integerArray);
  }
  
  public static int[] convertIntegers(List<Integer> integers) {
    int[] ret = new int[integers.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = ((Integer)integers.get(i)).intValue(); 
    return ret;
  }
  
  public static void applyModifiers(ItemStack stack, int modifier) {
    if (stack == null || !(stack.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.weapons.ItemPaladiumBow))
      return; 
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
      stack.func_77978_p().func_74768_a("modifiersammount", 1);
    } 
    NBTTagCompound tag = stack.func_77978_p();
    if (!tag.func_74764_b("modifiersarray"))
      tag.func_74783_a("modifiersarray", new int[68]); 
    int[] modifiersArray = tag.func_74759_k("modifiersarray");
    int modifiersSzize = modifiersArray.length;
    int[] finalModifiers = new int[modifiersSzize + 1];
    for (int i = 0; i < modifiersSzize; i++)
      finalModifiers[i] = modifiersArray[i]; 
    finalModifiers[modifiersSzize] = modifier;
    tag.func_74783_a("modifiersarray", finalModifiers);
  }
  
  public static boolean canApply(ItemStack stack, int type) {
    int[] result = getModifiers(stack);
    if (result == null)
      return true; 
    for (int i = 0; i < result.length; i++) {
      if (result[i] == type)
        return false; 
    } 
    if (type == 11 && EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack) > 0)
      return false; 
    return true;
  }
  
  public static String getModifierName(int type) {
    switch (type) {
      case 10:
        return "§a Range §r+";
      case 11:
        return "§6 Speed §r+";
    } 
    return "";
  }
  
  public static Item getItem(int type) {
    ItemArrowBase itemArrowBase5, itemArrowBase4, itemArrowBase3, itemArrowBase2, itemArrowBase1;
    switch (type) {
      case 0:
        return (Item)ItemsRegister.ARROW_POISON;
      case 2:
        return (Item)ItemsRegister.ARROW_SLOWNESS;
      case 3:
        return (Item)ItemsRegister.ARROW_SWITCH;
      case 1:
        return (Item)ItemsRegister.ARROW_WITHER;
      case 6:
        return (Item)PPRegisterer.PPItems.MID_LIFE;
    } 
    Item arrow = null;
    return arrow;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\BowHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */