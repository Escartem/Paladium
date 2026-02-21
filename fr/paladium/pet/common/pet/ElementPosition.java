package fr.paladium.pet.common.pet;

public class ElementPosition {
  private double x;
  
  private double y;
  
  private double z;
  
  public String toString() {
    return "ElementPosition(x=" + getX() + ", y=" + getY() + ", z=" + getZ() + ")";
  }
  
  public ElementPosition(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public double getZ() {
    return this.z;
  }
  
  public static ElementPosition of(double x, double y, double z) {
    return new ElementPosition(x, y, z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\pet\ElementPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */