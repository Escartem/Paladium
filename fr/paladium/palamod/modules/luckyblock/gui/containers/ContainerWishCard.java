package fr.paladium.palamod.modules.luckyblock.gui.containers;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWishCard extends Container {
  public ContainerWishCard(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory());
  }
  
  public ContainerWishCard(InventoryPlayer inventoryPlayer) {
    InventoryWishCard inventory = new InventoryWishCard();
    func_75146_a(new Slot(inventory, 0, 157, 140));
    int i;
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventoryPlayer, i, 150 + i * 18, 218)); 
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 150 + j * 18, 55 + (i + 6) * 18)); 
    } 
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slot) {
    return null;
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return player.field_71071_by.func_146028_b(ItemsRegister.WISH_CARD);
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    if (((Slot)this.field_75151_b.get(0)).func_75211_c() != null && 
      
      !player.field_71071_by.func_70441_a(((Slot)this.field_75151_b.get(0)).func_75211_c()))
      player.func_71019_a(((Slot)this.field_75151_b.get(0)).func_75211_c(), false); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\containers\ContainerWishCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */