package fr.paladium.palamod.modules.paladium.client.ui.util;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import lombok.NonNull;
import net.minecraft.item.Item;

public class RectItemNode extends RectNode {
  private Item item;
  
  protected RectItemNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    color(new Color(0.0F, 0.0F, 0.0F, 0.15F));
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    body(() -> ItemNode.create(0.0D, 0.0D, getWidth()).item(this.item).attach((Node)this));
  }
  
  public static RectItemNode create(double x, double y, double width, double height) {
    return new RectItemNode(x, y, width, height);
  }
  
  public <T extends RectItemNode> T item(Item item) {
    this.item = item;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\u\\util\RectItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */