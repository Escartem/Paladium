package fr.paladium.palamod.modules.palaboss.common.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntitySandstorm extends EntityBossProjectiles {
  public EntitySandstorm(World p_i1753_1_) {
    super(p_i1753_1_);
  }
  
  public EntitySandstorm(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_) {
    super(p_i1754_1_, p_i1754_2_, p_i1754_4_, p_i1754_6_);
  }
  
  public EntitySandstorm(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, Float p_i1755_4_, Float p_i1755_5_) {
    super(p_i1755_1_, p_i1755_2_, p_i1755_3_, p_i1755_4_, p_i1755_5_);
    func_70105_a(1.0F, 2.0F);
  }
  
  public EntitySandstorm(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
    super(p_i1756_1_, p_i1756_2_, p_i1756_3_);
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    float f4 = 0.25F;
    this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f4, this.field_70163_u - this.field_70181_x * f4, this.field_70161_v - this.field_70179_y * f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\projectiles\EntitySandstorm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */