package fr.paladium.palavanillagui.client.ui.util.node;

import fr.paladium.palavanillagui.client.ClientProxy;
import fr.paladium.palavanillagui.common.utils.InventoryShortcut;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class InventoryShortcutListNode extends Node {
  private static final Resource SHORTCUT_LIST_BACKGROUND = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/shortcut_list_background.png"));
  
  protected InventoryShortcutListNode(double x, double y, double size) {
    super(x, y, size, size);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    body(node -> {
          node.clearChildren();
          FlexNode.vertical(0.0D, 0.0D, getWidth()).margin(16.0D).body(()).attach(this);
        });
  }
  
  public static InventoryShortcutListNode create(double x, double y, double size) {
    return new InventoryShortcutListNode(x, y, size);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\node\InventoryShortcutListNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */