package fr.paladium.palamod.modules.back2future.entities.ai;

import com.google.common.base.Objects;
import net.minecraft.util.MathHelper;

public class Vec3i implements Comparable<Vec3i> {
  private final int x;
  
  private final int y;
  
  private final int z;
  
  public Vec3i(int xIn, int yIn, int zIn) {
    this.x = xIn;
    this.y = yIn;
    this.z = zIn;
  }
  
  public Vec3i(double xIn, double yIn, double zIn) {
    this(MathHelper.func_76128_c(xIn), MathHelper.func_76128_c(yIn), MathHelper.func_76128_c(zIn));
  }
  
  public boolean equals(Object p_equals_1_) {
    if (this == p_equals_1_)
      return true; 
    if (!(p_equals_1_ instanceof Vec3i))
      return false; 
    Vec3i vec3i = (Vec3i)p_equals_1_;
    return (getX() != vec3i.getX()) ? false : (
      (getY() != vec3i.getY()) ? false : ((getZ() == vec3i.getZ())));
  }
  
  public int hashCode() {
    return (getY() + getZ() * 31) * 31 + getX();
  }
  
  public int compareTo(Vec3i vec) {
    return (getY() == vec.getY()) ? ((getZ() == vec.getZ()) ? (getX() - vec.getX()) : (getZ() - vec.getZ())) : (
      getY() - vec.getY());
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
  
  public Vec3i crossProduct(Vec3i vec) {
    return new Vec3i(getY() * vec.getZ() - getZ() * vec.getY(), 
        getZ() * vec.getX() - getX() * vec.getZ(), getX() * vec.getY() - getY() * vec.getX());
  }
  
  public double distanceManhattan(double toX, double toY, double toZ) {
    return Math.abs(toX - getX()) + Math.abs(toY - getY()) + Math.abs(toZ - getZ());
  }
  
  public double distanceSq(double toX, double toY, double toZ) {
    double d3 = getX() - toX;
    double d4 = getY() - toY;
    double d5 = getZ() - toZ;
    return d3 * d3 + d4 * d4 + d5 * d5;
  }
  
  public double distanceSqToCenter(double xIn, double yIn, double zIn) {
    double d3 = getX() + 0.5D - xIn;
    double d4 = getY() + 0.5D - yIn;
    double d5 = getZ() + 0.5D - zIn;
    return d3 * d3 + d4 * d4 + d5 * d5;
  }
  
  public double distanceSq(Vec3i to) {
    return distanceSq(to.getX(), to.getY(), to.getZ());
  }
  
  public String toString() {
    return Objects.toStringHelper(this).add("x", getX()).add("y", getY()).add("z", getZ())
      .toString();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ai\Vec3i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */