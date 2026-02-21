package fr.paladium.palamod.modules.paladium.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class VoidStoneInventory implements IInventory {
  public static final int normalSlots = 1;
  
  public void onInventoryUpdate(int slot) {}
  
  public int func_70302_i_() {
    return 2;
  }
  
  public ItemStack func_70301_a(int var1) {
    return null;
  }
  
  public void updateInventoryState(int slot) {
    onInventoryUpdate(slot);
  }
  
  public ItemStack func_70298_a(int slot, int amount) {
    return null;
  }
  
  public String func_145825_b() {
    return "container.voidstone";
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public void func_70296_d() {}
  
  public boolean func_70300_a(EntityPlayer entityplayer) {
    return true;
  }
  
  public ItemStack getStackInSpecialSlot() {
    return null;
  }
  
  public ItemStack func_70304_b(int slot) {
    return null;
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    updateInventoryState(slot);
  }
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
  
  public boolean func_94041_b(int i, ItemStack stack) {
    if (stack.func_77973_b() instanceof net.minecraft.item.ItemTool)
      return false; 
    return ((stack == null || !(stack.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemVoidStone)) && i == 1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\inventory\VoidStoneInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */