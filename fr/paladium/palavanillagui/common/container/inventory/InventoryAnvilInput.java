package fr.paladium.palavanillagui.common.container.inventory;

import fr.paladium.palaforgeutils.lib.container.impl.ForgeInventory;
import fr.paladium.palavanillagui.common.container.ContainerAnvil;
import net.minecraft.inventory.IInventory;

public class InventoryAnvilInput extends ForgeInventory {
  private ContainerAnvil containerAnvil;
  
  public InventoryAnvilInput(ContainerAnvil containerAnvil) {
    super("anvil.input", 2);
    this.containerAnvil = containerAnvil;
  }
  
  public boolean shouldDropOnClose() {
    return true;
  }
  
  public void func_70296_d() {
    super.func_70296_d();
    this.containerAnvil.func_75130_a((IInventory)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\container\inventory\InventoryAnvilInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */