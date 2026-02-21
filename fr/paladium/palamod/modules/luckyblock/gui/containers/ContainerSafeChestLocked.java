package fr.paladium.palamod.modules.luckyblock.gui.containers;

import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySafeChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class ContainerSafeChestLocked extends Container {
  private final TileEntitySafeChest tile;
  
  public ContainerSafeChestLocked(SimpleGHandler.GuiHandlerData data) {
    this((TileEntitySafeChest)data.getTileEntity(TileEntitySafeChest.class), data.getInventory());
  }
  
  public ContainerSafeChestLocked(TileEntitySafeChest tile, InventoryPlayer inventory) {
    this.tile = tile;
    tile.func_70295_k_();
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slot) {
    return null;
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    this.tile.func_70305_f();
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tile.func_70300_a(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\containers\ContainerSafeChestLocked.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */