package fr.paladium.palashop.client.ui.kit.image;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import lombok.NonNull;

public class GlowImageNode extends ImageNode {
  private Color glowColor;
  
  private GlowProperty glowProperty;
  
  public Color getGlowColor() {
    return this.glowColor;
  }
  
  public GlowProperty getGlowProperty() {
    return this.glowProperty;
  }
  
  protected GlowImageNode(double x, double y) {
    this(x, y, 0.0D, 0.0D);
  }
  
  protected GlowImageNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static GlowImageNode create(double x, double y) {
    return new GlowImageNode(x, y);
  }
  
  @NonNull
  public static GlowImageNode create(double x, double y, double width, double height) {
    return new GlowImageNode(x, y, width, height);
  }
  
  @NonNull
  public final <T extends ImageNode> T glow(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.glowColor = color;
    if (this.glowProperty == null)
      this.glowProperty = GlowProperty.create(); 
    return (T)this;
  }
  
  @NonNull
  public final <T extends ImageNode> T glow(@NonNull Color color, @NonNull GlowProperty property) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    if (property == null)
      throw new NullPointerException("property is marked non-null but is null"); 
    this.glowColor = color;
    this.glowProperty = property;
    return (T)this;
  }
  
  @NonNull
  public final <T extends ImageNode> T glowProperty(@NonNull GlowProperty property) {
    if (property == null)
      throw new NullPointerException("property is marked non-null but is null"); 
    this.glowProperty = property;
    return (T)this;
  }
  
  public static class GlowProperty {
    private float opacity;
    
    private float strength;
    
    private GlowProperty(float opacity, float strength) {
      this.opacity = opacity;
      this.strength = strength;
    }
    
    public float getOpacity() {
      return this.opacity;
    }
    
    public float getStrength() {
      return this.strength;
    }
    
    @NonNull
    public static GlowProperty create() {
      return new GlowProperty(0.25F, 1.4F);
    }
    
    @NonNull
    public static GlowProperty create(float opacity, float strength) {
      return new GlowProperty(opacity, strength);
    }
    
    @NonNull
    public GlowProperty opacity(float opacity) {
      this.opacity = opacity;
      return this;
    }
    
    @NonNull
    public GlowProperty strength(float strength) {
      this.strength = strength;
      return this;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\image\GlowImageNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */