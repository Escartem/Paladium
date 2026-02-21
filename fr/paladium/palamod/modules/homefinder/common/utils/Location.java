package fr.paladium.palamod.modules.homefinder.common.utils;

public class Location {
  private double x;
  
  private double y;
  
  private double z;
  
  public void setX(double x) {
    this.x = x;
  }
  
  public void setY(double y) {
    this.y = y;
  }
  
  public void setZ(double z) {
    this.z = z;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof Location))
      return false; 
    Location other = (Location)o;
    return !other.canEqual(this) ? false : ((Double.compare(getX(), other.getX()) != 0) ? false : ((Double.compare(getY(), other.getY()) != 0) ? false : (!(Double.compare(getZ(), other.getZ()) != 0))));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof Location;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $x = Double.doubleToLongBits(getX());
    result = result * 59 + (int)($x >>> 32L ^ $x);
    long $y = Double.doubleToLongBits(getY());
    result = result * 59 + (int)($y >>> 32L ^ $y);
    long $z = Double.doubleToLongBits(getZ());
    return result * 59 + (int)($z >>> 32L ^ $z);
  }
  
  public String toString() {
    return "Location(x=" + getX() + ", y=" + getY() + ", z=" + getZ() + ")";
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
  
  public Location(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public int getBlockX() {
    return (int)Math.floor(this.x);
  }
  
  public int getBlockY() {
    return (int)Math.floor(this.y);
  }
  
  public int getBlockZ() {
    return (int)Math.floor(this.z);
  }
  
  public double distanceTo(Location location) {
    return Math.sqrt(Math.pow(location.getX() - this.x, 2.0D) + Math.pow(location.getZ() - this.z, 2.0D));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\commo\\utils\Location.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */