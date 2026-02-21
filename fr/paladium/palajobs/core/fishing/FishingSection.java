package fr.paladium.palajobs.core.fishing;

import fr.paladium.lib.apollon.utils.Color;

public class FishingSection {
  private final FishingSectionType type;
  
  private final Object value;
  
  private final Color color;
  
  private final float percentage;
  
  public FishingSectionType getType() {
    return this.type;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public Color getColor() {
    return this.color;
  }
  
  public float getPercentage() {
    return this.percentage;
  }
  
  public FishingSection(FishingSectionType type, Object value, Color color, float percentage) {
    this.type = type;
    this.value = value;
    this.color = color;
    this.percentage = percentage;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\fishing\FishingSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */