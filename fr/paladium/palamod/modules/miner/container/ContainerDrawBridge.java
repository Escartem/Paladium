package fr.paladium.palamod.modules.miner.container;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityDrawBridge;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerDrawBridge extends PaladiumContainer {
  private TileEntityDrawBridge tileEntity;
  
  public ContainerDrawBridge(SimpleGHandler.GuiHandlerData data) {
    this(data.getPlayer(), (TileEntityDrawBridge)data.getTileEntity(TileEntityDrawBridge.class));
  }
  
  public ContainerDrawBridge(EntityPlayer player, TileEntityDrawBridge tileEntity) {
    this.tileEntity = tileEntity;
    int i;
    for (i = 0; i < 16; i++) {
      func_75146_a(new Slot((IInventory)tileEntity, i, 35 + i % 8 * 19, 50 + (int)Math.floor((i / 8)) * 22) {
            public boolean func_75214_a(ItemStack stack) {
              return !ContainerDrawBridge.this.isBlacklist(stack);
            }
          });
    } 
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)player.field_71071_by, j + i * 9 + 9, 26 + j * 19, 99 + i * 19)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)player.field_71071_by, i, 26 + i * 19, 160)); 
  }
  
  public boolean func_75145_c(EntityPlayer p_75145_1_) {
    return this.tileEntity.func_70300_a(p_75145_1_);
  }
  
  private boolean isBlacklist(ItemStack item) {
    return isBlacklist(item.func_77973_b());
  }
  
  private boolean isBlacklist(Item item) {
    Block b = Block.func_149634_a(item);
    return (b == null || b == Blocks.field_150350_a || b.hasTileEntity(0));
  }
  
  public int getPlayerInventoryPosition() {
    return 16;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\container\ContainerDrawBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */