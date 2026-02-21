package fr.paladium.palaautomation.common.container;

import fr.paladium.palaautomation.common.tile.impl.TileEntityCrafter;
import fr.paladium.palaautomation.common.util.PipeUtils;
import fr.paladium.palaforgeutils.lib.container.ForgeContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ContainerCrafter extends ForgeContainer {
  private final TileEntityCrafter tile;
  
  public TileEntityCrafter getTile() {
    return this.tile;
  }
  
  public ContainerCrafter(TileEntityCrafter tile, EntityPlayer player) {
    this.tile = tile;
    func_75146_a(new Slot((IInventory)tile, 0, 158, 250) {
          public boolean func_75214_a(ItemStack stack) {
            return false;
          }
          
          public ResourceLocation getBackgroundIconTexture() {
            return super.getBackgroundIconTexture();
          }
        });
    PipeUtils.bindPlayerInventory(this, player);
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


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\container\ContainerCrafter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */