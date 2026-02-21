package fr.paladium.palamod.modules.alchimiste.common.enchant;

import java.util.function.Predicate;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantmentBase extends Enchantment {
  public int hunterLevel;
  
  public int farmerLevel;
  
  public int alchemistLevel;
  
  public int mineurLevel;
  
  private int maxLevel = 1;
  
  private boolean enabled = true;
  
  private final Predicate<Item> predicate;
  
  public EnchantmentBase(int id, int weight, EnumEnchantmentType type, String name, int hunterLevel, int farmerLevel, int alchemistLevel, int mineurLevel) {
    super(id, weight, type);
    func_77322_b(name);
    this.hunterLevel = hunterLevel;
    this.farmerLevel = farmerLevel;
    this.alchemistLevel = alchemistLevel;
    this.mineurLevel = mineurLevel;
    this.predicate = (item -> type.func_77557_a(item));
  }
  
  public EnchantmentBase(int id, int weight, Predicate<Item> predicate, String name, int hunterLevel, int farmerLevel, int alchemistLevel, int mineurLevel) {
    super(id, weight, EnumEnchantmentType.all);
    func_77322_b(name);
    this.hunterLevel = hunterLevel;
    this.farmerLevel = farmerLevel;
    this.alchemistLevel = alchemistLevel;
    this.mineurLevel = mineurLevel;
    this.predicate = predicate;
  }
  
  public boolean func_92089_a(ItemStack stack) {
    return canApply(stack.func_77973_b());
  }
  
  public boolean canApply(Item item) {
    return this.predicate.test(item);
  }
  
  public int func_77325_b() {
    return this.maxLevel;
  }
  
  public EnchantmentBase setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
    return this;
  }
  
  public EnchantmentBase setEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public boolean isAllowedOnBooks() {
    return false;
  }
  
  public boolean canApplyAtEnchantingTable(ItemStack stack) {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\enchant\EnchantmentBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */