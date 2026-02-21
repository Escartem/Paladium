package fr.paladium.pet.server.skill.handler.impl.active.data;

public class PickaxeData {
  private double value;
  
  private long expirationMillis;
  
  public void setValue(double value) {
    this.value = value;
  }
  
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof PickaxeData))
      return false; 
    PickaxeData other = (PickaxeData)o;
    return !other.canEqual(this) ? false : ((Double.compare(getValue(), other.getValue()) != 0) ? false : (!(getExpirationMillis() != other.getExpirationMillis())));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof PickaxeData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $value = Double.doubleToLongBits(getValue());
    result = result * 59 + (int)($value >>> 32L ^ $value);
    long $expirationMillis = getExpirationMillis();
    return result * 59 + (int)($expirationMillis >>> 32L ^ $expirationMillis);
  }
  
  public String toString() {
    return "PickaxeData(value=" + getValue() + ", expirationMillis=" + getExpirationMillis() + ")";
  }
  
  public PickaxeData(double value, long expirationMillis) {
    this.value = value;
    this.expirationMillis = expirationMillis;
  }
  
  public double getValue() {
    return this.value;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\data\PickaxeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */