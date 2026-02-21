package fr.paladium.palawither.client.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityWitherSmokeFX extends EntityFX {
  private final float smokeParticleScale;
  
  public EntityWitherSmokeFX(World world, double x, double y, double z, double red, double green, double blue) {
    this(world, x, y, z, red, green, blue, 1.0F);
  }
  
  public EntityWitherSmokeFX(World world, double x, double y, double z, double red, double green, double blue, float ticks) {
    super(world, x, y, z, 0.0D, 0.0D, 0.0D);
    this.field_70159_w *= 0.10000000149011612D;
    this.field_70181_x *= 0.10000000149011612D;
    this.field_70179_y *= 0.10000000149011612D;
    this.field_70552_h = (float)red;
    this.field_70553_i = (float)green;
    this.field_70551_j = (float)blue;
    this.field_70544_f *= 0.75F;
    this.field_70544_f *= ticks;
    this.smokeParticleScale = this.field_70544_f;
    this.field_70547_e = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
    this.field_70547_e = (int)(this.field_70547_e * ticks);
    this.field_70145_X = false;
  }
  
  public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
    float f6 = (this.field_70546_d + p_70539_2_) / this.field_70547_e * 32.0F;
    if (f6 < 0.0F)
      f6 = 0.0F; 
    if (f6 > 1.0F)
      f6 = 1.0F; 
    this.field_70544_f = this.smokeParticleScale * f6;
    super.func_70539_a(p_70539_1_, p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
  }
  
  public void func_70071_h_() {
    this.field_70169_q = this.field_70165_t;
    this.field_70167_r = this.field_70163_u;
    this.field_70166_s = this.field_70161_v;
    if (this.field_70546_d++ >= this.field_70547_e)
      func_70106_y(); 
    func_70536_a(7 - this.field_70546_d * 8 / this.field_70547_e);
    this.field_70181_x += 0.004D;
    func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
    if (this.field_70163_u == this.field_70167_r) {
      this.field_70159_w *= 1.1D;
      this.field_70179_y *= 1.1D;
    } 
    this.field_70159_w *= 0.9599999785423279D;
    this.field_70181_x *= 0.9599999785423279D;
    this.field_70179_y *= 0.9599999785423279D;
    if (this.field_70122_E) {
      this.field_70159_w *= 0.699999988079071D;
      this.field_70179_y *= 0.699999988079071D;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\entity\EntityWitherSmokeFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */