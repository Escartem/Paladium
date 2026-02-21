package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderCursorNode;

final class MicrophoneActivationCursorNode extends SliderCursorNode {
  protected MicrophoneActivationCursorNode(double width, double height) {
    super(width, height);
  }
  
  public void drawCursor(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX() - dw(2.0D), getY() - 4.0D, getWidth(), getHeight(), Color.WHITE);
    DrawUtils.SHAPE.drawRect(getX() - dw(2.0D), getY() - 4.0D + getHeight(), getWidth(), 1.0D, Color.WHITE.darker());
  }
  
  public boolean isHovered(double mouseX, double mouseY, boolean checkEnabled) {
    if (!((Boolean)MicrophoneActivationNode.access$000(MicrophoneActivationNode.this).get()).booleanValue())
      return false; 
    return super.isHovered(mouseX, mouseY, checkEnabled);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\MicrophoneActivationNode$MicrophoneActivationCursorNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */