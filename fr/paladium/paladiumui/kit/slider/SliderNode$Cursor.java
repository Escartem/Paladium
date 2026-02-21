package fr.paladium.paladiumui.kit.slider;

import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderCursorNode;
import fr.paladium.zephyrui.lib.utils.align.Align;

final class Cursor extends SliderCursorNode {
  protected Cursor(double width, double height) {
    super(width, height);
  }
  
  public void drawCursor(double mouseX, double mouseY, float ticks) {
    SliderNode<?> slider = (SliderNode)getParent();
    Color[] color = ButtonNode.DEFAULT_COLOR;
    float brightness = hoverValue(0.3F);
    DrawUtils.SHAPE.drawRect(getX(), getY() - SliderNode.access$000(slider), getWidth(), getHeight(), color[0].brighter(brightness));
    DrawUtils.SHAPE.drawRect(getX(), getY() - SliderNode.access$000(slider) - SliderNode.access$000(slider), getWidth(), SliderNode.access$000(slider), color[1].brighter(brightness));
    DrawUtils.SHAPE.drawRect(getX(), getY() - SliderNode.access$000(slider) + getHeight(), getWidth(), SliderNode.access$000(slider), color[2].brighter(brightness));
    DrawUtils.SHAPE.drawRect(getX() - SliderNode.access$000(slider), getY() - SliderNode.access$000(slider), SliderNode.access$000(slider), getHeight(), color[3].brighter(brightness));
    DrawUtils.SHAPE.drawRect(getX() + getWidth(), getY() - SliderNode.access$000(slider), SliderNode.access$000(slider), getHeight(), color[3].brighter(brightness));
    if (SliderNode.access$100(slider) != null)
      DrawUtils.TEXT.drawText(slider.dw(2.0D), slider.dh(2.0D) - SliderNode.access$200().dh(2.0D), Text.create(SliderNode.access$100(slider).replace("%value%", slider.getValue().toString()), SliderNode.access$200(), Align.CENTER)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\slider\SliderNode$Cursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */