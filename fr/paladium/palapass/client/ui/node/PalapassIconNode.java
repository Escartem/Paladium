package fr.paladium.palapass.client.ui.node;

import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PalapassIconNode extends Node {
  private String icon;
  
  protected PalapassIconNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static PalapassIconNode create(double x, double y, double size) {
    return new PalapassIconNode(x, y, size, size);
  }
  
  public void init(UI ui) {
    if (this.icon == null)
      return; 
    if (this.icon.startsWith("http")) {
      ImageNode.create(0.0D, 0.0D)
        .resource(this.icon)
        .size(getWidth(), getHeight())
        .attach(this);
    } else {
      ItemNode.create(0.0D, 0.0D, getWidth())
        .item(ItemStackSerializer.read(new String(Base64.getDecoder().decode(this.icon), StandardCharsets.UTF_8)))
        .stackSize(false)
        .attach(this);
    } 
  }
  
  public PalapassIconNode icon(String icon) {
    this.icon = icon;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\clien\\ui\node\PalapassIconNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */