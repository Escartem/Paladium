package fr.paladium.palamod.modules.hunter.gui.containers.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBackPack extends Slot {
  public SlotBackPack(IInventory inv, int index, int x, int y) {
    super(inv, index, x, y);
  }
  
  public boolean func_75214_a(ItemStack stack) {
    if (stack.func_77973_b() instanceof fr.paladium.palamod.modules.hunter.items.ItemHunterBackpack || stack.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemDrawers || stack
      .func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.pack.ItemDrawersPack || stack
      .func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemCustomDrawers || stack
      .func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemSuitcase || stack
      .func_77973_b().func_77658_a().toLowerCase().contains("drawer") || stack
      .func_77973_b().func_77658_a().toLowerCase().contains("safe_chest"))
      return false; 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\gui\containers\slot\SlotBackPack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */