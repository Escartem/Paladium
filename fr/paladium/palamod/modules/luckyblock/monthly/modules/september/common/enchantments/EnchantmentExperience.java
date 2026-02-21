package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentExperience extends Enchantment {
  public static final String ENCHANTMENT_NAME = "experience";
  
  public static final String DESCRIPTION = "Peut être combiné à une épée. Permet de gagner %s pourcent d'expérience en plus \nen tuant un monstre.";
  
  public static final int[] MULTIPLIERS = new int[] { 1, 2, 5 };
  
  public EnchantmentExperience(int id) {
    super(id, 0, EnumEnchantmentType.weapon);
    func_77322_b("experience");
  }
  
  public boolean func_92089_a(ItemStack stack) {
    return (stack.func_77973_b() != null && stack.func_77973_b() instanceof net.minecraft.item.ItemSword);
  }
  
  public boolean canApplyAtEnchantingTable(ItemStack stack) {
    return false;
  }
  
  public int func_77325_b() {
    return 3;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\enchantments\EnchantmentExperience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */