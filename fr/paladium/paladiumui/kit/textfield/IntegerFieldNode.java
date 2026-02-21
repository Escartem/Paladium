package fr.paladium.paladiumui.kit.textfield;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.impl.IntegerFieldNode;
import lombok.NonNull;

public class IntegerFieldNode extends IntegerFieldNode {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 18.0F, new Color(1.0F, 1.0F, 1.0F, 0.5F));
  
  private Color backgroundColor = ColorConstant.GRAY;
  
  private Color borderColor = ColorConstant.GRAY;
  
  private Color focusColor = ColorConstant.GRAY.darker(0.2F);
  
  protected IntegerFieldNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    info(TEXT_INFO);
  }
  
  @NonNull
  public static IntegerFieldNode create(double x, double y, double width) {
    return new IntegerFieldNode(x, y, width, 46.0D);
  }
  
  @NonNull
  public static IntegerFieldNode create(double x, double y, double width, double height) {
    return new IntegerFieldNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), this.backgroundColor);
    DrawUtils.SHAPE.drawBorder(getX(), getY(), getX() + getWidth(), getY() + getHeight(), this.borderColor, 5.0D);
    if (isFocused())
      DrawUtils.SHAPE.drawBorder(getX(), getY(), getX() + getWidth(), getY() + getHeight(), this.focusColor, 5.0D); 
    super.draw(mouseX, mouseY, ticks);
  }
  
  @NonNull
  public <T extends IntegerFieldNode> T backgroundColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.backgroundColor = color;
    return (T)this;
  }
  
  @NonNull
  public <T extends IntegerFieldNode> T borderColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.borderColor = color;
    return (T)this;
  }
  
  @NonNull
  public <T extends IntegerFieldNode> T focusColor(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.focusColor = color;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\textfield\IntegerFieldNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */