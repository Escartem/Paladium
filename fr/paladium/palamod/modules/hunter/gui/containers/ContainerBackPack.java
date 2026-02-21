package fr.paladium.palamod.modules.hunter.gui.containers;

import fr.paladium.palamod.modules.hunter.gui.containers.slot.SlotBackPack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBackPack extends Container {
  public InventoryBackPack invBackpack;
  
  public int rows;
  
  public ContainerBackPack(InventoryPlayer playerInv, InventoryBackPack inv) {
    init(playerInv, inv);
  }
  
  private void init(InventoryPlayer playerInv, InventoryBackPack inv) {
    this.invBackpack = inv;
    this.rows = inv.func_70302_i_() / 9;
    int i = (this.rows - 4) * 18;
    int j;
    for (j = 0; j < this.rows; j++) {
      for (int k = 0; k < 9; k++)
        func_75146_a((Slot)new SlotBackPack(inv, k + j * 9, 8 + k * 18, 18 + j * 18)); 
    } 
    for (j = 0; j < 3; j++) {
      for (int k = 0; k < 9; k++)
        func_75146_a(new Slot((IInventory)playerInv, k + j * 9 + 9, 8 + k * 18, ((this.rows > 6) ? 104 : 103) + j * 18 + i)); 
    } 
    for (j = 0; j < 9; j++)
      func_75146_a(new Slot((IInventory)playerInv, j, 8 + j * 18, ((this.rows > 6) ? 162 : 161) + i)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return true;
  }
  
  public void write(EntityPlayer player) {
    this.invBackpack.write(player);
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int index) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(index);
    if (slot != null && slot.func_75216_d()) {
      ItemStack itemstack1 = slot.func_75211_c();
      itemstack = itemstack1.func_77946_l();
      if (itemstack.func_77973_b() instanceof fr.paladium.palamod.modules.hunter.items.ItemHunterBackpack || itemstack.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemDrawers || itemstack.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.pack.ItemDrawersPack || itemstack.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemCustomDrawers || itemstack.func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemSuitcase || itemstack.func_77973_b().func_77658_a().toLowerCase().contains("drawer") || itemstack.func_77973_b().func_77658_a().toLowerCase().contains("safe_chest"))
        return null; 
      if (index < this.invBackpack.func_70302_i_()) {
        if (!func_75135_a(itemstack1, this.invBackpack.func_70302_i_(), this.field_75151_b.size(), true))
          return null; 
      } else if (!func_75135_a(itemstack1, 0, this.invBackpack.func_70302_i_(), false)) {
        return null;
      } 
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d((ItemStack)null);
      } else {
        slot.func_75218_e();
      } 
    } 
    return itemstack;
  }
  
  public ItemStack func_75144_a(int slotIndex, int buttonPressed, int flag, EntityPlayer player) {
    if ((flag == 2 && buttonPressed == player.field_71071_by.field_70461_c) || slotIndex - this.invBackpack.func_70302_i_() - 27 == player.field_71071_by.field_70461_c)
      return null; 
    ItemStack result = super.func_75144_a(slotIndex, buttonPressed, flag, player);
    write(player);
    return result;
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    write(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\gui\containers\ContainerBackPack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */