package fr.paladium.palamod.modules.chisel.inventory;

import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ChiselContainer extends Container {
  private final int chiselSlot;
  
  public final ChiselInventory inventory;
  
  public InventoryPlayer playerInventory;
  
  public ItemStack chisel;
  
  public boolean finished;
  
  public ChiselContainer(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory(), new ChiselInventory(null));
  }
  
  public ChiselContainer(InventoryPlayer inventoryplayer, ChiselInventory inventory) {
    this.inventory = inventory;
    this.playerInventory = inventoryplayer;
    this.chiselSlot = this.playerInventory.field_70461_c;
    inventory.container = this;
    int top = 8, left = 62;
    int i;
    for (i = 0; i < 60; i++)
      func_75146_a(new SlotChisel(this, this.inventory, this.inventory, i, left + i % 10 * 18, top + i / 10 * 18)); 
    func_75146_a(new SlotChiselInput(this, this.inventory, 60, 24, 24));
    top += 112;
    left += 9;
    for (i = 0; i < 27; i++)
      func_75146_a(new Slot((IInventory)inventoryplayer, i + 9, left + i % 9 * 18, top + i / 9 * 18)); 
    top += 58;
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventoryplayer, i, left + i % 9 * 18, top + i / 9 * 18)); 
    this.chisel = inventoryplayer.func_70448_g();
    this.inventory.updateItems();
  }
  
  public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
    int clickedSlot = par1 - this.inventory.func_70302_i_() - 27;
    if (clickedSlot == this.chiselSlot || (par3 == 2 && par2 == this.chiselSlot))
      return null; 
    return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
  }
  
  public void func_75134_a(EntityPlayer entityplayer) {
    ItemStack itemstack = this.inventory.func_70304_b(60);
    if (itemstack != null)
      entityplayer.func_71019_a(itemstack, false); 
    this.inventory.clearItems();
    super.func_75134_a(entityplayer);
  }
  
  public boolean func_75145_c(EntityPlayer entityplayer) {
    return this.inventory.func_70300_a(entityplayer);
  }
  
  public ItemStack func_82846_b(EntityPlayer entity, int slotIdx) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(slotIdx);
    if (slot != null && slot.func_75216_d()) {
      ItemStack itemstack1 = slot.func_75211_c();
      itemstack = itemstack1.func_77946_l();
      if (slotIdx > 60) {
        if (!func_75135_a(itemstack1, 60, 61, false))
          return null; 
      } else {
        if (slotIdx < 61 && itemstack1 != null) {
          entity.field_71071_by.func_70437_b(itemstack1.func_77946_l());
          slot.func_82870_a(entity, itemstack1);
          itemstack1 = entity.field_71071_by.func_70445_o();
          entity.field_71071_by.func_70437_b(null);
        } 
        if (!func_75135_a(itemstack1, 61, 97, false))
          return null; 
      } 
      slot.func_75220_a(itemstack1, itemstack);
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d((ItemStack)null);
      } else {
        slot.func_75215_d(itemstack1);
        slot.func_75218_e();
      } 
      if (itemstack1.field_77994_a == itemstack.field_77994_a)
        return null; 
      if (slotIdx >= 60)
        slot.func_82870_a(entity, itemstack1); 
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d(null);
        return null;
      } 
    } 
    return itemstack;
  }
  
  public void onChiselSlotChanged() {
    ItemStack stack = this.playerInventory.field_70462_a[this.chiselSlot];
    if (stack == null || !stack.func_77969_a(this.chisel))
      this.finished = true; 
    if (this.finished)
      return; 
    this.playerInventory.field_70462_a[this.chiselSlot] = this.chisel;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\inventory\ChiselContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */