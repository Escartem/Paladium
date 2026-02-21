package fr.paladium.palamod.modules.trixium.gui.container.slot;

import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import net.minecraft.inventory.IInventory;

public class UITrixiumSlot extends SlotNode {
  public UITrixiumSlot(IInventory inv, int slot, double x, double y, double size) {
    super(inv, slot, x, y, size, size);
    setItemScale(0.8F);
    setZindex(1);
    setTexture("palamod:textures/gui/trixium/container/trixium_slot");
    setHoveredTexture("palamod:textures/gui/trixium/container/trixium_slot_hover");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\container\slot\UITrixiumSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */