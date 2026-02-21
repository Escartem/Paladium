package fr.paladium.palamod.modules.luckyblock.gui.containers;

import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityMegaSafeChest;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMegaSafeChest extends Container {
  private int numRows = 6;
  
  private TileEntityMegaSafeChest tile;
  
  public ContainerMegaSafeChest(TileEntityMegaSafeChest tile, InventoryPlayer inventory) {
    this.tile = tile;
    int i;
    for (i = 0; i < 6; i++) {
      for (int j = 0; j < 9; j++) {
        func_75146_a(new Slot((IInventory)tile, j + i * 9, 8 + j * 18, 18 + i * 18) {
              public boolean func_75214_a(@Nullable ItemStack stack) {
                if (Block.func_149634_a(stack.func_77973_b()) instanceof com.jaquadro.minecraft.storagedrawers.block.BlockDrawers || 
                  Block.func_149634_a(stack.func_77973_b()) instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest || 
                  Block.func_149634_a(stack.func_77973_b()) instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockUltraSafeChest || 
                  Block.func_149634_a(stack.func_77973_b()) instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockMegaSafeChest)
                  return false; 
                return true;
              }
            });
      } 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventory, i, 8 + i * 18, 198)); 
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventory, j + i * 9 + 9, 8 + j * 18, 32 + (i + 6) * 18)); 
    } 
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slot) {
    ItemStack var3 = null;
    Slot var4 = this.field_75151_b.get(slot);
    if (var4 != null && var4.func_75216_d()) {
      ItemStack var5 = var4.func_75211_c();
      var3 = var5.func_77946_l();
      if (Block.func_149634_a(var3.func_77973_b()) instanceof com.jaquadro.minecraft.storagedrawers.block.BlockDrawers || 
        Block.func_149634_a(var3.func_77973_b()) instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest || 
        Block.func_149634_a(var3.func_77973_b()) instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockUltraSafeChest || 
        Block.func_149634_a(var3.func_77973_b()) instanceof fr.paladium.palamod.modules.luckyblock.blocks.BlockMegaSafeChest)
        return null; 
      if (slot < this.numRows * 9) {
        if (!func_75135_a(var5, this.numRows * 9, this.field_75151_b.size(), true))
          return null; 
      } else if (!func_75135_a(var5, 0, this.numRows * 9, false)) {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\containers\ContainerMegaSafeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */