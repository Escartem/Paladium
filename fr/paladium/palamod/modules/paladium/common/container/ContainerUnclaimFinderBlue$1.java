package fr.paladium.palamod.modules.paladium.common.container;

import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public boolean func_75214_a(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    if (Block.func_149634_a(stack.func_77973_b()) != null && 
      Block.func_149634_a(stack.func_77973_b()).hasTileEntity(stack.func_77973_b().func_77647_b(0)))
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\ContainerUnclaimFinderBlue$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */