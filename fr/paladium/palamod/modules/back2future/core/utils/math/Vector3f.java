package fr.paladium.palamod.modules.back2future.core.utils.math;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;

public final class Vector3f {
  public static Vector3f XN = new Vector3f(-1.0F, 0.0F, 0.0F);
  
  public static Vector3f XP = new Vector3f(1.0F, 0.0F, 0.0F);
  
  public static Vector3f YN = new Vector3f(0.0F, -1.0F, 0.0F);
  
  public static Vector3f YP = new Vector3f(0.0F, 1.0F, 0.0F);
  
  public static Vector3f ZN = new Vector3f(0.0F, 0.0F, -1.0F);
  
  public static Vector3f ZP = new Vector3f(0.0F, 0.0F, 1.0F);
  
  private float x;
  
  private float y;
  
  private float z;
  
  public Vector3f() {}
  
  public Vector3f(float p_i48098_1_, float p_i48098_2_, float p_i48098_3_) {
    this.x = p_i48098_1_;
    this.y = p_i48098_2_;
    this.z = p_i48098_3_;
  }
  
  public Vector3f(Vector3d p_i51412_1_) {
    this((float)p_i51412_1_.x, (float)p_i51412_1_.y, (float)p_i51412_1_.z);
  }
  
  public boolean equals(Object p_equals_1_) {
    if (this == p_equals_1_)
      return true; 
    if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
      Vector3f vector3f = (Vector3f)p_equals_1_;
      if (Float.compare(vector3f.x, this.x) != 0)
        return false; 
      if (Float.compare(vector3f.y, this.y) != 0)
        return false; 
      return (Float.compare(vector3f.z, this.z) == 0);
    } 
    return false;
  }
  
  public int hashCode() {
    int i = Float.floatToIntBits(this.x);
    i = 31 * i + Float.floatToIntBits(this.y);
    return 31 * i + Float.floatToIntBits(this.z);
  }
  
  public float x() {
    return this.x;
  }
  
  public float y() {
    return this.y;
  }
  
  public float z() {
    return this.z;
  }
  
  @SideOnly(Side.CLIENT)
  public void mul(float p_195898_1_) {
    this.x *= p_195898_1_;
    this.y *= p_195898_1_;
    this.z *= p_195898_1_;
  }
  
  @SideOnly(Side.CLIENT)
  public void mul(float p_229192_1_, float p_229192_2_, float p_229192_3_) {
    this.x *= p_229192_1_;
    this.y *= p_229192_2_;
    this.z *= p_229192_3_;
  }
  
  @SideOnly(Side.CLIENT)
  public void clamp(float p_195901_1_, float p_195901_2_) {
    this.x = MathHelper.func_76131_a(this.x, p_195901_1_, p_195901_2_);
    this.y = MathHelper.func_76131_a(this.y, p_195901_1_, p_195901_2_);
    this.z = MathHelper.func_76131_a(this.z, p_195901_1_, p_195901_2_);
  }
  
  public void set(float p_195905_1_, float p_195905_2_, float p_195905_3_) {
    this.x = p_195905_1_;
    this.y = p_195905_2_;
    this.z = p_195905_3_;
  }
  
  @SideOnly(Side.CLIENT)
  public void add(float p_195904_1_, float p_195904_2_, float p_195904_3_) {
    this.x += p_195904_1_;
    this.y += p_195904_2_;
    this.z += p_195904_3_;
  }
  
  @SideOnly(Side.CLIENT)
  public void add(Vector3f p_229189_1_) {
    this.x += p_229189_1_.x;
    this.y += p_229189_1_.y;
    this.z += p_229189_1_.z;
  }
  
  @SideOnly(Side.CLIENT)
  public void sub(Vector3f p_195897_1_) {
    this.x -= p_195897_1_.x;
    this.y -= p_195897_1_.y;
    this.z -= p_195897_1_.z;
  }
  
  @SideOnly(Side.CLIENT)
  public float dot(Vector3f p_195903_1_) {
    return this.x * p_195903_1_.x + this.y * p_195903_1_.y + this.z * p_195903_1_.z;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean normalize() {
    float f = this.x * this.x + this.y * this.y + this.z * this.z;
    if (f < 1.0E-5D)
      return false; 
    float f1 = fastInvSqrt(f);
    this.x *= f1;
    this.y *= f1;
    this.z *= f1;
    return true;
  }
  
  public static float fastInvSqrt(float p_226165_0_) {
    float lvt_1_1_ = 0.5F * p_226165_0_;
    int lvt_2_1_ = Float.floatToIntBits(p_226165_0_);
    lvt_2_1_ = 1597463007 - (lvt_2_1_ >> 1);
    p_226165_0_ = Float.intBitsToFloat(lvt_2_1_);
    return 1.5F - lvt_1_1_ * p_226165_0_ * p_226165_0_;
  }
  
  @SideOnly(Side.CLIENT)
  public void cross(Vector3f p_195896_1_) {
    float f = this.x;
    float f1 = this.y;
    float f2 = this.z;
    float f3 = p_195896_1_.x();
    float f4 = p_195896_1_.y();
    float f5 = p_195896_1_.z();
    this.x = f1 * f5 - f2 * f4;
    this.y = f2 * f3 - f * f5;
    this.z = f * f4 - f1 * f3;
  }
  
  @SideOnly(Side.CLIENT)
  public void transform(Matrix3f p_229188_1_) {
    float f = this.x;
    float f1 = this.y;
    float f2 = this.z;
    this.x = p_229188_1_.m00 * f + p_229188_1_.m01 * f1 + p_229188_1_.m02 * f2;
    this.y = p_229188_1_.m10 * f + p_229188_1_.m11 * f1 + p_229188_1_.m12 * f2;
    this.z = p_229188_1_.m20 * f + p_229188_1_.m21 * f1 + p_229188_1_.m22 * f2;
  }
  
  public void transform(Quaternion p_214905_1_) {
    Quaternion quaternion = new Quaternion(p_214905_1_);
    quaternion.mul(new Quaternion(x(), y(), z(), 0.0F));
    Quaternion quaternion1 = new Quaternion(p_214905_1_);
    quaternion1.conj();
    quaternion.mul(quaternion1);
    set(quaternion.i(), quaternion.j(), quaternion.k());
  }
  
  @SideOnly(Side.CLIENT)
  public void lerp(Vector3f p_229190_1_, float p_229190_2_) {
    float f = 1.0F - p_229190_2_;
    this.x = this.x * f + p_229190_1_.x * p_229190_2_;
    this.y = this.y * f + p_229190_1_.y * p_229190_2_;
    this.z = this.z * f + p_229190_1_.z * p_229190_2_;
  }
  
  @SideOnly(Side.CLIENT)
  public Quaternion rotation(float p_229193_1_) {
    return new Quaternion(this, p_229193_1_, false);
  }
  
  @SideOnly(Side.CLIENT)
  public Quaternion rotationDegrees(float p_229187_1_) {
    return new Quaternion(this, p_229187_1_, true);
  }
  
  @SideOnly(Side.CLIENT)
  public Vector3f copy() {
    return new Vector3f(this.x, this.y, this.z);
  }
  
  public String toString() {
    return "[" + this.x + ", " + this.y + ", " + this.z + "]";
  }
  
  public Vector3f(float[] p_i244838_1_) {
    set(p_i244838_1_);
  }
  
  public void set(float[] p_set_1_) {
    this.x = p_set_1_[0];
    this.y = p_set_1_[1];
    this.z = p_set_1_[2];
  }
  
  public void setX(float p_setX_1_) {
    this.x = p_setX_1_;
  }
  
  public void setY(float p_setY_1_) {
    this.y = p_setY_1_;
  }
  
  public void setZ(float p_setZ_1_) {
    this.z = p_setZ_1_;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\cor\\utils\math\Vector3f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */