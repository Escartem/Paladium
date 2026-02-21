package fr.paladium.paladiumui.kit.slider;

import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderCursorNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class SliderNode<T> extends SliderNode<T> {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 19.0F, Color.WHITE).shadow(new Color(0.0F, 0.0F, 0.0F, 0.4F)).shadow(0.0F, 2.0F);
  
  private String text;
  
  private double borderSize = 5.0D;
  
  protected SliderNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    cursor(new Cursor(16.0D, height + this.borderSize * 2.0D));
  }
  
  @NonNull
  public static <T> SliderNode<T> create(double x, double y, double width, double height) {
    return new SliderNode<>(x, y, width, height);
  }
  
  public void drawSlider(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), ColorConstant.GRAY);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), ColorConstant.GRAY);
    DrawUtils.SHAPE.drawRect(getX(), getY() - this.borderSize, getWidth(), this.borderSize, ColorConstant.GRAY);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight(), getWidth(), this.borderSize, ColorConstant.GRAY);
    DrawUtils.SHAPE.drawRect(getX() - this.borderSize, getY(), this.borderSize, getHeight(), ColorConstant.GRAY);
    DrawUtils.SHAPE.drawRect(getX() + getWidth(), getY(), this.borderSize, getHeight(), ColorConstant.GRAY);
  }
  
  @NonNull
  public <R extends SliderNode<T>> R text(String text) {
    this.text = text;
    return (R)this;
  }
  
  @NonNull
  public <R extends SliderNode<T>> R borderSize(double borderSize) {
    this.borderSize = borderSize;
    return (R)this;
  }
  
  @NonNull
  public <R extends SliderNode<T>> R cursor(double width) {
    cursor(new Cursor(width, getHeight() + this.borderSize * 2.0D));
    return (R)this;
  }
  
  private final class Cursor extends SliderCursorNode {
    protected Cursor(double width, double height) {
      super(width, height);
    }
    
    public void drawCursor(double mouseX, double mouseY, float ticks) {
      SliderNode<?> slider = (SliderNode)getParent();
      Color[] color = ButtonNode.DEFAULT_COLOR;
      float brightness = hoverValue(0.3F);
      DrawUtils.SHAPE.drawRect(getX(), getY() - slider.borderSize, getWidth(), getHeight(), color[0].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX(), getY() - slider.borderSize - slider.borderSize, getWidth(), slider.borderSize, color[1].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX(), getY() - slider.borderSize + getHeight(), getWidth(), slider.borderSize, color[2].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX() - slider.borderSize, getY() - slider.borderSize, slider.borderSize, getHeight(), color[3].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX() + getWidth(), getY() - slider.borderSize, slider.borderSize, getHeight(), color[3].brighter(brightness));
      if (slider.text != null)
        DrawUtils.TEXT.drawText(slider.dw(2.0D), slider.dh(2.0D) - SliderNode.TEXT_INFO.dh(2.0D), Text.create(slider.text.replace("%value%", slider.getValue().toString()), SliderNode.TEXT_INFO, Align.CENTER)); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\slider\SliderNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */