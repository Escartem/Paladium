package fr.paladium.palamod.modules.luckyblock.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public void func_82870_a(EntityPlayer player, ItemStack item) {
    ContainerDyeingMachine.access$000(ContainerDyeingMachine.this).func_70299_a(2, null);
    super.func_82870_a(player, item);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\containers\ContainerDyeingMachine$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */