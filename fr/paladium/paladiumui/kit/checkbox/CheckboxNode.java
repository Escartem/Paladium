package fr.paladium.paladiumui.kit.checkbox;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.checkbox.CheckboxNode;
import lombok.NonNull;

public class CheckboxNode extends CheckboxNode {
  private double borderSize = 5.0D;
  
  public double getBorderSize() {
    return this.borderSize;
  }
  
  private Color checkedBackgroundColor = ColorConstant.GREEN;
  
  public Color getCheckedBackgroundColor() {
    return this.checkedBackgroundColor;
  }
  
  private Color uncheckedBackgroundColor = ColorConstant.GRAY;
  
  public Color getUncheckedBackgroundColor() {
    return this.uncheckedBackgroundColor;
  }
  
  private Color color = Color.WHITE;
  
  public Color getColor() {
    return this.color;
  }
  
  protected CheckboxNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.borderSize = width / 6.0D;
  }
  
  @NonNull
  public static CheckboxNode create(double x, double y, double size) {
    return new CheckboxNode(x, y, size, size);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    Color color = (isChecked() ? this.checkedBackgroundColor : this.uncheckedBackgroundColor).darker(hoverValue(0.2F));
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), color);
    DrawUtils.SHAPE.drawRect(getX(), getY() - this.borderSize, getWidth(), this.borderSize, color);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight(), getWidth(), this.borderSize, color);
    DrawUtils.SHAPE.drawRect(getX() - this.borderSize, getY(), this.borderSize, getHeight(), color);
    DrawUtils.SHAPE.drawRect(getX() + getWidth(), getY(), this.borderSize, getHeight(), color);
    if (isChecked()) {
      double scareSize = (int)dw(7.0D);
      double iconX = getX() + dw(2.0D) - scareSize * 2.0D;
      double iconY = getY() + dh(2.0D) - scareSize * 2.5D;
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 0.0D, iconY + scareSize * 3.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 1.0D, iconY + scareSize * 4.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 2.0D, iconY + scareSize * 2.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 2.0D, iconY + scareSize * 3.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 3.0D, iconY + scareSize * 0.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 3.0D, iconY + scareSize * 1.0D, scareSize, scareSize, this.color);
    } 
  }
  
  @NonNull
  public <T extends CheckboxNode> T borderSize(double borderSize) {
    this.borderSize = borderSize;
    return (T)this;
  }
  
  @NonNull
  public <T extends CheckboxNode> T checkedBackgroundColor(@NonNull Color checkedBackgroundColor) {
    if (checkedBackgroundColor == null)
      throw new NullPointerException("checkedBackgroundColor is marked non-null but is null"); 
    this.checkedBackgroundColor = checkedBackgroundColor;
    return (T)this;
  }
  
  @NonNull
  public <T extends CheckboxNode> T uncheckedBackgroundColor(@NonNull Color uncheckedBackgroundColor) {
    if (uncheckedBackgroundColor == null)
      throw new NullPointerException("uncheckedBackgroundColor is marked non-null but is null"); 
    this.uncheckedBackgroundColor = uncheckedBackgroundColor;
    return (T)this;
  }
  
  @NonNull
  public <T extends CheckboxNode> T color(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.color = color;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\checkbox\CheckboxNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */