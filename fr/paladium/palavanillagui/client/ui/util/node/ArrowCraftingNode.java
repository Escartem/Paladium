package fr.paladium.palavanillagui.client.ui.util.node;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;

public class ArrowCraftingNode extends Node {
  private final double margin = 12.0D;
  
  protected ArrowCraftingNode(double x, double y) {
    super(x, y, 125.0D, 60.0D);
  }
  
  public static ArrowCraftingNode create(double x, double y) {
    return new ArrowCraftingNode(x, y);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY() + (getHeight() - 12.0D) / 2.0D, getWidth(), 12.0D, ColorConstant.GRAY);
    getClass();
    getClass();
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX() + getWidth() - 12.0D * 2.0D, getY() + (getHeight() - 12.0D * 3.0D) / 2.0D, 12.0D, 12.0D * 3.0D, ColorConstant.GRAY);
    getClass();
    getClass();
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX() + getWidth() - 12.0D * 3.0D, getY() + (getHeight() - 12.0D * 5.0D) / 2.0D, 12.0D, 12.0D * 5.0D, ColorConstant.GRAY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\node\ArrowCraftingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */