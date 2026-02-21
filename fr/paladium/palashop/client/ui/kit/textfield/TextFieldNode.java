package fr.paladium.palashop.client.ui.kit.textfield;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class TextFieldNode extends TextFieldNode {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.WHITE);
  
  private Color backgroundColor = ColorConstant.LIGHT_DARK;
  
  private Color focusColor = Color.WHITE;
  
  private int borderRadius = 6;
  
  protected TextFieldNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    marginHorizontal(10.0D);
    info(TEXT_INFO);
    align(Align.START, Align.CENTER);
  }
  
  @NonNull
  public static TextFieldNode create(double x, double y, double width) {
    return new TextFieldNode(x, y, width, 49.0D);
  }
  
  @NonNull
  public static TextFieldNode create(double x, double y, double width, double height) {
    return new TextFieldNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (isFocused())
      DrawUtils.SHAPE.drawRoundedRect(getX() - 1.0D, getY() - 1.0D, getWidth() + 2.0D, getHeight() + 2.0D, this.focusColor, (this.borderRadius + 1)); 
    DrawUtils.SHAPE.drawRoundedRect(getX(), getY(), getWidth(), getHeight(), this.backgroundColor, this.borderRadius);
    super.draw(mouseX, mouseY, ticks);
  }
  
  @NonNull
  public <T extends TextFieldNode> T backgroundColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.backgroundColor = color;
    return (T)this;
  }
  
  @NonNull
  public <T extends TextFieldNode> T focusColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.focusColor = color;
    return (T)this;
  }
  
  @NonNull
  public <T extends TextFieldNode> T borderRadius(int radius) {
    this.borderRadius = radius;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\textfield\TextFieldNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */