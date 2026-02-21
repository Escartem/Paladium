package fr.paladium.palaforgeutils.lib.container;

import java.util.HashSet;
import java.util.LinkedHashSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ForgeContainer extends Container {
  private final HashSet<IForgeInventory> externalInventories = new LinkedHashSet<>();
  
  public Slot func_75146_a(Slot slot) {
    Slot s = super.func_75146_a(slot);
    if (s.field_75224_c instanceof IForgeInventory) {
      IForgeInventory inventory = (IForgeInventory)s.field_75224_c;
      if (!this.externalInventories.contains(inventory))
        inventory.getSlots().clear(); 
      if (!inventory.getSlots().stream().anyMatch(iSlot -> (iSlot.getSlotIndex() == slot.getSlotIndex())))
        inventory.addSlot(s); 
      this.externalInventories.add(inventory);
    } else if (!(s.field_75224_c instanceof net.minecraft.entity.player.InventoryPlayer)) {
      throw new IllegalArgumentException("Unable to add an inventory who not extends IPaladiumInventory (" + s.field_75224_c + ")");
    } 
    return s;
  }
  
  public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
    ItemStack itemStackCopy = null;
    Slot slot = this.field_75151_b.get(index);
    if (!slot.func_75216_d())
      return itemStackCopy; 
    if (slot.field_75224_c == playerIn.field_71071_by) {
      ItemStack itemstack1 = slot.func_75211_c();
      itemStackCopy = itemstack1.func_77946_l();
      boolean merged = false;
      int lastInventorySlot = 0;
      for (IForgeInventory inventory : this.externalInventories) {
        if (merged)
          break; 
        for (int i = 0; i < inventory.func_70302_i_(); i++) {
          if (inventory.func_94041_b(i, itemstack1))
            if (func_75135_a(itemstack1, lastInventorySlot + i, lastInventorySlot + i + 1, false)) {
              merged = true;
              break;
            }  
        } 
        lastInventorySlot += inventory.func_70302_i_();
      } 
      if (!merged)
        return null; 
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d((ItemStack)null);
      } else {
        slot.func_75218_e();
      } 
      return itemStackCopy;
    } 
    for (IForgeInventory inventory : this.externalInventories) {
      if (slot.field_75224_c == inventory) {
        ItemStack slotItemStack = slot.func_75211_c();
        itemStackCopy = slotItemStack.func_77946_l();
        int defaultStackSize = itemStackCopy.field_77994_a;
        if (!func_75135_a(slotItemStack, getPlayerInventoryPosition(), getPlayerInventoryPosition() + playerIn.field_71071_by.func_70302_i_() - 4, false))
          return null; 
        onShiftClick(playerIn, index, slot, defaultStackSize, itemStackCopy, (IInventory)playerIn.field_71071_by);
        if (slotItemStack.field_77994_a == 0) {
          slot.func_75215_d((ItemStack)null);
        } else {
          slot.func_75218_e();
        } 
        return itemStackCopy;
      } 
    } 
    return itemStackCopy;
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    for (IForgeInventory inventory : this.externalInventories) {
      inventory.func_70305_f();
      if (inventory.shouldDropOnClose()) {
        for (int i = 0; i < inventory.func_70302_i_(); i++) {
          ItemStack itemStack = inventory.func_70304_b(i);
          if (itemStack != null)
            player.func_71019_a(itemStack, false); 
        } 
        continue;
      } 
      if (inventory.shouldReturnOnClose())
        for (int i = 0; i < inventory.func_70302_i_(); i++) {
          ItemStack itemStack = inventory.func_70304_b(i);
          if (itemStack != null && 
            !player.field_71071_by.func_70441_a(itemStack))
            player.func_71019_a(itemStack, false); 
        }  
    } 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return true;
  }
  
  public void onShiftClick(EntityPlayer player, int slotIndex, Slot slot, int defaultStackSize, ItemStack itemStack, IInventory target) {}
  
  public abstract int getPlayerInventoryPosition();
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\container\ForgeContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */