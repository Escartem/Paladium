package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.inventory.slot.SlotResult;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumMachineLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class PaladiumMachineContainer extends Container {
  private final PaladiumMachineLogic tile;
  
  public PaladiumMachineContainer(SimpleGHandler.GuiHandlerData data) {
    this((PaladiumMachineLogic)data.getTileEntity(PaladiumMachineLogic.class), data.getInventory());
  }
  
  public PaladiumMachineContainer(PaladiumMachineLogic tile, InventoryPlayer inventory) {
    this.tile = tile;
    func_75146_a(new Slot((IInventory)tile, 0, 89, 95));
    func_75146_a(new Slot((IInventory)tile, 1, 25, 103));
    func_75146_a(new Slot((IInventory)tile, 2, 53, 123));
    func_75146_a(new Slot((IInventory)tile, 3, 153, 103));
    func_75146_a(new Slot((IInventory)tile, 4, 125, 123));
    func_75146_a((Slot)new SlotResult((IInventory)tile, 5, 89, 146));
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventory, j + i * 9 + 9, 17 + j * 18, 171 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventory, i, 17 + i * 18, 229)); 
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slot) {
    ItemStack stack = null;
    Slot slots = this.field_75151_b.get(slot);
    if (slots != null && slots.func_75216_d()) {
      ItemStack stack1 = slots.func_75211_c();
      stack = stack1.func_77946_l();
      if (slot < 6) {
        if (!func_75135_a(stack1, 6, 42, true))
          return null; 
        slots.func_75220_a(stack1, stack);
      } 
      if (slot >= 6) {
        if (!func_75135_a(stack1, 0, 5, false))
          return null; 
        slots.func_75220_a(stack1, stack);
      } 
      if (stack1.field_77994_a == 0) {
        slots.func_75215_d((ItemStack)null);
      } else {
        slots.func_75218_e();
      } 
      if (stack1.field_77994_a == stack.field_77994_a)
        return null; 
      slots.func_82870_a(player, stack1);
    } 
    return stack;
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    this.tile.func_70305_f();
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tile.func_70300_a(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\PaladiumMachineContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */