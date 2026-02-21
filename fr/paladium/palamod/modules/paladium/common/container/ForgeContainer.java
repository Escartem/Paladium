package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.logics.ForgeLogic;
import fr.paladium.palamod.modules.paladium.utils.EnumAllowItemsForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ForgeContainer extends PaladiumContainer {
  private ForgeLogic tile;
  
  public ForgeContainer(SimpleGHandler.GuiHandlerData data) {
    this((ForgeLogic)data.getTileEntity(ForgeLogic.class), data.getPlayer());
  }
  
  public ForgeContainer(ForgeLogic tile, EntityPlayer player) {
    this.tile = tile;
    func_75146_a(new Slot((IInventory)tile, 0, 58, 15) {
          public boolean func_75214_a(ItemStack p_75214_1_) {
            if (EnumAllowItemsForge.containItem(p_75214_1_.func_77973_b()).booleanValue())
              return true; 
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 1, 58, 58) {
          public boolean func_75214_a(ItemStack p_75214_1_) {
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 2, 115, 45) {
          public boolean func_75214_a(ItemStack p_75214_1_) {
            if (TileEntityFurnace.func_145954_b(p_75214_1_))
              return true; 
            return false;
          }
        });
    int l1 = 7;
    for (int l = 9; l <= 17; l++) {
      func_75146_a(new Slot((IInventory)player.field_71071_by, l, l1, 93));
      l1 += 20;
    } 
    int a1 = 7;
    for (int a = 18; a <= 26; a++) {
      func_75146_a(new Slot((IInventory)player.field_71071_by, a, a1, 115));
      a1 += 20;
    } 
    int b1 = 7;
    for (int b = 27; b <= 35; b++) {
      func_75146_a(new Slot((IInventory)player.field_71071_by, b, b1, 137));
      b1 += 20;
    } 
    int c1 = 7;
    for (int c = 0; c <= 8; c++) {
      func_75146_a(new Slot((IInventory)player.field_71071_by, c, c1, 160));
      c1 += 20;
    } 
  }
  
  public boolean func_75145_c(EntityPlayer p_75145_1_) {
    return this.tile.func_70300_a(p_75145_1_);
  }
  
  public int getPlayerInventoryPosition() {
    return 3;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\ForgeContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */