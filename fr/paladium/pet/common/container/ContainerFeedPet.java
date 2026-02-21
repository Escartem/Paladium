package fr.paladium.pet.common.container;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.pet.common.container.slot.SlotFeedPet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerFeedPet extends PaladiumContainer {
  public ContainerFeedPet(EntityPlayer player) {
    func_75146_a((Slot)new SlotFeedPet((IInventory)new InventoryFeedPet(), 0, player));
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)player.field_71071_by, j + i * 9 + 9, 0, 0)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)player.field_71071_by, i, 0, 0)); 
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
  }
  
  public int getPlayerInventoryPosition() {
    return 1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\container\ContainerFeedPet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */