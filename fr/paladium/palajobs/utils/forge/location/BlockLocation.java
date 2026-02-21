package fr.paladium.palajobs.utils.forge.location;

import net.minecraft.util.MathHelper;

public class BlockLocation {
  public final int x;
  
  public final int y;
  
  public final int z;
  
  public BlockLocation(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public BlockLocation(Location location) {
    this.x = (int)location.getX();
    this.y = (int)location.getY();
    this.z = (int)location.getZ();
  }
  
  public String toString() {
    return "BlockLocation [x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
  }
  
  public double getDistance(double x, double y, double z) {
    double disX = this.x - x;
    double disY = this.y - y;
    double disZ = this.z - z;
    return MathHelper.func_76133_a(disX * disX + disY * disY + disZ * disZ);
  }
  
  public double getDistance(BlockLocation blockLocation) {
    return getDistance(blockLocation.x, blockLocation.y, blockLocation.z);
  }
  
  public BlockLocation clone() {
    return new BlockLocation(this.x, this.y, this.z);
  }
  
  public int hashCode() {
    return this.x ^ this.y * 137 ^ this.z * 11317;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    BlockLocation other = (BlockLocation)obj;
    if (this.x != other.x)
      return false; 
    if (this.y != other.y)
      return false; 
    if (this.z != other.z)
      return false; 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\location\BlockLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */