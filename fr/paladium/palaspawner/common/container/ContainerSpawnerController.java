package fr.paladium.palaspawner.common.container;

import fr.paladium.palaforgeutils.lib.container.ForgeContainer;
import fr.paladium.palaspawner.common.registry.SpawnerUpgradeRegistry;
import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ContainerSpawnerController extends ForgeContainer {
  private final TileEntitySpawnerController tile;
  
  public TileEntitySpawnerController getTile() {
    return this.tile;
  }
  
  public ContainerSpawnerController(final TileEntitySpawnerController tile, EntityPlayer player) {
    this.tile = tile;
    for (int i = 0; i < 4; i++) {
      final int slotId = i;
      func_75146_a(new Slot((IInventory)tile, slotId, 158, 235 + 15 * slotId) {
            public boolean func_75214_a(ItemStack stack) {
              return tile.isUpgradeValidForSlot(stack, slotId);
            }
            
            public ResourceLocation getBackgroundIconTexture() {
              return super.getBackgroundIconTexture();
            }
          });
    } 
    bindPlayerInventory(player.field_71071_by);
  }
  
  public boolean isSlotLocked(int slotId) {
    if (slotId < 2)
      return false; 
    boolean hasMoreUpgrade = (this.tile.getUpgradeCount(SpawnerUpgradeRegistry.MORE) > 0);
    return !hasMoreUpgrade;
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


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\container\ContainerSpawnerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */