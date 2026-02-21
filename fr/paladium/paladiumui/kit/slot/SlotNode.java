package fr.paladium.paladiumui.kit.slot;

import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slot.SlotNode;
import lombok.NonNull;
import net.minecraft.inventory.IInventory;

public class SlotNode extends SlotNode {
  protected SlotNode(double x, double y, double size, int slotIndex, @NonNull IInventory inventory) {
    super(x, y, size, slotIndex, inventory);
    if (inventory == null)
      throw new NullPointerException("inventory is marked non-null but is null"); 
    itemSize((int)(size * 0.8D));
    texture(ResourceConstant.SLOT);
    hoverTexture(ResourceConstant.SLOT_HOVER);
  }
  
  @NonNull
  public static SlotNode create(double x, double y, double size, int slotIndex, @NonNull IInventory inventory) {
    if (inventory == null)
      throw new NullPointerException("inventory is marked non-null but is null"); 
    return new SlotNode(x, y, size, slotIndex, inventory);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\slot\SlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */