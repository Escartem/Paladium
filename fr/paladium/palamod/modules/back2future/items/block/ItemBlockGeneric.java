package fr.paladium.palamod.modules.back2future.items.block;

import fr.paladium.palamod.modules.back2future.blocks.BlockGeneric;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGeneric extends ItemBlock {
  public ItemBlockGeneric(Block block) {
    super(block);
    func_77627_a(true);
  }
  
  public String func_77667_c(ItemStack stack) {
    if (this.field_150939_a instanceof BlockGeneric) {
      String name = ((BlockGeneric)this.field_150939_a).getNameFor(stack.func_77960_j());
      if ("".equals(name))
        return func_77658_a(); 
      return func_77658_a() + "_" + name;
    } 
    return func_77658_a() + "_" + stack.func_77960_j();
  }
  
  public int func_77647_b(int meta) {
    return meta;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\block\ItemBlockGeneric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */