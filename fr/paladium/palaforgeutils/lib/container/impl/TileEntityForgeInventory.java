package fr.paladium.palaforgeutils.lib.container.impl;

import fr.paladium.palaforgeutils.lib.container.IForgeInventory;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityForgeInventory extends TileEntity implements IForgeInventory {
  private final String inventoryName;
  
  private final List<Slot> slots;
  
  private ItemStack[] content;
  
  public TileEntityForgeInventory(String inventoryName, int contentSize) {
    this.inventoryName = inventoryName;
    this.slots = new LinkedList<>();
    this.content = new ItemStack[contentSize];
  }
  
  public void addSlot(Slot slot) {
    this.slots.add(slot);
    if (this.slots.size() > func_70302_i_())
      throw new IndexOutOfBoundsException("Number of slots is upper than inventorySize " + this.slots.size() + " > " + func_70302_i_()); 
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < func_70302_i_(); i++) {
      if (func_70301_a(i) != null) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74774_a("Slot", (byte)i);
        try {
          func_70301_a(i).func_77955_b(nbttagcompound);
        } catch (NullPointerException e) {
          e.printStackTrace();
        } 
        nbttaglist.func_74742_a((NBTBase)nbttagcompound);
      } 
    } 
    compound.func_74782_a("Items", (NBTBase)nbttaglist);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    NBTTagList nbttaglist = compound.func_150295_c("Items", 10);
    this.content = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
      int j = nbttagcompound.func_74771_c("Slot") & 0xFF;
      if (j >= 0 && j < func_70302_i_())
        this.content[j] = ItemStack.func_77949_a(nbttagcompound); 
    } 
  }
  
  public ItemStack func_70301_a(int slot) {
    return (slot >= 0 && slot < this.content.length) ? this.content[slot] : null;
  }
  
  public ItemStack func_70298_a(int slot, int stackSize) {
    if (this.content[slot] != null) {
      if ((this.content[slot]).field_77994_a <= stackSize) {
        ItemStack itemStack = this.content[slot];
        this.content[slot] = null;
        func_70296_d();
        return itemStack;
      } 
      ItemStack itemstack = this.content[slot].func_77979_a(stackSize);
      if ((this.content[slot]).field_77994_a == 0)
        this.content[slot] = null; 
      func_70296_d();
      return itemstack;
    } 
    return null;
  }
  
  public ItemStack func_70304_b(int slot) {
    if (this.content[slot] != null) {
      ItemStack itemstack = this.content[slot];
      this.content[slot] = null;
      return itemstack;
    } 
    return null;
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    this.content[slot] = stack;
    if (stack != null && stack.field_77994_a > func_70297_j_())
      stack.field_77994_a = func_70297_j_(); 
    func_70296_d();
  }
  
  public int func_70302_i_() {
    return this.content.length;
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
  
  public boolean shouldDropOnClose() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\container\impl\TileEntityForgeInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */