package fr.paladium.palacommunityevent.client.gui.container.slot;

import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import net.minecraft.inventory.IInventory;

public class UICommunityEventBaseSlot extends SlotNode {
  public UICommunityEventBaseSlot(IInventory inventory, int id, double x, double y, double size) {
    super(inventory, id, x, y, size, size);
    setItemScale(0.8F);
    setZindex(1);
    setTexture("palacommunityevent:textures/gui/common/slot");
    setHoveredTexture("palacommunityevent:textures/gui/common/slot_hover");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\slot\UICommunityEventBaseSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */