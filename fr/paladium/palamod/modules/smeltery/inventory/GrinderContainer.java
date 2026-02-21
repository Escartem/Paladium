package fr.paladium.palamod.modules.smeltery.inventory;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.inventory.slot.SlotResult;
import fr.paladium.palamod.modules.smeltery.blocks.BlockGrinder;
import fr.paladium.palamod.modules.smeltery.inventory.slot.SlotGrinder;
import fr.paladium.palamod.modules.smeltery.inventory.slot.SlotUpgradeGrinder;
import fr.paladium.palamod.modules.smeltery.logics.GrinderLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class GrinderContainer extends PaladiumContainer {
  private final GrinderLogic tile;
  
  public GrinderContainer(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory(), (GrinderLogic)data.getTileEntity(GrinderLogic.class));
  }
  
  public GrinderContainer(InventoryPlayer inventory, GrinderLogic tile) {
    this.tile = tile;
    func_75146_a((Slot)new SlotResult((IInventory)tile, 0, 77, 29));
    func_75146_a(new Slot((IInventory)tile, 1, 23, 20));
    func_75146_a(new Slot((IInventory)tile, 2, 23, 38));
    func_75146_a(new Slot((IInventory)tile, 3, 23, 69));
    func_75146_a((Slot)new SlotUpgradeGrinder((IInventory)tile, 4, 77, 69));
    func_75146_a((Slot)new SlotGrinder((IInventory)tile, 5, 125, 12, tile));
    bindPlayerInventory(inventory);
  }
  
  private void bindPlayerInventory(InventoryPlayer inventory) {
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventory, j + i * 9 + 9, 23 + j * 18, 91 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventory, i, 23 + i * 18, 149)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    if (!this.tile.func_70300_a(player))
      return false; 
    if (player.field_70170_p.func_72820_D() % 20L == 0L)
      return ((BlockGrinder)player.field_70170_p.func_147439_a(this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e))
        .isMultiBlockStructure(player.field_70170_p, this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e); 
    return true;
  }
  
  public int getPlayerInventoryPosition() {
    return 6;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\inventory\GrinderContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */