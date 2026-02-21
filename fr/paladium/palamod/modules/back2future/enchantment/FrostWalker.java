package fr.paladium.palamod.modules.back2future.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FrostWalker extends Enchantment {
  public static int ID = 36;
  
  public FrostWalker() {
    super(ID, 1, EnumEnchantmentType.armor_feet);
    Enchantment.addToBookList(this);
    func_77322_b("frost_walker");
  }
  
  public int func_77325_b() {
    return 2;
  }
  
  public int func_77321_a(int p_77321_1_) {
    return 1;
  }
  
  public int func_77317_b(int p_77317_1_) {
    return func_77321_a(p_77317_1_) + 40;
  }
  
  public boolean canApplyAtEnchantingTable(ItemStack stack) {
    return (stack != null && (stack.func_77973_b() == Items.field_151122_aG || func_92089_a(stack)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\enchantment\FrostWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */