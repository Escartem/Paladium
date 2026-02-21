package fr.paladium.pet.client.ui.feed.node;

import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import net.minecraft.inventory.IInventory;

public class FeedPetBaseSlotNode extends SlotNode {
  private static final String TEXTURE = "palapet:textures/ui/feed/slot/slot";
  
  private static final String HOVERED_TEXTURE = "palapet:textures/ui/feed/slot/slot_hover";
  
  public FeedPetBaseSlotNode(IInventory inventory, int slotIndex, double x, double y, double size) {
    super(inventory, slotIndex, x, y, size, size);
    setItemScale(0.8F);
    setTexture("palapet:textures/ui/feed/slot/slot");
    setHoveredTexture("palapet:textures/ui/feed/slot/slot_hover");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\feed\node\FeedPetBaseSlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */