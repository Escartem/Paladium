package fr.paladium.palamod.modules.chisel.inventory;

import fr.paladium.palamod.modules.chisel.crafting.ChiselRecipes;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ChiselInventory implements IInventory {
  public static final int normalSlots = 60;
  
  public int activeVariations = 0;
  
  ItemStack chisel = null;
  
  ChiselContainer container;
  
  ItemStack[] inventory;
  
  public ChiselInventory(ItemStack chisel) {
    this.inventory = new ItemStack[61];
    this.chisel = chisel;
  }
  
  public void onInventoryUpdate(int slot) {}
  
  public int func_70302_i_() {
    return 61;
  }
  
  public ItemStack func_70301_a(int var1) {
    return this.inventory[var1];
  }
  
  public void updateInventoryState(int slot) {
    onInventoryUpdate(slot);
  }
  
  public ItemStack func_70298_a(int slot, int amount) {
    if (this.inventory[slot] == null)
      return null; 
    if ((this.inventory[slot]).field_77994_a <= amount) {
      ItemStack itemStack = this.inventory[slot];
      this.inventory[slot] = null;
      updateInventoryState(slot);
      return itemStack;
    } 
    ItemStack stack = this.inventory[slot].func_77979_a(amount);
    if ((this.inventory[slot]).field_77994_a == 0)
      this.inventory[slot] = null; 
    updateInventoryState(slot);
    return stack;
  }
  
  public String func_145825_b() {
    return "container.chisel";
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
  
  public void clearItems() {
    this.activeVariations = 0;
    for (int i = 0; i < 60; i++)
      this.inventory[i] = null; 
  }
  
  public ItemStack getStackInSpecialSlot() {
    return this.inventory[60];
  }
  
  public void updateItems() {
    ItemStack chiseledItem = this.inventory[60];
    clearItems();
    if (chiseledItem == null) {
      this.container.onChiselSlotChanged();
      return;
    } 
    Item item = chiseledItem.func_77973_b();
    if (item == null || Block.func_149634_a(item) == null)
      return; 
    List<ItemStack> list = ChiselRecipes.getInstance().getResults(chiseledItem);
    if (list.isEmpty())
      return; 
    this.activeVariations = 0;
    while (this.activeVariations < 60 && this.activeVariations < list.size()) {
      if (Block.field_149771_c.func_148750_c(Block.func_149634_a(((ItemStack)list.get(this.activeVariations)).func_77973_b())) != null) {
        this.inventory[this.activeVariations] = list.get(this.activeVariations);
        this.activeVariations++;
      } 
    } 
    this.container.onChiselSlotChanged();
  }
  
  public ItemStack func_70304_b(int slot) {
    ItemStack stack = func_70301_a(slot);
    if (stack == null)
      return null; 
    this.inventory[slot] = null;
    updateInventoryState(slot);
    return stack;
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    this.inventory[slot] = stack;
    updateInventoryState(slot);
  }
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
  
  public boolean func_94041_b(int i, ItemStack stack) {
    if (stack.func_77973_b() instanceof net.minecraft.item.ItemTool)
      return false; 
    return ((stack == null || !(stack.func_77973_b() instanceof fr.paladium.palamod.modules.chisel.item.ItemChisel)) && i == 60);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\inventory\ChiselInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */