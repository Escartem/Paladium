package fr.paladium.palarpg.client.ui.kit.separator;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import javax.vecmath.Vector4f;
import lombok.NonNull;

public class GradientSeparatorNode extends Node {
  private boolean vertical;
  
  private Color startColor;
  
  private Color endColor;
  
  public boolean isVertical() {
    return this.vertical;
  }
  
  public Color getStartColor() {
    return this.startColor;
  }
  
  public Color getEndColor() {
    return this.endColor;
  }
  
  protected GradientSeparatorNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static GradientSeparatorNode horizontal(double x, double y, double width, double height) {
    return (new GradientSeparatorNode(x, y, width, height)).horizontal();
  }
  
  @NonNull
  public static GradientSeparatorNode vertical(double x, double y, double width, double height) {
    return (new GradientSeparatorNode(x, y, width, height)).vertical();
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.vertical) {
      RectNode.create(0.0D, 0.0D, getWidth(), dh(2.0D))
        .color(this.endColor.toGradient(this.startColor, new Vector4f(0.5F, 1.0F, 0.5F, 0.0F)))
        .attach(this);
      RectNode.create(0.0D, dh(2.0D), getWidth(), dh(2.0D))
        .color(this.startColor.toGradient(this.endColor, new Vector4f(0.5F, 1.0F, 0.5F, 0.5F)))
        .attach(this);
    } else {
      RectNode.create(0.0D, 0.0D, dw(2.0D), getHeight())
        .color(this.endColor.toGradient(this.startColor, new Vector4f(0.0F, 0.5F, 1.0F, 0.5F)))
        .attach(this);
      RectNode.create(dw(2.0D), 0.0D, dw(2.0D), getHeight())
        .color(this.startColor.toGradient(this.endColor, new Vector4f(0.0F, 0.5F, 0.5F, 0.5F)))
        .attach(this);
    } 
  }
  
  @NonNull
  public final GradientSeparatorNode vertical() {
    this.vertical = true;
    return this;
  }
  
  @NonNull
  public final GradientSeparatorNode horizontal() {
    this.vertical = false;
    return this;
  }
  
  @NonNull
  public final GradientSeparatorNode startColor(@NonNull Color startColor) {
    if (startColor == null)
      throw new NullPointerException("startColor is marked non-null but is null"); 
    this.startColor = startColor;
    return this;
  }
  
  @NonNull
  public final GradientSeparatorNode endColor(@NonNull Color endColor) {
    if (endColor == null)
      throw new NullPointerException("endColor is marked non-null but is null"); 
    this.endColor = endColor;
    return this;
  }
  
  @NonNull
  public final GradientSeparatorNode color(@NonNull Color startColor, @NonNull Color endColor) {
    if (startColor == null)
      throw new NullPointerException("startColor is marked non-null but is null"); 
    if (endColor == null)
      throw new NullPointerException("endColor is marked non-null but is null"); 
    this.startColor = startColor;
    this.endColor = endColor;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\clien\\ui\kit\separator\GradientSeparatorNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */