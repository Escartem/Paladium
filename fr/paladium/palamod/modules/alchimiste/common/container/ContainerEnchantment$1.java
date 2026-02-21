package fr.paladium.palamod.modules.alchimiste.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;

class null extends InventoryBasic {
  private static final String __OBFID = "CL_00001746";
  
  null(String x0, boolean x1, int x2) {
    super(x0, x1, x2);
  }
  
  public int func_70297_j_() {
    return 1;
  }
  
  public void func_70296_d() {
    super.func_70296_d();
    ContainerEnchantment.this.func_75130_a((IInventory)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\container\ContainerEnchantment$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */