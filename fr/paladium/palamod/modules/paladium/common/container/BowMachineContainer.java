package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.inventory.slot.SlotSingle;
import fr.paladium.palamod.modules.paladium.common.logics.BowMachineLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;

public class BowMachineContainer extends PaladiumContainer {
  public BowMachineLogic tile;
  
  public BowMachineContainer(SimpleGHandler.GuiHandlerData data) {
    this((BowMachineLogic)data.getTileEntity(BowMachineLogic.class), data.getInventory());
  }
  
  public BowMachineContainer(BowMachineLogic tile, InventoryPlayer inventory) {
    this.tile = tile;
    func_75146_a(new Slot((IInventory)tile, 0, 81, 35));
    func_75146_a((Slot)new SlotSingle((IInventory)tile, 1, 150, 35, (Item)ItemsRegister.PALADIUM_BOW));
    bindPlayerInventory(inventory);
  }
  
  private void bindPlayerInventory(InventoryPlayer inventory) {
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventory, i, 8 + i * 18, 142)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tile.func_70300_a(player);
  }
  
  public int getPlayerInventoryPosition() {
    return 2;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\BowMachineContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */