package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.inventory.slot.SlotSize;
import fr.paladium.palamod.modules.paladium.common.logics.AlchemyCreatorLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class AlchemyCreatorPotionContainer extends PaladiumContainer {
  private final InventoryPlayer inventory;
  
  private final AlchemyCreatorLogic tile;
  
  public AlchemyCreatorPotionContainer(SimpleGHandler.GuiHandlerData data) {
    this((AlchemyCreatorLogic)data.getTileEntity(AlchemyCreatorLogic.class), data.getPlayer());
  }
  
  public AlchemyCreatorPotionContainer(AlchemyCreatorLogic tile, EntityPlayer player) {
    this.inventory = player.field_71071_by;
    this.tile = tile;
    func_75146_a(new Slot((IInventory)tile, 0, 50, 35));
    func_75146_a(new Slot((IInventory)tile, 1, 79, 17));
    func_75146_a(new Slot((IInventory)tile, 2, 108, 35));
    func_75146_a((Slot)new SlotSize((IInventory)tile, 3, 79, 53, 1));
    bindPlayerInventory();
  }
  
  private void bindPlayerInventory() {
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)this.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)this.inventory, i, 8 + i * 18, 142)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    AlchemyCreatorLogic.oppenedGui.put(UUIDUtils.toString((Entity)player), this.tile);
    return this.tile.func_70300_a(player);
  }
  
  public int getPlayerInventoryPosition() {
    return 4;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\AlchemyCreatorPotionContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */