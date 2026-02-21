package fr.paladium.palavoicechat.utils;

import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;

public class Vec3d {
  public static final Vec3d ZERO = new Vec3d(0.0D, 0.0D, 0.0D);
  
  public final double x;
  
  public final double y;
  
  public final double z;
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public double getZ() {
    return this.z;
  }
  
  public Vec3d(double xIn, double yIn, double zIn) {
    this.x = xIn;
    this.y = yIn;
    this.z = zIn;
  }
  
  public Vec3d(Vector3f vec3f) {
    this(vec3f.getX(), vec3f.getY(), vec3f.getZ());
  }
  
  public Vec3d subtractReverse(Vec3d vec) {
    return new Vec3d(vec.x - this.x, vec.y - this.y, vec.z - this.z);
  }
  
  public Vec3d normalize() {
    double d0 = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    return (d0 < 1.0E-4D) ? ZERO : new Vec3d(this.x / d0, this.y / d0, this.z / d0);
  }
  
  public double dotProduct(Vec3d vec) {
    return this.x * vec.x + this.y * vec.y + this.z * vec.z;
  }
  
  public Vec3d crossProduct(Vec3d vec) {
    return new Vec3d(this.y * vec.z - this.z * vec.y, this.z * vec.x - this.x * vec.z, this.x * vec.y - this.y * vec.x);
  }
  
  public Vec3d subtract(Vec3d vec) {
    return subtract(vec.x, vec.y, vec.z);
  }
  
  public Vec3d subtract(double x, double y, double z) {
    return add(-x, -y, -z);
  }
  
  public Vec3d add(Vec3d vec) {
    return add(vec.x, vec.y, vec.z);
  }
  
  public Vec3d add(double x, double y, double z) {
    return new Vec3d(this.x + x, this.y + y, this.z + z);
  }
  
  public double distanceTo(Vec3d vec) {
    double d0 = vec.x - this.x;
    double d1 = vec.y - this.y;
    double d2 = vec.z - this.z;
    return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
  }
  
  public double squareDistanceTo(Vec3d vec) {
    double d0 = vec.x - this.x;
    double d1 = vec.y - this.y;
    double d2 = vec.z - this.z;
    return d0 * d0 + d1 * d1 + d2 * d2;
  }
  
  public double squareDistanceTo(double xIn, double yIn, double zIn) {
    double d0 = xIn - this.x;
    double d1 = yIn - this.y;
    double d2 = zIn - this.z;
    return d0 * d0 + d1 * d1 + d2 * d2;
  }
  
  public Vec3d scale(double factor) {
    return mul(factor, factor, factor);
  }
  
  public Vec3d inverse() {
    return scale(-1.0D);
  }
  
  public Vec3d mul(Vec3d p_216369_1_) {
    return mul(p_216369_1_.x, p_216369_1_.y, p_216369_1_.z);
  }
  
  public Vec3d mul(double factorX, double factorY, double factorZ) {
    return new Vec3d(this.x * factorX, this.y * factorY, this.z * factorZ);
  }
  
  public double length() {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
  }
  
  public double lengthSquared() {
    return this.x * this.x + this.y * this.y + this.z * this.z;
  }
  
  public boolean equals(Object p_equals_1_) {
    if (this == p_equals_1_)
      return true; 
    if (!(p_equals_1_ instanceof Vec3d))
      return false; 
    Vec3d vec3d = (Vec3d)p_equals_1_;
    if (Double.compare(vec3d.x, this.x) != 0)
      return false; 
    if (Double.compare(vec3d.y, this.y) != 0)
      return false; 
    return (Double.compare(vec3d.z, this.z) == 0);
  }
  
  public int hashCode() {
    long j = Double.doubleToLongBits(this.x);
    int i = (int)(j ^ j >>> 32L);
    j = Double.doubleToLongBits(this.y);
    i = 31 * i + (int)(j ^ j >>> 32L);
    j = Double.doubleToLongBits(this.z);
    i = 31 * i + (int)(j ^ j >>> 32L);
    return i;
  }
  
  public String toString() {
    return "(" + this.x + ", " + this.y + ", " + this.z + ")";
  }
  
  public Vec3d rotatePitch(float pitch) {
    float f = MathHelper.func_76134_b(pitch);
    float f1 = MathHelper.func_76126_a(pitch);
    double d0 = this.x;
    double d1 = this.y * f + this.z * f1;
    double d2 = this.z * f - this.y * f1;
    return new Vec3d(d0, d1, d2);
  }
  
  public Vec3d rotateYaw(float yaw) {
    float f = MathHelper.func_76134_b(yaw);
    float f1 = MathHelper.func_76126_a(yaw);
    double d0 = this.x * f + this.z * f1;
    double d1 = this.y;
    double d2 = this.z * f - this.x * f1;
    return new Vec3d(d0, d1, d2);
  }
  
  public static Vec3d fromPitchYaw(Vec2f vec2f) {
    return fromPitchYaw(vec2f.getX(), vec2f.getY());
  }
  
  public static Vec3d fromPitchYaw(float pitch, float yaw) {
    float f = MathHelper.func_76134_b(-yaw * 0.017453292F - 3.1415927F);
    float f1 = MathHelper.func_76126_a(-yaw * 0.017453292F - 3.1415927F);
    float f2 = -MathHelper.func_76134_b(-pitch * 0.017453292F);
    float f3 = MathHelper.func_76126_a(-pitch * 0.017453292F);
    return new Vec3d((f1 * f2), f3, (f * f2));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicecha\\utils\Vec3d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */