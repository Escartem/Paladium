package fr.paladium.palamod.modules.luckyblock.blocks.containers;

import fr.paladium.palamod.modules.luckyblock.blocks.containers.slots.SlotClipboard;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityClipboard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerClipboard extends Container {
  private TileEntityClipboard tileEntity;
  
  private SlotClipboard slotClippy;
  
  public ContainerClipboard(InventoryPlayer inventoryPlayer, TileEntityClipboard tile) {
    this.tileEntity = tile;
    func_75146_a((Slot)(this.slotClippy = new SlotClipboard(this, (IInventory)this.tileEntity, 0, 20, 20)));
    bindPlayerInventory(inventoryPlayer);
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tileEntity.func_70300_a(player);
  }
  
  protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 142)); 
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slot) {
    ItemStack stack = null;
    Slot slotObject = this.field_75151_b.get(slot);
    if (slotObject != null && slotObject.func_75216_d()) {
      ItemStack stackInSlot = slotObject.func_75211_c();
      stack = stackInSlot.func_77946_l();
      if (slot < 1) {
        if (!func_75135_a(stackInSlot, 1, 37, true))
          return null; 
      } else if (!func_75135_a(stackInSlot, 0, 1, false)) {
        return null;
      } 
      if (stackInSlot.field_77994_a == 0) {
        slotObject.func_75215_d(null);
      } else {
        slotObject.func_75218_e();
      } 
      if (stackInSlot.field_77994_a == stack.field_77994_a)
        return null; 
      slotObject.func_82870_a(player, stackInSlot);
    } 
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\containers\ContainerClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */