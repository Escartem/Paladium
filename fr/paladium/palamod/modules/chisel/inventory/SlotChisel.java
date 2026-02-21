package fr.paladium.palamod.modules.chisel.inventory;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotChisel extends Slot {
  private final ChiselContainer container;
  
  private final ChiselInventory selInventory;
  
  public SlotChisel(ChiselContainer container, ChiselInventory inv, IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
    this.container = container;
    this.selInventory = inv;
  }
  
  public boolean func_75214_a(ItemStack itemstack) {
    return false;
  }
  
  public boolean func_82869_a(EntityPlayer par1EntityPlayer) {
    if (this.container.finished)
      return false; 
    return (par1EntityPlayer.field_71071_by.func_70445_o() == null);
  }
  
  public void func_82870_a(EntityPlayer player, ItemStack itemstack) {
    ItemStack heldStack = player.field_71071_by.func_70445_o();
    if (heldStack == null) {
      this.selInventory.func_70298_a(60, 1);
    } else {
      func_75215_d(itemstack.func_77946_l());
      player.field_71071_by.func_70437_b(null);
      if (this.selInventory.inventory[60] == null)
        return; 
      player.field_71071_by.func_70437_b(new ItemStack(itemstack.func_77973_b(), (this.selInventory.inventory[60]).field_77994_a, itemstack.func_77960_j()));
      this.selInventory.func_70299_a(60, null);
    } 
    this.selInventory.updateItems();
    this.container.chisel.func_77972_a(1, (EntityLivingBase)player);
    if (this.container.chisel.field_77994_a <= 0)
      player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null; 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\inventory\SlotChisel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */