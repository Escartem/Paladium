package fr.paladium.palacommunityevent.client.gui.container.slot;

import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import net.minecraft.inventory.IInventory;

public class UICommunityEventSlot extends SlotNode {
  public UICommunityEventSlot(IInventory inv, int slot, double x, double y, double size, String backgroundFile) {
    super(inv, slot, x, y, size, size);
    setItemScale(0.8F);
    setZindex(1);
    setTexture("palacommunityevent:textures/gui/" + backgroundFile + ".png");
    setHoveredTexture("palacommunityevent:textures/gui/" + backgroundFile + "_hover.png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\slot\UICommunityEventSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */