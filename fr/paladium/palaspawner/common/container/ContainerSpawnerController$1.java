package fr.paladium.palaspawner.common.container;

import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public boolean func_75214_a(ItemStack stack) {
    return tile.isUpgradeValidForSlot(stack, slotId);
  }
  
  public ResourceLocation getBackgroundIconTexture() {
    return super.getBackgroundIconTexture();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\container\ContainerSpawnerController$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */