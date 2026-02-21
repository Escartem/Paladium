package fr.paladium.palamod.modules.palaboss.common.entity.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityArachnaWeb extends EntityBossProjectiles {
  public EntityArachnaWeb(World p_i1753_1_) {
    super(p_i1753_1_);
    func_70105_a(1.0F, 1.0F);
  }
  
  public EntityArachnaWeb(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_) {
    super(p_i1754_1_, p_i1754_2_, p_i1754_4_, p_i1754_6_);
    func_70105_a(1.0F, 1.0F);
  }
  
  public EntityArachnaWeb(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, Float p_i1755_4_, Float p_i1755_5_) {
    super(p_i1755_1_, p_i1755_2_, p_i1755_3_, p_i1755_4_, p_i1755_5_);
    func_70105_a(1.0F, 1.0F);
  }
  
  public EntityArachnaWeb(World p_i1756_1_, EntityLivingBase p_i1756_2_, EntityLivingBase p_i1755_3_, float p_i1756_3_, float p_i1755_5_, int rotationYaw, int rotationPitch) {
    super(p_i1756_1_, p_i1756_2_, p_i1755_3_, Float.valueOf(p_i1756_3_), Float.valueOf(p_i1755_5_));
    this.field_70155_l = 10.0D;
    this.shootingEntity = (Entity)p_i1756_2_;
    func_70012_b(p_i1756_2_.field_70165_t, p_i1756_2_.field_70163_u + p_i1756_2_.func_70047_e(), p_i1756_2_.field_70161_v, rotationYaw, rotationPitch);
    this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    this.field_70163_u -= 0.10000000149011612D;
    this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    this.field_70129_M = 0.0F;
    this
      .field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
    this
      .field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
    this.field_70181_x = -MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F);
    func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, p_i1756_3_ * 1.5F, 1.0F);
    func_70105_a(1.0F, 1.0F);
  }
  
  public EntityArachnaWeb(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
    super(p_i1756_1_, p_i1756_2_, p_i1756_3_);
    func_70105_a(1.0F, 1.0F);
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    float f4 = 0.25F;
    this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f4, this.field_70163_u - this.field_70181_x * f4, this.field_70161_v - this.field_70179_y * f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
  }
  
  public void onImpact(Entity entityHit) {
    super.onImpact(entityHit);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\projectiles\EntityArachnaWeb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */