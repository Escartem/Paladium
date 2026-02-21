package fr.paladium.palamod.modules.back2future.core.utils.math;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class Matrix3f {
  private static final float G = 3.0F + 2.0F * (float)Math.sqrt(2.0D);
  
  private static final float CS = (float)Math.cos(0.39269908169872414D);
  
  private static final float SS = (float)Math.sin(0.39269908169872414D);
  
  private static final float SQ2 = 1.0F / (float)Math.sqrt(2.0D);
  
  protected float m00;
  
  protected float m01;
  
  protected float m02;
  
  protected float m10;
  
  protected float m11;
  
  protected float m12;
  
  protected float m20;
  
  protected float m21;
  
  protected float m22;
  
  public Matrix3f() {}
  
  public Matrix3f(Quaternion p_i225696_1_) {
    float f = p_i225696_1_.i();
    float f1 = p_i225696_1_.j();
    float f2 = p_i225696_1_.k();
    float f3 = p_i225696_1_.r();
    float f4 = 2.0F * f * f;
    float f5 = 2.0F * f1 * f1;
    float f6 = 2.0F * f2 * f2;
    this.m00 = 1.0F - f5 - f6;
    this.m11 = 1.0F - f6 - f4;
    this.m22 = 1.0F - f4 - f5;
    float f7 = f * f1;
    float f8 = f1 * f2;
    float f9 = f2 * f;
    float f10 = f * f3;
    float f11 = f1 * f3;
    float f12 = f2 * f3;
    this.m10 = 2.0F * (f7 + f12);
    this.m01 = 2.0F * (f7 - f12);
    this.m20 = 2.0F * (f9 - f11);
    this.m02 = 2.0F * (f9 + f11);
    this.m21 = 2.0F * (f8 + f10);
    this.m12 = 2.0F * (f8 - f10);
  }
  
  @SideOnly(Side.CLIENT)
  public static Matrix3f createScaleMatrix(float p_226117_0_, float p_226117_1_, float p_226117_2_) {
    Matrix3f matrix3f = new Matrix3f();
    matrix3f.m00 = p_226117_0_;
    matrix3f.m11 = p_226117_1_;
    matrix3f.m22 = p_226117_2_;
    return matrix3f;
  }
  
  public Matrix3f(Matrix4f p_i225695_1_) {
    this.m00 = p_i225695_1_.m00;
    this.m01 = p_i225695_1_.m01;
    this.m02 = p_i225695_1_.m02;
    this.m10 = p_i225695_1_.m10;
    this.m11 = p_i225695_1_.m11;
    this.m12 = p_i225695_1_.m12;
    this.m20 = p_i225695_1_.m20;
    this.m21 = p_i225695_1_.m21;
    this.m22 = p_i225695_1_.m22;
  }
  
  public Matrix3f(Matrix3f p_i225694_1_) {
    this.m00 = p_i225694_1_.m00;
    this.m01 = p_i225694_1_.m01;
    this.m02 = p_i225694_1_.m02;
    this.m10 = p_i225694_1_.m10;
    this.m11 = p_i225694_1_.m11;
    this.m12 = p_i225694_1_.m12;
    this.m20 = p_i225694_1_.m20;
    this.m21 = p_i225694_1_.m21;
    this.m22 = p_i225694_1_.m22;
  }
  
  @SideOnly(Side.CLIENT)
  public void transpose() {
    float f = this.m01;
    this.m01 = this.m10;
    this.m10 = f;
    f = this.m02;
    this.m02 = this.m20;
    this.m20 = f;
    f = this.m12;
    this.m12 = this.m21;
    this.m21 = f;
  }
  
  public boolean equals(Object p_equals_1_) {
    if (this == p_equals_1_)
      return true; 
    if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
      Matrix3f matrix3f = (Matrix3f)p_equals_1_;
      return (Float.compare(matrix3f.m00, this.m00) == 0 && Float.compare(matrix3f.m01, this.m01) == 0 && Float.compare(matrix3f.m02, this.m02) == 0 && Float.compare(matrix3f.m10, this.m10) == 0 && Float.compare(matrix3f.m11, this.m11) == 0 && Float.compare(matrix3f.m12, this.m12) == 0 && Float.compare(matrix3f.m20, this.m20) == 0 && Float.compare(matrix3f.m21, this.m21) == 0 && Float.compare(matrix3f.m22, this.m22) == 0);
    } 
    return false;
  }
  
  public int hashCode() {
    int i = (this.m00 != 0.0F) ? Float.floatToIntBits(this.m00) : 0;
    i = 31 * i + ((this.m01 != 0.0F) ? Float.floatToIntBits(this.m01) : 0);
    i = 31 * i + ((this.m02 != 0.0F) ? Float.floatToIntBits(this.m02) : 0);
    i = 31 * i + ((this.m10 != 0.0F) ? Float.floatToIntBits(this.m10) : 0);
    i = 31 * i + ((this.m11 != 0.0F) ? Float.floatToIntBits(this.m11) : 0);
    i = 31 * i + ((this.m12 != 0.0F) ? Float.floatToIntBits(this.m12) : 0);
    i = 31 * i + ((this.m20 != 0.0F) ? Float.floatToIntBits(this.m20) : 0);
    i = 31 * i + ((this.m21 != 0.0F) ? Float.floatToIntBits(this.m21) : 0);
    return 31 * i + ((this.m22 != 0.0F) ? Float.floatToIntBits(this.m22) : 0);
  }
  
  @SideOnly(Side.CLIENT)
  public void load(Matrix3f p_226114_1_) {
    this.m00 = p_226114_1_.m00;
    this.m01 = p_226114_1_.m01;
    this.m02 = p_226114_1_.m02;
    this.m10 = p_226114_1_.m10;
    this.m11 = p_226114_1_.m11;
    this.m12 = p_226114_1_.m12;
    this.m20 = p_226114_1_.m20;
    this.m21 = p_226114_1_.m21;
    this.m22 = p_226114_1_.m22;
  }
  
  public String toString() {
    StringBuilder stringbuilder = new StringBuilder();
    stringbuilder.append("Matrix3f:\n");
    stringbuilder.append(this.m00);
    stringbuilder.append(" ");
    stringbuilder.append(this.m01);
    stringbuilder.append(" ");
    stringbuilder.append(this.m02);
    stringbuilder.append("\n");
    stringbuilder.append(this.m10);
    stringbuilder.append(" ");
    stringbuilder.append(this.m11);
    stringbuilder.append(" ");
    stringbuilder.append(this.m12);
    stringbuilder.append("\n");
    stringbuilder.append(this.m20);
    stringbuilder.append(" ");
    stringbuilder.append(this.m21);
    stringbuilder.append(" ");
    stringbuilder.append(this.m22);
    stringbuilder.append("\n");
    return stringbuilder.toString();
  }
  
  @SideOnly(Side.CLIENT)
  public void setIdentity() {
    this.m00 = 1.0F;
    this.m01 = 0.0F;
    this.m02 = 0.0F;
    this.m10 = 0.0F;
    this.m11 = 1.0F;
    this.m12 = 0.0F;
    this.m20 = 0.0F;
    this.m21 = 0.0F;
    this.m22 = 1.0F;
  }
  
  @SideOnly(Side.CLIENT)
  public float adjugateAndDet() {
    float f = this.m11 * this.m22 - this.m12 * this.m21;
    float f1 = -(this.m10 * this.m22 - this.m12 * this.m20);
    float f2 = this.m10 * this.m21 - this.m11 * this.m20;
    float f3 = -(this.m01 * this.m22 - this.m02 * this.m21);
    float f4 = this.m00 * this.m22 - this.m02 * this.m20;
    float f5 = -(this.m00 * this.m21 - this.m01 * this.m20);
    float f6 = this.m01 * this.m12 - this.m02 * this.m11;
    float f7 = -(this.m00 * this.m12 - this.m02 * this.m10);
    float f8 = this.m00 * this.m11 - this.m01 * this.m10;
    float f9 = this.m00 * f + this.m01 * f1 + this.m02 * f2;
    this.m00 = f;
    this.m10 = f1;
    this.m20 = f2;
    this.m01 = f3;
    this.m11 = f4;
    this.m21 = f5;
    this.m02 = f6;
    this.m12 = f7;
    this.m22 = f8;
    return f9;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean invert() {
    float f = adjugateAndDet();
    if (Math.abs(f) > 1.0E-6F) {
      mul(f);
      return true;
    } 
    return false;
  }
  
  public void set(int p_232605_1_, int p_232605_2_, float p_232605_3_) {
    if (p_232605_1_ == 0) {
      if (p_232605_2_ == 0) {
        this.m00 = p_232605_3_;
      } else if (p_232605_2_ == 1) {
        this.m01 = p_232605_3_;
      } else {
        this.m02 = p_232605_3_;
      } 
    } else if (p_232605_1_ == 1) {
      if (p_232605_2_ == 0) {
        this.m10 = p_232605_3_;
      } else if (p_232605_2_ == 1) {
        this.m11 = p_232605_3_;
      } else {
        this.m12 = p_232605_3_;
      } 
    } else if (p_232605_2_ == 0) {
      this.m20 = p_232605_3_;
    } else if (p_232605_2_ == 1) {
      this.m21 = p_232605_3_;
    } else {
      this.m22 = p_232605_3_;
    } 
  }
  
  public void mul(Matrix3f p_226118_1_) {
    float f = this.m00 * p_226118_1_.m00 + this.m01 * p_226118_1_.m10 + this.m02 * p_226118_1_.m20;
    float f1 = this.m00 * p_226118_1_.m01 + this.m01 * p_226118_1_.m11 + this.m02 * p_226118_1_.m21;
    float f2 = this.m00 * p_226118_1_.m02 + this.m01 * p_226118_1_.m12 + this.m02 * p_226118_1_.m22;
    float f3 = this.m10 * p_226118_1_.m00 + this.m11 * p_226118_1_.m10 + this.m12 * p_226118_1_.m20;
    float f4 = this.m10 * p_226118_1_.m01 + this.m11 * p_226118_1_.m11 + this.m12 * p_226118_1_.m21;
    float f5 = this.m10 * p_226118_1_.m02 + this.m11 * p_226118_1_.m12 + this.m12 * p_226118_1_.m22;
    float f6 = this.m20 * p_226118_1_.m00 + this.m21 * p_226118_1_.m10 + this.m22 * p_226118_1_.m20;
    float f7 = this.m20 * p_226118_1_.m01 + this.m21 * p_226118_1_.m11 + this.m22 * p_226118_1_.m21;
    float f8 = this.m20 * p_226118_1_.m02 + this.m21 * p_226118_1_.m12 + this.m22 * p_226118_1_.m22;
    this.m00 = f;
    this.m01 = f1;
    this.m02 = f2;
    this.m10 = f3;
    this.m11 = f4;
    this.m12 = f5;
    this.m20 = f6;
    this.m21 = f7;
    this.m22 = f8;
  }
  
  @SideOnly(Side.CLIENT)
  public void mul(Quaternion p_226115_1_) {
    mul(new Matrix3f(p_226115_1_));
  }
  
  @SideOnly(Side.CLIENT)
  public void mul(float p_226111_1_) {
    this.m00 *= p_226111_1_;
    this.m01 *= p_226111_1_;
    this.m02 *= p_226111_1_;
    this.m10 *= p_226111_1_;
    this.m11 *= p_226111_1_;
    this.m12 *= p_226111_1_;
    this.m20 *= p_226111_1_;
    this.m21 *= p_226111_1_;
    this.m22 *= p_226111_1_;
  }
  
  @SideOnly(Side.CLIENT)
  public Matrix3f copy() {
    return new Matrix3f(this);
  }
  
  public void multiplyBackward(Matrix3f p_multiplyBackward_1_) {
    Matrix3f copy = p_multiplyBackward_1_.copy();
    copy.mul(this);
    load(copy);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\cor\\utils\math\Matrix3f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */