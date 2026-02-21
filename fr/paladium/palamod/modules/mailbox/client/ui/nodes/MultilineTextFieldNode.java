package fr.paladium.palamod.modules.mailbox.client.ui.nodes;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.MultilineTextFieldNode;
import lombok.NonNull;

public class MultilineTextFieldNode extends MultilineTextFieldNode {
  private Color backgroundColor = ColorConstant.GRAY;
  
  private Color borderColor = ColorConstant.GRAY;
  
  private Color focusColor = ColorConstant.GRAY.darker(0.2F);
  
  protected MultilineTextFieldNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static MultilineTextFieldNode create(double x, double y, double width, double height) {
    return new MultilineTextFieldNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), this.backgroundColor);
    DrawUtils.SHAPE.drawBorder(getX(), getY(), getX() + getWidth(), getY() + getHeight(), this.borderColor, 5.0D);
    if (isFocused())
      DrawUtils.SHAPE.drawBorder(getX(), getY(), getX() + getWidth(), getY() + getHeight(), this.focusColor, 5.0D); 
    super.draw(mouseX, mouseY, ticks);
  }
  
  @NonNull
  public <T extends MultilineTextFieldNode> T backgroundColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.backgroundColor = color;
    return (T)this;
  }
  
  @NonNull
  public <T extends MultilineTextFieldNode> T borderColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.borderColor = color;
    return (T)this;
  }
  
  @NonNull
  public <T extends MultilineTextFieldNode> T focusColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.focusColor = color;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\nodes\MultilineTextFieldNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */