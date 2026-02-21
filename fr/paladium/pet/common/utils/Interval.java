package fr.paladium.pet.common.utils;

public class Interval {
  private float min;
  
  private float max;
  
  public void setMin(float min) {
    this.min = min;
  }
  
  public void setMax(float max) {
    this.max = max;
  }
  
  public float getMin() {
    return this.min;
  }
  
  public float getMax() {
    return this.max;
  }
  
  public Interval(float min, float max) {
    this.min = Math.min(min, max);
    this.max = Math.max(max, min);
  }
  
  public static Interval of(float min, float max) {
    return new Interval(min, max);
  }
  
  public boolean isInRange(float value) {
    return (value >= this.min && value <= this.max);
  }
  
  public float getRandom() {
    return (float)(Math.random() * (this.max - this.min + 1.0F) + this.min);
  }
  
  public boolean isInferiorOrEquals(float target) {
    float generated = getRandom();
    return (generated <= target);
  }
  
  public Interval multiply(float value) {
    this.min *= value;
    this.max *= value;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\commo\\utils\Interval.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */