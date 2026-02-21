package fr.paladium.palamod.modules.luckyblock.entity.black;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

public class EntityMissileMeteo extends EntityLiving {
  private double lastMotion = 0.01D;
  
  private double yOrigin = -1.0D;
  
  public EntityMissileMeteo(World p_i1582_1_) {
    super(p_i1582_1_);
    func_70105_a(1.0F, 1.0F);
  }
  
  public void func_70030_z() {
    super.func_70030_z();
    if (this.field_70173_aa > 1200L)
      func_70106_y(); 
    if (this.field_70128_L)
      return; 
    this.field_70181_x *= 1.05D;
    if (this.yOrigin == -1.0D)
      this.yOrigin = this.field_70163_u; 
    if (this.field_70181_x > 0.0D) {
      this.lastMotion = this.field_70181_x;
    } else {
      this.field_70181_x = this.lastMotion;
    } 
    if (this.field_70181_x > 1.0D)
      this.field_70181_x = 1.0D; 
    func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
    if (this.field_70163_u > 300.0D || this.field_70163_u - this.yOrigin >= 60.0D) {
      if (!this.field_70170_p.field_72995_K) {
        WorldInfo info = this.field_70170_p.func_72912_H();
        if (info.func_76059_o()) {
          info.func_76080_g(0);
          info.func_76084_b(false);
          info.func_76090_f(0);
          info.func_76069_a(false);
        } else {
          info.func_76084_b(true);
          info.func_76069_a(true);
        } 
        this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, true);
      } 
      func_70106_y();
    } 
    this.field_70170_p.func_72869_a("flame", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, -0.10000000149011612D * this.field_70181_x, 0.0D);
    this.field_70170_p.func_72869_a("smoke", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, -0.10000000149011612D * this.field_70181_x, 0.0D);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70181_x = 0.01D;
  }
  
  public void func_70612_e(float p_70612_1_, float p_70612_2_) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\black\EntityMissileMeteo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */