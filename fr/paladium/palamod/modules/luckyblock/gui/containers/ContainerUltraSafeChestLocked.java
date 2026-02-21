package fr.paladium.palamod.modules.luckyblock.gui.containers;

import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityUltraSafeChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class ContainerUltraSafeChestLocked extends Container {
  private TileEntityUltraSafeChest tile;
  
  public ContainerUltraSafeChestLocked(TileEntityUltraSafeChest tile, InventoryPlayer inventory) {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\containers\ContainerUltraSafeChestLocked.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */