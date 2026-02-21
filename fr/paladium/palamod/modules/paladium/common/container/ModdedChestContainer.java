package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.inventory.slot.SlotRing;
import fr.paladium.palamod.modules.paladium.common.inventory.slot.SlotRingEndium;
import fr.paladium.palamod.modules.paladium.common.logics.ModdedChestLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ModdedChestContainer extends Container {
  protected ModdedChestLogic tile;
  
  protected InventoryPlayer inventory;
  
  public ModdedChestContainer(SimpleGHandler.GuiHandlerData data) {
    this((ModdedChestLogic)data.getTileEntity(ModdedChestLogic.class), data.getInventory());
  }
  
  public ModdedChestContainer(ModdedChestLogic chestTile, InventoryPlayer pInv) {
    this.inventory = pInv;
    this.tile = chestTile;
    for (int i = 0; i < 9; i++) {
      for (int k = 0; k < 12; k++)
        func_75146_a(new Slot((IInventory)this.tile, k + i * 12, 12 + k * 18, 8 + i * 18)); 
    } 
    int slotRingCount = this.tile.func_70302_i_() - 108;
    for (int j = 0; j < slotRingCount; j++)
      func_75146_a((this.tile instanceof fr.paladium.palamod.modules.paladium.common.logics.EndiumChestLogic) ? (Slot)new SlotRingEndium((IInventory)this.tile, 108 + j, 228, 8 + j * 18) : (Slot)new SlotRing((IInventory)this.tile, 108 + j, 228, 8 + j * 18)); 
    bindPlayerInventory();
  }
  
  private void bindPlayerInventory() {
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)this.inventory, j + i * 9 + 9, 39 + j * 18, 174 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)this.inventory, i, 39 + i * 18, 232)); 
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slot) {
    ItemStack stack = null;
    Slot slots = this.field_75151_b.get(slot);
    if (slots != null && slots.func_75216_d()) {
      ItemStack stack1 = slots.func_75211_c();
      stack = stack1.func_77946_l();
      if (slot < this.tile.func_70302_i_()) {
        if (!func_75135_a(stack1, this.tile.func_70302_i_(), this.tile.func_70302_i_() + 36, true))
          return null; 
        slots.func_75220_a(stack1, stack);
      } 
      if (slot >= this.tile.func_70302_i_()) {
        if (!func_75135_a(stack1, 0, 108, false))
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
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tile.func_70300_a(player);
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    this.tile.func_70305_f();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\ModdedChestContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */