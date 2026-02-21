package fr.paladium.palashop.client.ui.kit.scrollbar;

import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.utils.box.BoundingBox;
import lombok.NonNull;

public class ScrollbarNode extends ScrollbarNode {
  private static final Color BACKGROUND_COLOR = ColorConstant.LIGHT_DARK;
  
  private static final Color SCROLL_COLOR = ColorConstant.GRAY;
  
  private final boolean vertical;
  
  public boolean isVertical() {
    return this.vertical;
  }
  
  private Color backgroundColor = BACKGROUND_COLOR;
  
  public Color getBackgroundColor() {
    return this.backgroundColor;
  }
  
  private Color scrollColor = SCROLL_COLOR;
  
  public Color getScrollColor() {
    return this.scrollColor;
  }
  
  protected ScrollbarNode(double x, double y, double width, double height, boolean vertical) {
    super(x, y, vertical ? width : (width / 5.0D), vertical ? (height / 5.0D) : height, BoundingBox.create(x, y, width, height));
    this.vertical = vertical;
  }
  
  @NonNull
  public static ScrollbarNode vertical(double x, double y, double height) {
    return new ScrollbarNode(x, y, 10.0D, height, true);
  }
  
  @NonNull
  public static ScrollbarNode horizontal(double x, double y, double width) {
    return new ScrollbarNode(x, y, width, 10.0D, false);
  }
  
  public void drawScrollbar(double mouseX, double mouseY, float ticks) {
    BoundingBox scroll = getScroll();
    DrawUtils.SHAPE.drawRoundedRect(scroll.getMinX(), scroll.getMinY(), scroll.getWidth(), scroll.getHeight(), this.backgroundColor, (float)(this.vertical ? dw(2.0D) : dh(2.0D)));
    DrawUtils.SHAPE.drawRoundedRect(getX(), getY(), getWidth(), getHeight(), this.scrollColor.brighter(hoverValue(0.2F)), (float)(this.vertical ? dw(2.0D) : dh(2.0D)));
  }
  
  @NonNull
  public final <T extends ScrollbarNode> T backgroundColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.backgroundColor = color;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ScrollbarNode> T scrollColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.scrollColor = color;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\scrollbar\ScrollbarNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */