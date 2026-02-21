package fr.paladium.palamod.modules.luckyblock.blocks.containers.slots;

import fr.paladium.palamod.modules.luckyblock.blocks.containers.ContainerClipboard;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotClipboard extends Slot {
  final ContainerClipboard container;
  
  public SlotClipboard(ContainerClipboard clipboardContainer, IInventory iInventory, int i, int j, int k) {
    super(iInventory, i, j, k);
    this.container = clipboardContainer;
  }
  
  public boolean func_75214_a(ItemStack stack) {
    if (stack != null && stack.func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.items.ItemClipboard)
      return true; 
    return false;
  }
  
  public int func_75219_a() {
    return 1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\containers\slots\SlotClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */