package fr.paladium.palajobs.client.ui.boss;

import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import net.minecraft.inventory.IInventory;

public class UIMinecraftSlot extends SlotNode {
  public UIMinecraftSlot(IInventory inventory, int slotIndex, double x, double y, double width) {
    super(inventory, slotIndex, x, y, width, width);
    setItemScale(0.8F);
    setZindex(1);
    setTexture("apollon:textures/gui/buttons/minecraft/slot");
    setHoveredTexture("apollon:textures/gui/buttons/minecraft/slot_hover");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\boss\UIMinecraftSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */