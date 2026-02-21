package fr.paladium.palamod.modules.hunter.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class AdminInventoryBackpack extends InventoryBackPack {
  public AdminInventoryBackpack(int size, EntityPlayer owner) {
    this(size, owner, "backpack");
  }
  
  public AdminInventoryBackpack(int size, EntityPlayer owner, String inventoryName) {
    super(size, owner, inventoryName);
  }
  
  public AdminInventoryBackpack(int size, EntityPlayer owner, ItemStack[] content) {
    super(size, owner, "backpack.endium");
    this.content = content;
  }
  
  public void write(EntityPlayer player) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\gui\containers\AdminInventoryBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */