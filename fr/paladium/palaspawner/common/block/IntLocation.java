package fr.paladium.palaspawner.common.block;

import java.util.Objects;
import net.minecraft.util.MathHelper;

public class IntLocation {
  private int x;
  
  private int y;
  
  private int z;
  
  public void setX(int x) {
    this.x = x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public void setZ(int z) {
    this.z = z;
  }
  
  protected IntLocation(int x, int y, int z) {
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
  
  public static IntLocation of(int x, int y, int z) {
    return new IntLocation(x, y, z);
  }
  
  public static IntLocation of(double x, double y, double z) {
    return new IntLocation(MathHelper.func_76128_c(x), MathHelper.func_76128_c(y), MathHelper.func_76128_c(z));
  }
  
  public boolean equals(Object obj) {
    if (obj instanceof IntLocation) {
      IntLocation other = (IntLocation)obj;
      return (this.x == other.x && this.y == other.y && this.z == other.z);
    } 
    return false;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.z) });
  }
  
  public double distanceSq(double toX, double toY, double toZ) {
    double distanceX = this.x - toX;
    double distanceY = this.y - toY;
    double distanceZ = this.z - toZ;
    return distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\block\IntLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */