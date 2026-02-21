package fr.paladium.palatrade.common.container.inventory;

import fr.paladium.lib.apollon.container.abstracts.PaladiumInventory;
import net.minecraft.item.ItemStack;

public class InventoryTrade extends PaladiumInventory {
  private boolean validated;
  
  public void setValidated(boolean validated) {
    this.validated = validated;
  }
  
  public boolean isValidated() {
    return this.validated;
  }
  
  public InventoryTrade() {
    super("palatrade", 42);
    this.validated = false;
  }
  
  public boolean func_94041_b(int slot, ItemStack stack) {
    return (!this.validated && slot < 21);
  }
  
  public boolean shouldDropOnClose() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\container\inventory\InventoryTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */