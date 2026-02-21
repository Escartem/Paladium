package fr.paladium.palamod.modules.back2future.core.utils.math;

import com.google.common.base.MoreObjects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import javax.annotation.concurrent.Immutable;
import net.minecraft.dispenser.IPosition;
import net.minecraft.util.MathHelper;

@Immutable
public class Vector3i implements Comparable<Vector3i> {
  public static final Vector3i ZERO = new Vector3i(0, 0, 0);
  
  private int x;
  
  private int y;
  
  private int z;
  
  public Vector3i(int p_i46007_1_, int p_i46007_2_, int p_i46007_3_) {
    this.x = p_i46007_1_;
    this.y = p_i46007_2_;
    this.z = p_i46007_3_;
  }
  
  public Vector3i(double p_i46008_1_, double p_i46008_3_, double p_i46008_5_) {
    this(MathHelper.func_76128_c(p_i46008_1_), MathHelper.func_76128_c(p_i46008_3_), MathHelper.func_76128_c(p_i46008_5_));
  }
  
  public boolean equals(Object p_equals_1_) {
    if (this == p_equals_1_)
      return true; 
    if (!(p_equals_1_ instanceof Vector3i))
      return false; 
    Vector3i lvt_2_1_ = (Vector3i)p_equals_1_;
    if (getX() != lvt_2_1_.getX())
      return false; 
    if (getY() != lvt_2_1_.getY())
      return false; 
    if (getZ() != lvt_2_1_.getZ())
      return false; 
    return true;
  }
  
  public int hashCode() {
    return (getY() + getZ() * 31) * 31 + getX();
  }
  
  public int compareTo(Vector3i p_compareTo_1_) {
    if (getY() == p_compareTo_1_.getY()) {
      if (getZ() == p_compareTo_1_.getZ())
        return getX() - p_compareTo_1_.getX(); 
      return getZ() - p_compareTo_1_.getZ();
    } 
    return getY() - p_compareTo_1_.getY();
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
  
  protected void setX(int p_223471_1_) {
    this.x = p_223471_1_;
  }
  
  protected void setY(int p_185336_1_) {
    this.y = p_185336_1_;
  }
  
  protected void setZ(int p_223472_1_) {
    this.z = p_223472_1_;
  }
  
  public Vector3i cross(Vector3i p_177955_1_) {
    return new Vector3i(getY() * p_177955_1_.getZ() - getZ() * p_177955_1_.getY(), 
        getZ() * p_177955_1_.getX() - getX() * p_177955_1_.getZ(), 
        getX() * p_177955_1_.getY() - getY() * p_177955_1_.getX());
  }
  
  public boolean closerThan(Vector3i p_218141_1_, double p_218141_2_) {
    return (distSqr(p_218141_1_.getX(), p_218141_1_.getY(), p_218141_1_.getZ(), false) < p_218141_2_ * p_218141_2_);
  }
  
  public boolean closerThan(IPosition p_218137_1_, double p_218137_2_) {
    return (distSqr(p_218137_1_.func_82615_a(), p_218137_1_.func_82617_b(), p_218137_1_.func_82616_c(), true) < p_218137_2_ * p_218137_2_);
  }
  
  public double distSqr(Vector3i p_177951_1_) {
    return distSqr(p_177951_1_.getX(), p_177951_1_.getY(), p_177951_1_.getZ(), true);
  }
  
  public double distSqr(IPosition p_218138_1_, boolean p_218138_2_) {
    return distSqr(p_218138_1_.func_82615_a(), p_218138_1_.func_82617_b(), p_218138_1_.func_82616_c(), p_218138_2_);
  }
  
  public double distSqr(double p_218140_1_, double p_218140_3_, double p_218140_5_, boolean p_218140_7_) {
    double lvt_8_1_ = p_218140_7_ ? 0.5D : 0.0D;
    double lvt_10_1_ = getX() + lvt_8_1_ - p_218140_1_;
    double lvt_12_1_ = getY() + lvt_8_1_ - p_218140_3_;
    double lvt_14_1_ = getZ() + lvt_8_1_ - p_218140_5_;
    return lvt_10_1_ * lvt_10_1_ + lvt_12_1_ * lvt_12_1_ + lvt_14_1_ * lvt_14_1_;
  }
  
  public int distManhattan(Vector3i p_218139_1_) {
    float lvt_2_1_ = Math.abs(p_218139_1_.getX() - getX());
    float lvt_3_1_ = Math.abs(p_218139_1_.getY() - getY());
    float lvt_4_1_ = Math.abs(p_218139_1_.getZ() - getZ());
    return (int)(lvt_2_1_ + lvt_3_1_ + lvt_4_1_);
  }
  
  public String toString() {
    return MoreObjects.toStringHelper(this).add("x", getX()).add("y", getY()).add("z", getZ())
      .toString();
  }
  
  @SideOnly(Side.CLIENT)
  public String toShortString() {
    return "" + getX() + ", " + getY() + ", " + getZ();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\cor\\utils\math\Vector3i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */