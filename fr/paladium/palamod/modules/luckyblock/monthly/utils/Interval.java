package fr.paladium.palamod.modules.luckyblock.monthly.utils;

public class Interval {
  private final int min;
  
  private final int max;
  
  public int getMin() {
    return this.min;
  }
  
  public int getMax() {
    return this.max;
  }
  
  public Interval(int min, int max) {
    this.min = Math.min(min, max);
    this.max = Math.max(max, min);
  }
  
  public boolean isInRange(int value) {
    return (value >= this.min && value <= this.max);
  }
  
  public int getRandom() {
    return (int)(Math.random() * (this.max - this.min + 1) + this.min);
  }
  
  public static Interval of(int min, int max) {
    return new Interval(min, max);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\Interval.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */