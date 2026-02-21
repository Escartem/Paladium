package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.containers;

import javax.annotation.Nullable;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public boolean func_75214_a(@Nullable ItemStack stack) {
    if (stack.func_77973_b().equals(Item.func_150898_a(Blocks.field_150347_e)) || stack
      .func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemVoidStoneMinage)
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\containers\ContainerCrabCobbleBreaker$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */