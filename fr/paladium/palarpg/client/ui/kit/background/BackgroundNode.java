package fr.paladium.palarpg.client.ui.kit.background;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import lombok.NonNull;

public class BackgroundNode extends BackgroundNode {
  public static final Color BACKGROUND_COLOR = new Color(14, 14, 14);
  
  public static final Color BORDER_COLOR = new Color(58, 59, 60);
  
  protected BackgroundNode(double width, double height) {
    super(width, height);
    setup();
  }
  
  protected BackgroundNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    setup();
  }
  
  private void setup() {
    innerRadius(8.0D);
    outerRadius(28.0D);
    backgroundColor(BACKGROUND_COLOR);
    borderColor(BORDER_COLOR);
  }
  
  @NonNull
  public static BackgroundNode create(double x, double y, double width, double height) {
    return new BackgroundNode(x, y, width, height);
  }
  
  @NonNull
  public static BackgroundNode create(double width, double height) {
    return new BackgroundNode(width, height);
  }
  
  @NonNull
  public static BackgroundNode create() {
    return new BackgroundNode(1864.0D, 1024.0D);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    super.draw(mouseX, mouseY, ticks);
    DrawUtils.SHAPE.drawRect(getX() + getInnerRadius(), getY() - getInnerRadius(), getInnerRadius(), getHeight() + getInnerRadius() * 2.0D, getBackgroundColor());
    DrawUtils.SHAPE.drawRect(getX() + getWidth() - getInnerRadius() * 2.0D, getY() - getInnerRadius(), getInnerRadius(), getHeight() + getInnerRadius() * 2.0D, getBackgroundColor());
    DrawUtils.SHAPE.drawRect(getX() - getInnerRadius(), getY() + getInnerRadius(), getWidth() + getInnerRadius() * 2.0D, getInnerRadius(), getBackgroundColor());
    DrawUtils.SHAPE.drawRect(getX() - getInnerRadius(), getY() + getHeight() - getInnerRadius() * 2.0D, getWidth() + getInnerRadius() * 2.0D, getInnerRadius(), getBackgroundColor());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\clien\\ui\kit\background\BackgroundNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */