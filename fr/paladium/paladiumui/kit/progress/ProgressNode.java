package fr.paladium.paladiumui.kit.progress;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import lombok.NonNull;

public class ProgressNode extends Node {
  private Color color;
  
  private double padding;
  
  private float progress;
  
  protected ProgressNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.padding = 7.0D;
  }
  
  @NonNull
  public static ProgressNode create(double x, double y, double width) {
    return new ProgressNode(x, y, width, 62.0D);
  }
  
  @NonNull
  public static ProgressNode create(double x, double y, double width, double height) {
    return new ProgressNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), ColorConstant.GRAY);
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-this.padding), getWidth(), this.padding, ColorConstant.GRAY.darker(0.2F));
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth() * this.progress, getHeight(), this.color);
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-this.padding), getWidth() * this.progress, this.padding, this.color.darker(0.2F));
  }
  
  @NonNull
  public <T extends ProgressNode> T color(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.color = color;
    return (T)this;
  }
  
  @NonNull
  public <T extends ProgressNode> T padding(double padding) {
    this.padding = padding;
    return (T)this;
  }
  
  @NonNull
  public <T extends ProgressNode> T progress(float progress) {
    this.progress = progress;
    return (T)this;
  }
  
  @NonNull
  public <T extends ProgressNode> T progress(float min, float max, float value) {
    this.progress = (value - min) / (max - min);
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\progress\ProgressNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */