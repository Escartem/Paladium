package fr.paladium.palamod.modules.luckyblock.gui.containers;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public boolean func_75214_a(@Nullable ItemStack stack) {
    if (Block.func_149634_a(stack.func_77973_b()) instanceof com.jaquadro.minecraft.storagedrawers.block.BlockDrawers || 
      Block.func_149634_a(stack.func_77973_b()) instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest || 
      Block.func_149634_a(stack.func_77973_b()) instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockUltraSafeChest)
      return false; 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\containers\ContainerUltraSafeChest$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */