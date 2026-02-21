package fr.paladium.palamod.modules.back2future.items.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockRedSandstone extends ItemBlock {
  private final String[] types = new String[] { "default", "chiseled", "smooth" };
  
  public ItemBlockRedSandstone(Block block) {
    super(block);
    func_77627_a(true);
  }
  
  public String func_77667_c(ItemStack stack) {
    return func_77658_a() + "_" + this.types[
        Math.min(Math.max(0, stack.func_77960_j()), this.types.length - 1)];
  }
  
  public int func_77647_b(int meta) {
    return meta;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\block\ItemBlockRedSandstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */