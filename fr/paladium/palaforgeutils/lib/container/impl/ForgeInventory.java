package fr.paladium.palaforgeutils.lib.container.impl;

import fr.paladium.palaforgeutils.lib.container.IForgeInventory;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ForgeInventory implements IForgeInventory {
  private final String inventoryName;
  
  private final List<Slot> slots;
  
  private ItemStack[] content;
  
  public ForgeInventory(String inventoryName, int contentSize) {
    this.inventoryName = inventoryName;
    this.slots = new LinkedList<>();
    this.content = new ItemStack[contentSize];
  }
  
  public void addSlot(Slot slot) {
    this.slots.add(slot);
    if (this.slots.size() > func_70302_i_())
      throw new IndexOutOfBoundsException("Number of slots is upper than inventorySize " + this.slots.size() + " > " + func_70302_i_()); 
  }
  
  public ItemStack func_70301_a(int slot) {
    return (slot >= 0 && slot < (getContent()).length) ? getContent()[slot] : null;
  }
  
  public ItemStack func_70298_a(int slot, int stackSize) {
    if (getContent()[slot] == null)
      return null; 
    if ((getContent()[slot]).field_77994_a <= stackSize) {
      ItemStack itemStack = getContent()[slot];
      getContent()[slot] = null;
      func_70296_d();
      return itemStack;
    } 
    ItemStack itemstack = getContent()[slot].func_77979_a(stackSize);
    if ((getContent()[slot]).field_77994_a == 0)
      getContent()[slot] = null; 
    func_70296_d();
    return itemstack;
  }
  
  public ItemStack func_70304_b(int slot) {
    if (getContent()[slot] != null) {
      ItemStack itemstack = getContent()[slot];
      getContent()[slot] = null;
      return itemstack;
    } 
    return null;
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    getContent()[slot] = stack;
    if (stack != null && stack.field_77994_a > func_70297_j_())
      stack.field_77994_a = func_70297_j_(); 
    func_70296_d();
  }
  
  public int func_70302_i_() {
    return (getContent()).length;
  }
  
  public String func_145825_b() {
    return this.inventoryName;
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return true;
  }
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
  
  public void func_70296_d() {}
  
  public boolean func_94041_b(int slot, ItemStack stack) {
    if (slot < 0)
      return false; 
    if (slot >= this.slots.size())
      return true; 
    return getSlot(slot).func_75214_a(stack);
  }
  
  public Slot getSlot(int index) {
    return this.slots.get(index);
  }
  
  public List<Slot> getSlots() {
    return this.slots;
  }
  
  public ItemStack[] getContent() {
    return this.content;
  }
  
  public void setContent(ItemStack[] content) {
    this.content = content;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\container\impl\ForgeInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */