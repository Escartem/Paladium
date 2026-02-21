package fr.paladium.palatrade.client.gui.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;

public class CheckboxNode extends AClickableNode {
  private final Color checkedBackgroundColor = ColorConstant.GREEN;
  
  public Color getCheckedBackgroundColor() {
    return this.checkedBackgroundColor;
  }
  
  private final Color uncheckedBackgroundColor = ColorConstant.GRAY;
  
  public Color getUncheckedBackgroundColor() {
    return this.uncheckedBackgroundColor;
  }
  
  private final Color color = Color.WHITE;
  
  public Color getColor() {
    return this.color;
  }
  
  private double borderSize = 5.0D;
  
  private boolean checked;
  
  public double getBorderSize() {
    return this.borderSize;
  }
  
  public boolean isChecked() {
    return this.checked;
  }
  
  public CheckboxNode(double x, double y, double width, double height, Consumer<CheckboxNode> callback) {
    super(x, y, width, height);
    setCallback(node -> {
          setChecked(!this.checked);
          callback.accept(this);
        });
    this.borderSize = width / 6.0D;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    Color color = (isChecked() ? this.checkedBackgroundColor : this.uncheckedBackgroundColor).darker(isEnabled() ? animationValue(0.2F) : 0.0F);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), color);
    DrawUtils.SHAPE.drawRect(getX(), getY() - this.borderSize, getWidth(), this.borderSize, color);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight(), getWidth(), this.borderSize, color);
    DrawUtils.SHAPE.drawRect(getX() - this.borderSize, getY(), this.borderSize, getHeight(), color);
    DrawUtils.SHAPE.drawRect(getX() + getWidth(), getY(), this.borderSize, getHeight(), color);
    if (isChecked()) {
      double scareSize = (int)(getWidth() / 7.0D);
      double iconX = getX() + getWidth() / 2.0D - scareSize * 2.0D;
      double iconY = getY() + getHeight() / 2.0D - scareSize * 2.5D;
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 0.0D, iconY + scareSize * 3.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 1.0D, iconY + scareSize * 4.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 2.0D, iconY + scareSize * 2.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 2.0D, iconY + scareSize * 3.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 3.0D, iconY + scareSize * 0.0D, scareSize, scareSize, this.color);
      DrawUtils.SHAPE.drawRect(iconX + scareSize * 3.0D, iconY + scareSize * 1.0D, scareSize, scareSize, this.color);
    } 
  }
  
  public void setBorderSize(double borderSize) {
    this.borderSize = borderSize;
  }
  
  public void setChecked(boolean checked) {
    this.checked = checked;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\client\gui\node\CheckboxNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */