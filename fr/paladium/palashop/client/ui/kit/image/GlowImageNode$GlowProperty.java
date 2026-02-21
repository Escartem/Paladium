package fr.paladium.palashop.client.ui.kit.image;

import lombok.NonNull;

public class GlowProperty {
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


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\image\GlowImageNode$GlowProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */