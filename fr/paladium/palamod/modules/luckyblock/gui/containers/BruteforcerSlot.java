package fr.paladium.palamod.modules.luckyblock.gui.containers;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BruteforcerSlot extends Slot {
  public BruteforcerSlot(TileEntityBruteforcer inv, int slot, int x, int y) {
    super((IInventory)inv, slot, x, y);
  }
  
  public boolean func_75214_a(ItemStack itemStack) {
    Item item = itemStack.func_77973_b();
    if (item == ItemsRegister.FINDIUM)
      return true; 
    return false;
  }
  
  public ItemStack func_75209_a(int count) {
    return super.func_75209_a(count);
  }
  
  public int func_75219_a() {
    return 64;
  }
  
  public void func_82870_a(EntityPlayer p, ItemStack itemStack) {
    func_75208_c(itemStack);
    super.func_82870_a(p, itemStack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\containers\BruteforcerSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */