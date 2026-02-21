package fr.paladium.palaforgeutils.lib.command.impl.palagive.manager;

import net.minecraft.item.ItemStack;

class GiveStatus {
  private final ItemStack itemStack;
  
  private final int givenCount;
  
  private String inventoryBefore;
  
  private String inventoryAfter;
  
  public void setInventoryBefore(String inventoryBefore) {
    this.inventoryBefore = inventoryBefore;
  }
  
  public void setInventoryAfter(String inventoryAfter) {
    this.inventoryAfter = inventoryAfter;
  }
  
  public String toString() {
    return "PalaGiveManager.GiveStatus(itemStack=" + getItemStack() + ", givenCount=" + getGivenCount() + ", inventoryBefore=" + getInventoryBefore() + ", inventoryAfter=" + getInventoryAfter() + ")";
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public int getGivenCount() {
    return this.givenCount;
  }
  
  public String getInventoryBefore() {
    return this.inventoryBefore;
  }
  
  public String getInventoryAfter() {
    return this.inventoryAfter;
  }
  
  public GiveStatus(ItemStack stack, int givenCount) {
    this.itemStack = stack;
    this.givenCount = givenCount;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\impl\palagive\manager\PalaGiveManager$GiveStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */