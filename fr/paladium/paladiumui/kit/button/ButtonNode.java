package fr.paladium.paladiumui.kit.button;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import lombok.NonNull;

public class ButtonNode extends Node {
  public static final Color[] DISBALED_COLOR = new Color[] { ColorConstant.DISABLED, new Color(217, 217, 217), new Color(91, 91, 91), new Color(180, 180, 180) };
  
  public static final Color[] DEFAULT_COLOR = new Color[] { ColorConstant.PRIMARY, new Color(255, 132, 123), new Color(146, 19, 12), new Color(221, 43, 29) };
  
  public static final Color[] GREEN_COLOR = new Color[] { ColorConstant.GREEN, new Color(127, 255, 123), new Color(52, 146, 12), new Color(87, 190, 19) };
  
  private Color[] color;
  
  public Color[] getColor() {
    return this.color;
  }
  
  private double horizontalPadding = 30.0D;
  
  public double getHorizontalPadding() {
    return this.horizontalPadding;
  }
  
  private double verticalPadding = 10.0D;
  
  public double getVerticalPadding() {
    return this.verticalPadding;
  }
  
  private boolean showBackground = true;
  
  public boolean isShowBackground() {
    return this.showBackground;
  }
  
  private boolean showBorder = true;
  
  public boolean isShowBorder() {
    return this.showBorder;
  }
  
  protected ButtonNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.color = DEFAULT_COLOR;
  }
  
  protected ButtonNode(double x, double y) {
    this(x, y, 0.0D, 0.0D);
  }
  
  @NonNull
  public static ButtonNode create(double x, double y) {
    return new ButtonNode(x, y);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (!this.showBackground)
      return; 
    float brightness = hoverValue(0.3F);
    Color[] currentColor = isEnabled() ? this.color : DISBALED_COLOR;
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), currentColor[0].brighter(brightness));
    if (this.showBorder) {
      DrawUtils.SHAPE.drawRect(getX(), getY() - 5.0D, getWidth(), 5.0D, currentColor[1].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight(), getWidth(), 5.0D, currentColor[2].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX() - 5.0D, getY(), 5.0D, getHeight(), currentColor[3].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX() + getWidth(), getY(), 5.0D, getHeight(), currentColor[3].brighter(brightness));
    } 
  }
  
  @NonNull
  public <T extends ButtonNode> T color(@NonNull Color[] color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.color = color;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T horizontalPadding(double horizontalPadding) {
    this.horizontalPadding = horizontalPadding;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T verticalPadding(double verticalPadding) {
    this.verticalPadding = verticalPadding;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T padding(double padding) {
    this.horizontalPadding = padding;
    this.verticalPadding = padding;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T showBackground(boolean showBackground) {
    this.showBackground = showBackground;
    return (T)this;
  }
  
  @NonNull
  public <T extends ButtonNode> T showBorder(boolean showBorder) {
    this.showBorder = showBorder;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\ButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */