package fr.paladium.palamod.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PCreativeTab extends CreativeTabs {
  private ItemStack display;
  
  public PCreativeTab(String label) {
    super(label);
  }
  
  public void init(ItemStack stack) {
    this.display = stack;
  }
  
  public ItemStack func_151244_d() {
    return (this.display == null || this.display.func_77973_b() == null) ? new ItemStack(Blocks.field_150348_b) : this.display;
  }
  
  public Item func_78016_d() {
    return (this.display == null || this.display.func_77973_b() == null) ? Item.func_150898_a(Blocks.field_150348_b) : this.display.func_77973_b();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\creativetab\PCreativeTab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */