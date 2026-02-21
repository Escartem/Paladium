package fr.paladium.palaautomation.common.container;

import fr.paladium.palaautomation.common.tile.impl.TileEntityGiver;
import fr.paladium.palaautomation.common.util.PipeUtils;
import fr.paladium.palaforgeutils.lib.container.ForgeContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ContainerGiver extends ForgeContainer {
  private final TileEntityGiver tile;
  
  public TileEntityGiver getTile() {
    return this.tile;
  }
  
  public ContainerGiver(final TileEntityGiver tile, EntityPlayer player) {
    this.tile = tile;
    func_75146_a(new Slot((IInventory)tile, 0, 158, 250) {
          public boolean func_75214_a(ItemStack stack) {
            return tile.func_94041_b(0, stack);
          }
          
          public ResourceLocation getBackgroundIconTexture() {
            return super.getBackgroundIconTexture();
          }
        });
    PipeUtils.bindPlayerInventory(this, player);
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tile.func_70300_a(player);
  }
  
  public int getPlayerInventoryPosition() {
    return 10;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\container\ContainerGiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */