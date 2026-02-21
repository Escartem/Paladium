package fr.paladium.palamod.modules.chisel.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotChiselInput extends Slot {
  private final ChiselContainer container;
  
  private final ChiselInventory selInventory;
  
  public SlotChiselInput(ChiselContainer container, ChiselInventory inv, int i, int j, int k) {
    super(inv, i, j, k);
    this.selInventory = inv;
    this.container = container;
  }
  
  public boolean func_75214_a(ItemStack itemstack) {
    if (this.container.finished)
      return false; 
    return super.func_75214_a(itemstack);
  }
  
  public boolean func_82869_a(EntityPlayer par1EntityPlayer) {
    return (!this.container.finished && super.func_82869_a(par1EntityPlayer));
  }
  
  public void func_75218_e() {
    if (this.container.finished)
      return; 
    super.func_75218_e();
    this.selInventory.updateItems();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\inventory\SlotChiselInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */