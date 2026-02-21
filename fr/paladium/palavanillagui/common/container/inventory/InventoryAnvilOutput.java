package fr.paladium.palavanillagui.common.container.inventory;

import fr.paladium.palaforgeutils.lib.container.impl.ForgeInventory;

public class InventoryAnvilOutput extends ForgeInventory {
  public InventoryAnvilOutput() {
    super("anvil.output", 1);
  }
  
  public boolean shouldDropOnClose() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\container\inventory\InventoryAnvilOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */