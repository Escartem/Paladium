package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.palaforgeutils.lib.container.ForgeContainer;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockFlowerFarm;
import fr.paladium.palamod.modules.paladium.common.logics.TileFlowerFarm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class FlowerFarmContainer extends ForgeContainer {
  private final TileFlowerFarm tile;
  
  public TileFlowerFarm getTile() {
    return this.tile;
  }
  
  public FlowerFarmContainer(SimpleGHandler.GuiHandlerData data) {
    this((TileFlowerFarm)data.getTileEntity(TileFlowerFarm.class), data.getPlayer());
  }
  
  public FlowerFarmContainer(TileFlowerFarm tile, EntityPlayer player) {
    this.tile = tile;
    func_75146_a(new Slot((IInventory)tile, 0, 158, 384) {
          public boolean func_75214_a(ItemStack is) {
            return (is != null && is.func_77969_a(BlockFlowerFarm.FLOWER_FARM_FUEL));
          }
        });
    for (int i = 0; i < 9; i++) {
      int slotId = i + 1;
      func_75146_a(new Slot((IInventory)tile, slotId, 158, 235 + 15 * slotId) {
            public boolean func_75214_a(ItemStack is) {
              return false;
            }
            
            public ResourceLocation getBackgroundIconTexture() {
              return super.getBackgroundIconTexture();
            }
          });
    } 
    bindPlayerInventory(player.field_71071_by);
  }
  
  private void bindPlayerInventory(InventoryPlayer inventory) {
    int delta = 40;
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventory, j + i * 9 + 9, 104 + j * 67 - 40, 483 + i * 67 - 40)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventory, i, 104 + i * 67 - 40, 658)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tile.func_70300_a(player);
  }
  
  public int getPlayerInventoryPosition() {
    return 10;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\FlowerFarmContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */