package fr.paladium.palarpg.client.ui.kit.background;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import lombok.NonNull;

public class BackgroundElementNode extends Node {
  private double cornerSize = 0.0D;
  
  private Color color = new Color(40, 40, 40);
  
  private Color shadowColor = new Color(31, 31, 31);
  
  private float shadowX = 0.0F;
  
  private float shadowY = 0.0F;
  
  protected BackgroundElementNode(double x, double y) {
    super(x, y);
  }
  
  protected BackgroundElementNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static <T extends BackgroundElementNode> T create(double x, double y) {
    return (T)new BackgroundElementNode(x, y);
  }
  
  public static <T extends BackgroundElementNode> T create(double x, double y, double width, double height) {
    return (T)new BackgroundElementNode(x, y, width, height);
  }
  
  public <T extends BackgroundElementNode> T cornerSize(double cornerSize) {
    this.cornerSize = cornerSize;
    return (T)this;
  }
  
  public <T extends BackgroundElementNode> T color(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.color = color;
    return (T)this;
  }
  
  public <T extends BackgroundElementNode> T shadowColor(@NonNull Color shadowColor) {
    if (shadowColor == null)
      throw new NullPointerException("shadowColor is marked non-null but is null"); 
    this.shadowColor = shadowColor;
    return (T)this;
  }
  
  public <T extends BackgroundElementNode> T shadow(float shadowX, float shadowY) {
    this.shadowX = shadowX;
    this.shadowY = shadowY;
    return (T)this;
  }
  
  public <T extends BackgroundElementNode> T shadowX(float shadowX) {
    this.shadowX = shadowX;
    return (T)this;
  }
  
  public <T extends BackgroundElementNode> T shadowY(float shadowY) {
    this.shadowY = shadowY;
    return (T)this;
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.cornerSize <= 0.0D || this.cornerSize >= getHeight() || this.cornerSize >= getWidth())
      return; 
    double minusWidth = aw(-(this.cornerSize * 2.0D));
    double minusHeight = ah(-(this.cornerSize * 2.0D));
    if (this.shadowX != 0.0F || (this.shadowY != 0.0F && this.shadowColor != null)) {
      DrawUtils.SHAPE.drawRect(getX() + this.shadowX, getY() + this.cornerSize + this.shadowY, this.cornerSize, minusHeight, this.shadowColor);
      DrawUtils.SHAPE.drawRect(getX() + this.cornerSize + this.shadowX, getY() + this.shadowY, minusWidth, getHeight(), this.shadowColor);
      DrawUtils.SHAPE.drawRect(getX() + aw(-this.cornerSize) + this.shadowX, getY() + this.cornerSize + this.shadowY, this.cornerSize, minusHeight, this.shadowColor);
    } 
    DrawUtils.SHAPE.drawRect(getX(), getY() + this.cornerSize, this.cornerSize, minusHeight, this.color);
    DrawUtils.SHAPE.drawRect(getX() + this.cornerSize, getY(), minusWidth, getHeight(), this.color);
    DrawUtils.SHAPE.drawRect(getX() + aw(-this.cornerSize), getY() + this.cornerSize, this.cornerSize, minusHeight, this.color);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\clien\\ui\kit\background\BackgroundElementNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */