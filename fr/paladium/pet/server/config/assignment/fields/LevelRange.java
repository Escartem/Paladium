package fr.paladium.pet.server.config.assignment.fields;

public class LevelRange {
  private int min;
  
  private int max;
  
  public LevelRange() {}
  
  public LevelRange(int min, int max) {
    this.min = min;
    this.max = max;
  }
  
  public int getMin() {
    return this.min;
  }
  
  public int getMax() {
    return this.max;
  }
  
  public boolean isInRange(int value) {
    return (value >= this.min && value <= this.max);
  }
  
  public boolean equals(Object obj) {
    if (!(obj instanceof LevelRange))
      return false; 
    LevelRange range = (LevelRange)obj;
    return (range.min == this.min && range.max == this.max);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\config\assignment\fields\LevelRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */