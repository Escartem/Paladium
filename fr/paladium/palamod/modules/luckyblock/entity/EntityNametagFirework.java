package fr.paladium.palamod.modules.luckyblock.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNametagFirework extends EntityFireworkRocket {
  private int fireworkAge;
  
  private int lifetime;
  
  private static final String __OBFID = "CL_00001718";
  
  private String text;
  
  public EntityNametagFirework(World world) {
    super(world);
    func_70105_a(0.25F, 0.25F);
  }
  
  protected void func_70088_a() {
    this.field_70180_af.func_82709_a(8, 5);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_70112_a(double dost) {
    return (dost < 4096.0D);
  }
  
  public EntityNametagFirework(World world, double x, double y, double z, String text) {
    super(world);
    this.fireworkAge = 0;
    func_70105_a(0.25F, 0.25F);
    func_70107_b(x, y, z);
    this.field_70129_M = 0.0F;
    int i = 1;
    this.text = text;
    this.field_70159_w = this.field_70146_Z.nextGaussian() * 0.001D;
    this.field_70179_y = this.field_70146_Z.nextGaussian() * 0.001D;
    this.field_70181_x = 0.05D;
    this.lifetime = 30 * i + this.field_70146_Z.nextInt(6) + this.field_70146_Z.nextInt(7);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
    this.field_70159_w = p_70016_1_;
    this.field_70181_x = p_70016_3_;
    this.field_70179_y = p_70016_5_;
    if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
      float f = MathHelper.func_76133_a(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
      this
        .field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / Math.PI);
      this
        .field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70016_3_, f) * 180.0D / Math.PI);
    } 
  }
  
  public void func_70071_h_() {
    this.field_70142_S = this.field_70165_t;
    this.field_70137_T = this.field_70163_u;
    this.field_70136_U = this.field_70161_v;
    super.func_70071_h_();
    this.field_70159_w *= 1.15D;
    this.field_70179_y *= 1.15D;
    this.field_70181_x += 0.04D;
    func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
    float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
    this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
    this
      .field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0D / Math.PI);
    for (; this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F);
    while (this.field_70125_A - this.field_70127_C >= 180.0F)
      this.field_70127_C += 360.0F; 
    while (this.field_70177_z - this.field_70126_B < -180.0F)
      this.field_70126_B -= 360.0F; 
    while (this.field_70177_z - this.field_70126_B >= 180.0F)
      this.field_70126_B += 360.0F; 
    this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
    this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
    if (this.fireworkAge == 0)
      this.field_70170_p.func_72956_a((Entity)this, "fireworks.launch", 3.0F, 1.0F); 
    this.fireworkAge++;
    if (this.field_70170_p.field_72995_K && this.fireworkAge % 2 < 2)
      this.field_70170_p.func_72869_a("fireworksSpark", this.field_70165_t, this.field_70163_u - 0.3D, this.field_70161_v, this.field_70146_Z
          .nextGaussian() * 0.05D, -this.field_70181_x * 0.5D, this.field_70146_Z.nextGaussian() * 0.05D); 
    if (this.field_70170_p.field_72995_K || this.fireworkAge != this.lifetime - 30 || func_70089_S());
    if (!this.field_70170_p.field_72995_K && this.fireworkAge > this.lifetime && func_70089_S())
      func_70106_y(); 
  }
  
  public void func_70103_a(byte b) {
    super.func_70103_a(b);
  }
  
  public void drawText(String text, double x, double y, double z) {
    int ox = 0;
    for (int i = 0; i < text.length(); i++) {
      drawChat(text.toLowerCase().charAt(i), x + ox, y, z);
      ox += 4;
    } 
  }
  
  public void drawChat(char c, double x, double y, double z) {
    if (c == 'a') {
      double i;
      for (i = 0.0D; i < 2.0D; i += 0.1D)
        createParticle(x - i, y + 2.0D - i * 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.1D)
        createParticle(x + i, y + 2.0D - i * 2.0D, z, 0.0D); 
      for (i = -1.0D; i < 1.0D; i += 0.1D)
        createParticle(x + i, y + 1.0D, z, 0.0D); 
    } 
    if (c == 'b') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 2.0D, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 1.9D; i += 0.1D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 1.9D; i += 0.1D)
        createParticle(x + i, y, z, 0.0D); 
      for (i = 0.0D; i < 1.9D; i += 0.1D)
        createParticle(x + i, y - 2.0D, z, 0.0D); 
    } 
    if (c == 'c') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.1D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.1D)
        createParticle(x + i, y - 2.0D, z, 0.0D); 
    } 
    if (c == 'd') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 2.1D, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.1D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.1D)
        createParticle(x + i, y - 2.0D, z, 0.0D); 
    } 
    if (c == 'e') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 1.9D; i += 0.1D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 1.9D; i += 0.1D)
        createParticle(x + i, y, z, 0.0D); 
      for (i = 0.0D; i < 1.9D; i += 0.1D)
        createParticle(x + i, y - 2.0D, z, 0.0D); 
    } 
    if (c == 'f') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 1.9D; i += 0.1D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 1.9D; i += 0.1D)
        createParticle(x + i, y, z, 0.0D); 
    } 
    if (c == 'g') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.1D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.1D)
        createParticle(x + i, y - 2.0D, z, 0.0D); 
      for (i = 4.0D; i > 2.0D; i -= 0.1D)
        createParticle(x + 2.0D, y + 2.0D - i, z, 0.0D); 
      for (i = 1.0D; i < 2.0D; i += 0.1D)
        createParticle(x + i, y, z, 0.0D); 
    } 
    if (c == 'h') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 2.0D, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 1.9D; i += 0.1D)
        createParticle(x + i, y, z, 0.0D); 
    } 
    if (c == 'i') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 1.5D, y + 2.0D - i, z, 0.0D); 
    } 
    if (c == 'j') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 1.5D, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 1.5D; i += 0.2D)
        createParticle(x + i, y - 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 1.0D; i += 0.2D)
        createParticle(x, y - 2.0D + i, z, 0.0D); 
    } 
    if (c == 'k') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.2D)
        createParticle(x + 2.0D - i, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.2D)
        createParticle(x + i, y - i, z, 0.0D); 
    } 
    if (c == 'l') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 1.5D, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 1.5D; i += 0.2D)
        createParticle(x + 1.5D + i, y - 2.0D, z, 0.0D); 
    } 
    if (c == 'm') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 1.5D, y + 2.0D - i, z, 0.0D); 
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 3.0D, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
    } 
    if (c == 'n') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 0.75D * i, y + 2.0D - i, z, 0.0D); 
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 3.0D, y + 2.0D - i, z, 0.0D); 
    } 
    if (c == 'o') {
      double i;
      for (i = 3.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 1.5D - i, z, 0.0D); 
      for (i = 3.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 3.0D, y + 1.5D - i, z, 0.0D); 
      for (i = 3.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + i, y + 1.5D, z, 0.0D); 
      for (i = 3.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + i, y - 1.5D, z, 0.0D); 
    } 
    if (c == 'p') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.2D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 2.0D; i += 0.2D)
        createParticle(x + i, y + 0.5D, z, 0.0D); 
      for (i = 1.5D; i > 0.0D; i -= 0.2D)
        createParticle(x + 2.0D, y + 2.0D - i, z, 0.0D); 
    } 
    if (c == 'q') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 3.0D, y + 2.0D - i, z, 0.0D); 
      for (i = 3.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 3.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + i, y - 2.0D, z, 0.0D); 
      for (i = 1.3D; i > 0.0D; i -= 0.2D)
        createParticle(x + 2.5D + i, y - 1.3D - i, z, 0.0D); 
    } 
    if (c == 'r') {
      double i;
      for (i = 4.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 1.5D; i > 0.0D; i -= 0.2D)
        createParticle(x + 3.0D, y + 2.0D - i, z, 0.0D); 
      for (i = 3.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 3.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + i, y + 0.5D, z, 0.0D); 
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y + 0.5D - 0.8333333333333334D * i, z, 0.0D); 
    } 
    if (c == 's') {
      double i;
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 2.0D; i > 0.0D; i -= 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y + 2.0D - 2.0D, z, 0.0D); 
      for (i = 2.0D; i > 0.0D; i -= 0.2D)
        createParticle(x + 3.0D, y + 2.0D - 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y + 2.0D - 4.0D, z, 0.0D); 
    } 
    if (c == 't') {
      double i;
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 1.5D, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
    } 
    if (c == 'u') {
      double i;
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 3.0D, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y - 2.0D, z, 0.0D); 
    } 
    if (c == 'v') {
      double i;
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 0.375D * i, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 1.5D + 0.375D * i, y - 2.0D + i, z, 0.0D); 
    } 
    if (c == 'w') {
      double i;
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 0.1875D * i, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 0.75D + 0.1875D * i, y - 2.0D + i, z, 0.0D); 
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 1.5D + 0.1875D * i, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 2.25D + 0.1875D * i, y - 2.0D + i, z, 0.0D); 
    } 
    if (c == 'x') {
      double i;
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 0.75D * i, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 4.0D; i += 0.2D)
        createParticle(x + 0.75D * i, y - 2.0D + i, z, 0.0D); 
    } 
    if (c == 'y') {
      double i;
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + 1.5D, y + 1.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 1.0D; i += 0.2D)
        createParticle(x + 1.5D * i, y + 2.0D - i, z, 0.0D); 
      for (i = 0.0D; i < 1.0D; i += 0.2D)
        createParticle(x + 1.5D + 1.5D * i, y + 1.0D + i, z, 0.0D); 
    } 
    if (c == 'z') {
      double i;
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y + 2.0D, z, 0.0D); 
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y - 2.0D + 1.3333333333333333D * i, z, 0.0D); 
      for (i = 0.0D; i < 3.0D; i += 0.2D)
        createParticle(x + i, y - 2.0D, z, 0.0D); 
    } 
  }
  
  private void createParticle(double x, double y, double z, double velocity) {
    EntityFireworkSparkFX entityfireworksparkfx = new EntityFireworkSparkFX(this.field_70170_p, x, y, z, velocity, velocity, velocity, (Minecraft.func_71410_x()).field_71452_i);
    entityfireworksparkfx.func_92045_e(true);
    entityfireworksparkfx.func_92043_f(true);
    entityfireworksparkfx.func_92044_a(Color.RED.getRGB());
    entityfireworksparkfx.func_92046_g(Color.ORANGE.getRGB());
    (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)entityfireworksparkfx);
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    p_70014_1_.func_74768_a("Life", this.fireworkAge);
    p_70014_1_.func_74768_a("LifeTime", this.lifetime);
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_) {
    this.fireworkAge = p_70037_1_.func_74762_e("Life");
    this.lifetime = p_70037_1_.func_74762_e("LifeTime");
  }
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R() {
    return 0.0F;
  }
  
  public float func_70013_c(float p_70013_1_) {
    return super.func_70013_c(p_70013_1_);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_70070_b(float p_70070_1_) {
    return super.func_70070_b(p_70070_1_);
  }
  
  public boolean func_70075_an() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityNametagFirework.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */