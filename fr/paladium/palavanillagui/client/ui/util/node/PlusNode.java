package fr.paladium.palavanillagui.client.ui.util.node;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;

public class PlusNode extends Node {
  protected PlusNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static PlusNode create(double x, double y, double width, double height) {
    return new PlusNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX() + (getWidth() - 12.0D) / 2.0D, getY(), 12.0D, getHeight(), ColorConstant.GRAY);
    DrawUtils.SHAPE.drawRect(getX(), getY() + (getHeight() - 12.0D) / 2.0D, getWidth(), 12.0D, ColorConstant.GRAY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\node\PlusNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */