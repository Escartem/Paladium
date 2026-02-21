package fr.paladium.palamod.modules.shop.client.ui.node;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;

public class ShopBannerNode extends Node {
  private Color color;
  
  private Color shadowColor;
  
  private double bottomOffset;
  
  private double shadowHeight;
  
  protected ShopBannerNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.color = new Color(255, 255, 255);
    this.shadowColor = new Color(105, 106, 112);
    this.bottomOffset = 10.0D;
    this.shadowHeight = 5.0D;
  }
  
  public static ShopBannerNode create(double x, double y, double width, double height) {
    return new ShopBannerNode(x, y, width, height);
  }
  
  public ShopBannerNode color(Color color) {
    this.color = color;
    return this;
  }
  
  public ShopBannerNode shadowColor(Color shadowColor) {
    this.shadowColor = shadowColor;
    return this;
  }
  
  public ShopBannerNode bottomOffset(double offset) {
    this.bottomOffset = offset;
    return this;
  }
  
  public ShopBannerNode shadowheight(double size) {
    this.shadowHeight = size;
    return this;
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), this.color);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight(), this.bottomOffset, this.shadowHeight, this.shadowColor);
    DrawUtils.SHAPE.drawRect(getX() + getWidth() - this.bottomOffset, getY() + getHeight(), this.bottomOffset, this.shadowHeight, this.shadowColor);
    DrawUtils.SHAPE.drawRect(getX() + this.bottomOffset, getY() + getHeight(), getWidth() - this.bottomOffset * 2.0D, this.bottomOffset, this.color);
    DrawUtils.SHAPE.drawRect(getX() + this.bottomOffset, getY() + getHeight() + this.bottomOffset, getWidth() - this.bottomOffset * 2.0D, this.shadowHeight, this.shadowColor);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\clien\\ui\node\ShopBannerNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */