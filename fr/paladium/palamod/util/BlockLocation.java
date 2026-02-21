package fr.paladium.palamod.util;

public class BlockLocation {
  private int x;
  
  private int y;
  
  private int z;
  
  public BlockLocation(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
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
  
  public String toString() {
    return "LOCATION BLOCK: (X: " + this.x + ", Y: " + this.y + ", Z: " + this.z + " )";
  }
  
  public int hashCode() {
    return ("LOCATION BLOCK: (X: " + this.x + ", Y: " + this.y + ", Z: " + this.z + " )").hashCode();
  }
  
  public boolean equals(Object obj) {
    if (obj instanceof BlockLocation) {
      BlockLocation loc = (BlockLocation)obj;
      if (loc.x == this.x && loc.y == this.y && loc.z == this.z)
        return true; 
    } 
    return false;
  }
  
  public BlockLocation add(int x, int y, int z) {
    return new BlockLocation(this.x + x, this.y + y, this.z + z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\BlockLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */