package fr.paladium.palamod.modules.luckyblock.gui.containers;

import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityDyeingMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDyeingMachine extends Container {
  private TileEntityDyeingMachine te;
  
  public ContainerDyeingMachine(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory(), (TileEntityDyeingMachine)data.getTileEntity(TileEntityDyeingMachine.class));
  }
  
  public ContainerDyeingMachine(InventoryPlayer inv, TileEntityDyeingMachine tileentity) {
    this.te = tileentity;
    func_75146_a(new Slot((IInventory)tileentity, 0, 27, 22) {
          public void func_82870_a(EntityPlayer player, ItemStack item) {
            ContainerDyeingMachine.this.te.func_70299_a(2, null);
            super.func_82870_a(player, item);
          }
        });
    func_75146_a(new Slot((IInventory)tileentity, 1, 76, 22));
    func_75146_a(new Slot((IInventory)tileentity, 2, 133, 22) {
          public boolean func_75214_a(ItemStack itemstack) {
            return false;
          }
          
          public void func_82870_a(EntityPlayer player, ItemStack item) {
            super.func_82870_a(player, item);
          }
        });
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 59 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inv, i, 8 + i * 18, 117)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.te.func_70300_a(player);
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    this.te.func_70305_f();
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slotID) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(slotID);
    if (slot != null && slot.func_75216_d()) {
      ItemStack itemstack1 = slot.func_75211_c();
      if (slotID == 2) {
        this.te.func_70298_a(0, itemstack1.field_77994_a);
        this.te.func_70298_a(1, itemstack1.field_77994_a);
      } 
      itemstack = itemstack1.func_77946_l();
      if (slotID == 2) {
        if (!func_75135_a(itemstack1, 3, 39, true))
          return null; 
        slot.func_75220_a(itemstack1, itemstack);
      } else if (slotID != 1 && slotID != 0) {
        if (itemstack1.func_77973_b() instanceof net.minecraft.item.Item && itemstack1.func_77973_b() != Items.field_151100_aR) {
          if (!func_75135_a(itemstack1, 0, 1, false))
            return null; 
        } else if (itemstack1.func_77973_b() == Items.field_151100_aR) {
          if (!func_75135_a(itemstack1, 1, 2, false))
            return null; 
        } else if (slotID >= 3 && slotID < 30) {
          if (!func_75135_a(itemstack1, 30, 39, false))
            return null; 
        } else if (slotID >= 30 && slotID < 39 && !func_75135_a(itemstack1, 3, 30, false)) {
          return null;
        } 
      } else if (!func_75135_a(itemstack1, 3, 39, false)) {
        return null;
      } 
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d((ItemStack)null);
      } else {
        slot.func_75218_e();
      } 
      if (itemstack1.field_77994_a == itemstack.field_77994_a)
        return null; 
      slot.func_82870_a(player, itemstack1);
    } 
    return itemstack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\containers\ContainerDyeingMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */