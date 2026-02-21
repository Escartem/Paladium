package fr.paladium.paladiumui.kit.scrollbar;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.utils.box.BoundingBox;
import lombok.NonNull;

public class ScrollbarNode extends ScrollbarNode {
  private static final Color BACKGROUND_COLOR = new Color(65, 55, 54);
  
  private static final Color SCROLL_COLOR = new Color(158, 158, 158);
  
  private Color backgroundColor = BACKGROUND_COLOR;
  
  public Color getBackgroundColor() {
    return this.backgroundColor;
  }
  
  private Color scrollColor = SCROLL_COLOR;
  
  public Color getScrollColor() {
    return this.scrollColor;
  }
  
  protected ScrollbarNode(double x, double y, double width, double height) {
    super(x, y, width, height / 4.0D, BoundingBox.create(x + width / 4.0D, y, width / 2.0D, height));
  }
  
  @NonNull
  public static ScrollbarNode create(double x, double y, double height) {
    return new ScrollbarNode(x, y, 31.0D, height);
  }
  
  @NonNull
  public static ScrollbarNode create(double x, double y, double height, double width) {
    return new ScrollbarNode(x, y, width, height);
  }
  
  public void drawScrollbar(double mouseX, double mouseY, float ticks) {
    BoundingBox scroll = getScroll();
    DrawUtils.SHAPE.drawRect(scroll.getMinX(), scroll.getMinY(), scroll.getWidth(), scroll.getHeight(), this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), this.scrollColor.brighter(hoverValue(0.2F)));
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


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\scrollbar\ScrollbarNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */