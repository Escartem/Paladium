package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.inventory.ChestExplorerInventory;
import fr.paladium.palamod.modules.paladium.common.inventory.slot.SlotChestExplorer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ChestExplorerContainer extends Container {
  public final ChestExplorerInventory inventory;
  
  private final IInventory tile_inventory;
  
  private final InventoryPlayer playerInv;
  
  public ChestExplorerContainer(SimpleGHandler.GuiHandlerData data) {
    this(data.getTileEntity(TileEntity.class), new ChestExplorerInventory(), data.getInventory());
  }
  
  public ChestExplorerContainer(TileEntity tile, ChestExplorerInventory inventory, InventoryPlayer playerInv) {
    this.tile_inventory = (IInventory)tile;
    this.inventory = inventory;
    this.playerInv = playerInv;
    int i = 0;
    int j = 0;
    for (i = 0; i < 108; i++) {
      if (i % 12 == 0)
        j++; 
      int u = i % 12 + 1;
      if (i < this.tile_inventory.func_70302_i_()) {
        func_75146_a((Slot)new SlotChestExplorer(this.tile_inventory, i, u * 18 - 6, j * 18 - 10));
      } else {
        func_75146_a((Slot)new SlotChestExplorer((IInventory)this.inventory, 0, u * 18 - 6, j * 18 - 10));
      } 
    } 
    bindPlayerInventory();
  }
  
  private void bindPlayerInventory() {
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)this.playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)this.playerInv, i, 8 + i * 18, 142)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.inventory.func_70300_a(player);
  }
  
  public ItemStack func_75144_a(int slotIndex, int buttonPressed, int flag, EntityPlayer player) {
    return null;
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int quantity) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\ChestExplorerContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */