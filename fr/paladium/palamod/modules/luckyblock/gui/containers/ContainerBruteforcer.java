package fr.paladium.palamod.modules.luckyblock.gui.containers;

import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBruteforcer extends Container {
  private final int numRows = 6;
  
  private final TileEntityBruteforcer tile;
  
  public ContainerBruteforcer(SimpleGHandler.GuiHandlerData data) {
    this((TileEntityBruteforcer)data.getTileEntity(TileEntityBruteforcer.class), data.getInventory());
  }
  
  public ContainerBruteforcer(TileEntityBruteforcer tile, InventoryPlayer inventory) {
    this.tile = tile;
    func_75146_a(new BruteforcerSlot(tile, 0, 128, 95));
    int i;
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventory, i, (int)(127.0D + i * 19.25D), 199)); 
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventory, i * 9 + j + 9, (int)(127.0D + j * 19.36D), (int)(136.0D + i * 19.0D + i))); 
    } 
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slot) {
    ItemStack var3 = null;
    Slot var4 = this.field_75151_b.get(slot);
    if (var4 != null && var4.func_75216_d()) {
      ItemStack var5 = var4.func_75211_c();
      var3 = var5.func_77946_l();
      getClass();
      if (slot < 6 * 9) {
        getClass();
        if (!func_75135_a(var5, 6 * 9, this.field_75151_b.size(), true))
          return null; 
      } else {
        getClass();
        if (!func_75135_a(var5, 0, 6 * 9, false))
          return null; 
      } 
      if (var5.field_77994_a == 0) {
        var4.func_75215_d((ItemStack)null);
      } else {
        var4.func_75218_e();
      } 
    } 
    return var3;
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    this.tile.func_70305_f();
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tile.func_70300_a(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\containers\ContainerBruteforcer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */