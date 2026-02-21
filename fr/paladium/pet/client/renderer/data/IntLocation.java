package fr.paladium.pet.client.renderer.data;

import java.util.Objects;

public class IntLocation {
  private final int x;
  
  private final int y;
  
  private final int z;
  
  public String toString() {
    return "IntLocation(x=" + getX() + ", y=" + getY() + ", z=" + getZ() + ")";
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public IntLocation(double x, double y, double z) {
    this.x = (int)Math.floor(x);
    this.y = (int)Math.floor(y);
    this.z = (int)Math.floor(z);
  }
  
  public boolean equals(Object obj) {
    if (!(obj instanceof IntLocation))
      return false; 
    IntLocation other = (IntLocation)obj;
    return (other.x == this.x && other.y == this.y && other.z == this.z);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.z) });
  }
  
  public int distance(double x, double y, double z) {
    return distance(new IntLocation(x, y, z));
  }
  
  public int distance(IntLocation other) {
    return (int)Math.sqrt(Math.pow((other.x - this.x), 2.0D) + Math.pow((other.y - this.y), 2.0D) + Math.pow((other.z - this.z), 2.0D));
  }
  
  public static IntLocation of(double x, double y, double z) {
    return new IntLocation(x, y, z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\renderer\data\IntLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */