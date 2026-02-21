package fr.paladium.palamod.modules.paladium.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSingle extends Slot {
  ItemStack item;
  
  public SlotSingle(IInventory inventory, int id, int x, int y, Item item) {
    super(inventory, id, x, y);
    this.item = new ItemStack(item);
  }
  
  public boolean func_75214_a(ItemStack stack) {
    if (stack.func_77973_b() == this.item.func_77973_b())
      return true; 
    return false;
  }
  
  public ItemStack func_75209_a(int amount) {
    return super.func_75209_a(amount);
  }
  
  public void func_82870_a(EntityPlayer player, ItemStack stack) {
    func_75208_c(stack);
    super.func_82870_a(player, stack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\inventory\slot\SlotSingle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */