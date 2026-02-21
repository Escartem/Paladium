package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderCursorNode;

final class VolumeSliderCursorNode extends SliderCursorNode {
  protected VolumeSliderCursorNode(double width, double height) {
    super(width, height);
  }
  
  public void drawCursor(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX(), getY() - 3.0D, getWidth(), getHeight(), Color.WHITE);
    DrawUtils.SHAPE.drawRect(getX(), getY() - 3.0D + getHeight(), getWidth(), 1.0D, Color.WHITE.darker());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\VolumeSliderNode$VolumeSliderCursorNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */