package fr.paladium.pet.server.config.global;

public class HappinessValue {
  private int minLevel;
  
  private int maxLevel;
  
  private int maxHappiness;
  
  public void setMinLevel(int minLevel) {
    this.minLevel = minLevel;
  }
  
  public void setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
  }
  
  public void setMaxHappiness(int maxHappiness) {
    this.maxHappiness = maxHappiness;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof HappinessValue))
      return false; 
    HappinessValue other = (HappinessValue)o;
    return !other.canEqual(this) ? false : ((getMinLevel() != other.getMinLevel()) ? false : ((getMaxLevel() != other.getMaxLevel()) ? false : (!(getMaxHappiness() != other.getMaxHappiness()))));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof HappinessValue;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getMinLevel();
    result = result * 59 + getMaxLevel();
    return result * 59 + getMaxHappiness();
  }
  
  public String toString() {
    return "HappinessValue(minLevel=" + getMinLevel() + ", maxLevel=" + getMaxLevel() + ", maxHappiness=" + getMaxHappiness() + ")";
  }
  
  public HappinessValue() {}
  
  public HappinessValue(int minLevel, int maxLevel, int maxHappiness) {
    this.minLevel = minLevel;
    this.maxLevel = maxLevel;
    this.maxHappiness = maxHappiness;
  }
  
  public int getMinLevel() {
    return this.minLevel;
  }
  
  public int getMaxLevel() {
    return this.maxLevel;
  }
  
  public int getMaxHappiness() {
    return this.maxHappiness;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\config\global\HappinessValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */