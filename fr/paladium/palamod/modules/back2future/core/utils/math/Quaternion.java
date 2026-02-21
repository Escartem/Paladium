package fr.paladium.palamod.modules.back2future.core.utils.math;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class Quaternion {
  public static final Quaternion ONE = new Quaternion(0.0F, 0.0F, 0.0F, 1.0F);
  
  private float i;
  
  private float j;
  
  private float k;
  
  private float r;
  
  public Quaternion(float p_i48100_1_, float p_i48100_2_, float p_i48100_3_, float p_i48100_4_) {
    this.i = p_i48100_1_;
    this.j = p_i48100_2_;
    this.k = p_i48100_3_;
    this.r = p_i48100_4_;
  }
  
  public Quaternion(Vector3f p_i48101_1_, float p_i48101_2_, boolean p_i48101_3_) {
    if (p_i48101_3_)
      p_i48101_2_ *= 0.017453292F; 
    float lvt_4_1_ = sin(p_i48101_2_ / 2.0F);
    this.i = p_i48101_1_.x() * lvt_4_1_;
    this.j = p_i48101_1_.y() * lvt_4_1_;
    this.k = p_i48101_1_.z() * lvt_4_1_;
    this.r = cos(p_i48101_2_ / 2.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public Quaternion(float p_i48102_1_, float p_i48102_2_, float p_i48102_3_, boolean p_i48102_4_) {
    if (p_i48102_4_) {
      p_i48102_1_ *= 0.017453292F;
      p_i48102_2_ *= 0.017453292F;
      p_i48102_3_ *= 0.017453292F;
    } 
    float lvt_5_1_ = sin(0.5F * p_i48102_1_);
    float lvt_6_1_ = cos(0.5F * p_i48102_1_);
    float lvt_7_1_ = sin(0.5F * p_i48102_2_);
    float lvt_8_1_ = cos(0.5F * p_i48102_2_);
    float lvt_9_1_ = sin(0.5F * p_i48102_3_);
    float lvt_10_1_ = cos(0.5F * p_i48102_3_);
    this.i = lvt_5_1_ * lvt_8_1_ * lvt_10_1_ + lvt_6_1_ * lvt_7_1_ * lvt_9_1_;
    this.j = lvt_6_1_ * lvt_7_1_ * lvt_10_1_ - lvt_5_1_ * lvt_8_1_ * lvt_9_1_;
    this.k = lvt_5_1_ * lvt_7_1_ * lvt_10_1_ + lvt_6_1_ * lvt_8_1_ * lvt_9_1_;
    this.r = lvt_6_1_ * lvt_8_1_ * lvt_10_1_ - lvt_5_1_ * lvt_7_1_ * lvt_9_1_;
  }
  
  public Quaternion(Quaternion p_i48103_1_) {
    this.i = p_i48103_1_.i;
    this.j = p_i48103_1_.j;
    this.k = p_i48103_1_.k;
    this.r = p_i48103_1_.r;
  }
  
  public boolean equals(Object p_equals_1_) {
    if (this == p_equals_1_)
      return true; 
    if (p_equals_1_ == null || getClass() != p_equals_1_.getClass())
      return false; 
    Quaternion lvt_2_1_ = (Quaternion)p_equals_1_;
    if (Float.compare(lvt_2_1_.i, this.i) != 0)
      return false; 
    if (Float.compare(lvt_2_1_.j, this.j) != 0)
      return false; 
    if (Float.compare(lvt_2_1_.k, this.k) != 0)
      return false; 
    return (Float.compare(lvt_2_1_.r, this.r) == 0);
  }
  
  public String toString() {
    StringBuilder lvt_1_1_ = new StringBuilder();
    lvt_1_1_.append("Quaternion[").append(r()).append(" + ");
    lvt_1_1_.append(i()).append("i + ");
    lvt_1_1_.append(j()).append("j + ");
    lvt_1_1_.append(k()).append("k]");
    return lvt_1_1_.toString();
  }
  
  public float i() {
    return this.i;
  }
  
  public float j() {
    return this.j;
  }
  
  public float k() {
    return this.k;
  }
  
  public float r() {
    return this.r;
  }
  
  public void mul(Quaternion p_195890_1_) {
    float lvt_2_1_ = i();
    float lvt_3_1_ = j();
    float lvt_4_1_ = k();
    float lvt_5_1_ = r();
    float lvt_6_1_ = p_195890_1_.i();
    float lvt_7_1_ = p_195890_1_.j();
    float lvt_8_1_ = p_195890_1_.k();
    float lvt_9_1_ = p_195890_1_.r();
    this.i = lvt_5_1_ * lvt_6_1_ + lvt_2_1_ * lvt_9_1_ + lvt_3_1_ * lvt_8_1_ - lvt_4_1_ * lvt_7_1_;
    this.j = lvt_5_1_ * lvt_7_1_ - lvt_2_1_ * lvt_8_1_ + lvt_3_1_ * lvt_9_1_ + lvt_4_1_ * lvt_6_1_;
    this.k = lvt_5_1_ * lvt_8_1_ + lvt_2_1_ * lvt_7_1_ - lvt_3_1_ * lvt_6_1_ + lvt_4_1_ * lvt_9_1_;
    this.r = lvt_5_1_ * lvt_9_1_ - lvt_2_1_ * lvt_6_1_ - lvt_3_1_ * lvt_7_1_ - lvt_4_1_ * lvt_8_1_;
  }
  
  @SideOnly(Side.CLIENT)
  public void mul(float p_227065_1_) {
    this.i *= p_227065_1_;
    this.j *= p_227065_1_;
    this.k *= p_227065_1_;
    this.r *= p_227065_1_;
  }
  
  public void conj() {
    this.i = -this.i;
    this.j = -this.j;
    this.k = -this.k;
  }
  
  @SideOnly(Side.CLIENT)
  public void set(float p_227066_1_, float p_227066_2_, float p_227066_3_, float p_227066_4_) {
    this.i = p_227066_1_;
    this.j = p_227066_2_;
    this.k = p_227066_3_;
    this.r = p_227066_4_;
  }
  
  private static float cos(float p_214904_0_) {
    return (float)Math.cos(p_214904_0_);
  }
  
  private static float sin(float p_214903_0_) {
    return (float)Math.sin(p_214903_0_);
  }
  
  @SideOnly(Side.CLIENT)
  public void normalize() {
    float lvt_1_1_ = i() * i() + j() * j() + k() * k() + r() * r();
    if (lvt_1_1_ > 1.0E-6F) {
      float lvt_2_1_ = Vector3f.fastInvSqrt(lvt_1_1_);
      this.i *= lvt_2_1_;
      this.j *= lvt_2_1_;
      this.k *= lvt_2_1_;
      this.r *= lvt_2_1_;
    } else {
      this.i = 0.0F;
      this.j = 0.0F;
      this.k = 0.0F;
      this.r = 0.0F;
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public Quaternion copy() {
    return new Quaternion(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\cor\\utils\math\Quaternion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */