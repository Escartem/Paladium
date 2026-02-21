package fr.paladium.palacommunityevent.client.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CommunityEventInventory implements IInventory {
  private ItemStack[] set = new ItemStack[1];
  
  public int func_70302_i_() {
    return this.set.length;
  }
  
  public ItemStack func_70301_a(int slot) {
    return this.set[slot];
  }
  
  public ItemStack func_70298_a(int slot, int amount) {
    if (this.set[slot] == null)
      return null; 
    if ((this.set[slot]).field_77994_a <= amount) {
      ItemStack itemStack = this.set[slot];
      this.set[slot] = null;
      func_70296_d();
      return itemStack;
    } 
    ItemStack stack = this.set[slot].func_77979_a(amount);
    if ((this.set[slot]).field_77994_a == 0)
      this.set[slot] = null; 
    func_70296_d();
    return stack;
  }
  
  public ItemStack func_70304_b(int slot) {
    if (this.set[slot] == null)
      return null; 
    ItemStack stack = this.set[slot];
    this.set[slot] = null;
    return stack;
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    this.set[slot] = stack;
    if (stack == null || stack.field_77994_a <= func_70297_j_()) {
      func_70296_d();
      return;
    } 
    stack.field_77994_a = func_70297_j_();
    func_70296_d();
  }
  
  public String func_145825_b() {
    return "communityevent";
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public void func_70296_d() {}
  
  public boolean func_70300_a(EntityPlayer player) {
    return true;
  }
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
  
  public boolean func_94041_b(int slot, ItemStack stack) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\CommunityEventInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */