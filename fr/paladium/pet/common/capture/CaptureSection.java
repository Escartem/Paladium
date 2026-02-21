package fr.paladium.pet.common.capture;

import fr.paladium.lib.apollon.utils.Color;

public class CaptureSection {
  private final int value;
  
  private final Color color;
  
  private final float percentage;
  
  public int getValue() {
    return this.value;
  }
  
  public Color getColor() {
    return this.color;
  }
  
  public float getPercentage() {
    return this.percentage;
  }
  
  public CaptureSection(int value, Color color, float percentage) {
    this.value = value;
    this.color = color;
    this.percentage = percentage;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\capture\CaptureSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */