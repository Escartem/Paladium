package fr.paladium.palamod.modules.luckyblock.gui.halloween;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public boolean func_75214_a(ItemStack item) {
    return item.func_77969_a((ContainerHalloweenTrade.access$000(ContainerHalloweenTrade.this)[0]).input);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\halloween\ContainerHalloweenTrade$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */