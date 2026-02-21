package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.inventory.VoidStoneInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class VoidStoneContainer extends Container {
  public final VoidStoneInventory inventory;
  
  private final int voidstoneSlot;
  
  public InventoryPlayer playerInventory;
  
  public ItemStack voidstone;
  
  public VoidStoneContainer(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory(), new VoidStoneInventory());
  }
  
  public VoidStoneContainer(InventoryPlayer inventoryplayer, VoidStoneInventory inventory) {
    this.inventory = inventory;
    this.playerInventory = inventoryplayer;
    this.voidstoneSlot = this.playerInventory.field_70461_c;
    int top = 8, left = 62;
    func_75146_a(new Slot((IInventory)this.inventory, 0, 80, 18));
    top += 32;
    left -= 54;
    int i;
    for (i = 0; i < 27; i++)
      func_75146_a(new Slot((IInventory)inventoryplayer, i + 9, left + i % 9 * 18, top + i / 9 * 18)); 
    top += 58;
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventoryplayer, i, left + i % 9 * 18, top + i / 9 * 18)); 
    this.voidstone = inventoryplayer.func_70448_g();
  }
  
  public ItemStack func_75144_a(int slotIndex, int buttonPressed, int flag, EntityPlayer entityplayer) {
    if ((flag == 2 && buttonPressed == this.voidstoneSlot) || slotIndex - 1 - 27 == this.voidstoneSlot)
      return null; 
    return super.func_75144_a(slotIndex, buttonPressed, flag, entityplayer);
  }
  
  public boolean func_75145_c(EntityPlayer entityplayer) {
    return this.inventory.func_70300_a(entityplayer);
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slotIdx) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(slotIdx);
    if (slot != null && slot.func_75216_d()) {
      ItemStack itemstack1 = slot.func_75211_c();
      itemstack = itemstack1.func_77946_l();
      if (slotIdx >= 1 && !(itemstack.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemVoidStone) && 
        !func_75135_a(itemstack1, 0, 1, true))
        return null; 
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d((ItemStack)null);
      } else {
        slot.func_75215_d(itemstack1);
        slot.func_75218_e();
      } 
      if (itemstack1.field_77994_a == itemstack.field_77994_a)
        return null; 
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d(null);
        return null;
      } 
      slot.func_82870_a(player, itemstack1);
    } 
    return itemstack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\VoidStoneContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */