package fr.paladium.palamod.modules.miner.container;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityAutoCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAutoCrafter extends PaladiumContainer {
  private TileEntityAutoCrafter tileEntity;
  
  public ContainerAutoCrafter(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory(), (TileEntityAutoCrafter)data.getTileEntity(TileEntityAutoCrafter.class));
  }
  
  public ContainerAutoCrafter(InventoryPlayer inventoryPlayer, TileEntityAutoCrafter tileEntity) {
    this.tileEntity = tileEntity;
    int i;
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)tileEntity, i, 287 + i % 3 * 24, 76 + (int)Math.floor((i / 3)) * 24)); 
    for (i = 0; i < 9; i++) {
      func_75146_a(new Slot((IInventory)tileEntity, i + 9, 287 + i % 3 * 24, 175 + (int)Math.floor((i / 3)) * 24) {
            public boolean func_75214_a(ItemStack p_75214_1_) {
              return false;
            }
          });
    } 
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 90 + j * 19, 162 + i * 19)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventoryPlayer, i, 90 + i * 19, 224)); 
  }
  
  public boolean func_75145_c(EntityPlayer p_75145_1_) {
    return this.tileEntity.func_70300_a(p_75145_1_);
  }
  
  public int getPlayerInventoryPosition() {
    return 18;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\container\ContainerAutoCrafter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */