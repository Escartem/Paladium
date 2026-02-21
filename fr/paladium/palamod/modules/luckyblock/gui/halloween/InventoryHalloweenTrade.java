package fr.paladium.palamod.modules.luckyblock.gui.halloween;

import fr.paladium.palamod.modules.luckyblock.utils.HalloweenTradeConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryHalloweenTrade implements IInventory {
  private ItemStack[] content;
  
  private Container eventHandler;
  
  public HalloweenTradeConfig trade;
  
  public int tradeAmount;
  
  public ItemStack input;
  
  public ItemStack output;
  
  public InventoryHalloweenTrade(Container container, HalloweenTradeConfig trade, int tradeAmount, ItemStack input, ItemStack output) {
    this.content = new ItemStack[2];
    this.eventHandler = container;
    this.trade = trade;
    this.tradeAmount = tradeAmount;
    this.input = input;
    this.output = output;
  }
  
  public boolean isLocked() {
    return (this.trade == null) ? false : ((this.tradeAmount >= this.trade.getMaxTrade()));
  }
  
  public ItemStack[] getContents() {
    return this.content;
  }
  
  public int func_70302_i_() {
    return this.content.length;
  }
  
  public ItemStack func_70301_a(int slot) {
    return (slot >= func_70302_i_()) ? null : this.content[slot];
  }
  
  public String func_145825_b() {
    return "container.halloween.trade";
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public ItemStack func_70304_b(int slot) {
    if (this.content[slot] != null) {
      ItemStack itemstack = this.content[slot];
      this.content[slot] = null;
      return itemstack;
    } 
    return null;
  }
  
  public ItemStack func_70298_a(int slot, int stackSize) {
    if (this.content[slot] != null) {
      if ((this.content[slot]).field_77994_a <= stackSize) {
        ItemStack itemStack = this.content[slot];
        this.content[slot] = null;
        if (slot == 0)
          this.eventHandler.func_75130_a(this); 
        return itemStack;
      } 
      ItemStack itemstack = this.content[slot].func_77979_a(stackSize);
      if ((this.content[slot]).field_77994_a == 0)
        this.content[slot] = null; 
      if (slot == 0)
        this.eventHandler.func_75130_a(this); 
      return itemstack;
    } 
    return null;
  }
  
  public void func_70299_a(int slot, ItemStack item) {
    this.content[slot] = item;
    if (slot == 0)
      this.eventHandler.func_75130_a(this); 
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return this.eventHandler.func_75145_c(player);
  }
  
  public boolean func_94041_b(int slot, ItemStack item) {
    return false;
  }
  
  public void func_70296_d() {}
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\halloween\InventoryHalloweenTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */