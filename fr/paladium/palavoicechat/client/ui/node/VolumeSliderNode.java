package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderCursorNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderNode;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class VolumeSliderNode extends SliderNode<Float> {
  private float maxValue = 2000.0F;
  
  protected VolumeSliderNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    Set<Float> values = new LinkedHashSet<>();
    float i;
    for (i = 0.0F; i <= 2000.0F; i++)
      values.add(Float.valueOf(i)); 
    valueSet(values, Float.valueOf(1000.0F));
    cursor(new VolumeSliderCursorNode(4.0D, 10.0D));
  }
  
  public static VolumeSliderNode create(double x, double y, double width, double height) {
    return new VolumeSliderNode(x, y, width, height);
  }
  
  public void drawSlider(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), Color.GRAY);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth() * ((Float)getValue()).floatValue() / this.maxValue, getHeight(), Color.RED);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight(), getWidth(), 1.0D, Color.GRAY.darker());
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight(), getWidth() * ((Float)getValue()).floatValue() / this.maxValue, 1.0D, Color.RED.darker());
  }
  
  public VolumeSliderNode maxValue(float maxValue) {
    this.maxValue = maxValue;
    return this;
  }
  
  public VolumeSliderNode value(float value) {
    float lastDiff = Float.MAX_VALUE;
    for (Iterator<Float> iterator = getValueSet().iterator(); iterator.hasNext(); ) {
      float v = ((Float)iterator.next()).floatValue();
      float diff = Math.abs(v - value);
      if (diff < lastDiff) {
        value(Float.valueOf(v));
        lastDiff = diff;
      } 
    } 
    return this;
  }
  
  private final class VolumeSliderCursorNode extends SliderCursorNode {
    protected VolumeSliderCursorNode(double width, double height) {
      super(width, height);
    }
    
    public void drawCursor(double mouseX, double mouseY, float ticks) {
      DrawUtils.SHAPE.drawRect(getX(), getY() - 3.0D, getWidth(), getHeight(), Color.WHITE);
      DrawUtils.SHAPE.drawRect(getX(), getY() - 3.0D + getHeight(), getWidth(), 1.0D, Color.WHITE.darker());
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\VolumeSliderNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */