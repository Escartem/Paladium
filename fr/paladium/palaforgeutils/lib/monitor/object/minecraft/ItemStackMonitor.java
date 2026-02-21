package fr.paladium.palaforgeutils.lib.monitor.object.minecraft;

import fr.paladium.palaforgeutils.lib.monitor.Monitor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemStackMonitor extends Monitor<ItemStack> {
  public ItemStackMonitor(ItemStack itemStack) {
    super(itemStack);
  }
  
  public void setStackSize(int size) {
    ItemStack newItemStack = ((ItemStack)get()).func_77946_l();
    newItemStack.field_77994_a = size;
    set(newItemStack);
  }
  
  public void setItemDamage(int damage) {
    ItemStack newItemStack = ((ItemStack)get()).func_77946_l();
    newItemStack.func_77964_b(damage);
    set(newItemStack);
  }
  
  public void setTagCompound(NBTTagCompound compound) {
    ItemStack newItemStack = ((ItemStack)get()).func_77946_l();
    newItemStack.func_77982_d(compound);
    set(newItemStack);
  }
  
  public void setStackDisplayName(String displayName) {
    ItemStack newItemStack = ((ItemStack)get()).func_77946_l();
    newItemStack.func_151001_c(displayName);
    set(newItemStack);
  }
  
  public void setRepairCost(int cost) {
    ItemStack newItemStack = ((ItemStack)get()).func_77946_l();
    newItemStack.func_82841_c(cost);
    set(newItemStack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\object\minecraft\ItemStackMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */