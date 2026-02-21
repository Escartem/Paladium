package fr.paladium.palamod.modules.trixium.gui.container.slot;

import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import net.minecraft.inventory.IInventory;

public class UITrixiumBaseSlot extends SlotNode {
  public UITrixiumBaseSlot(IInventory inventory, int id, double x, double y, double size) {
    super(inventory, id, x, y, size, size);
    setItemScale(0.8F);
    setZindex(1);
    setTexture("palamod:textures/gui/trixium/container/slot");
    setHoveredTexture("palamod:textures/gui/trixium/container/slot_hover");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\container\slot\UITrixiumBaseSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */