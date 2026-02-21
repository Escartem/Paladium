package fr.paladium.palamod.modules.palaboss.common.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGear extends EntityBossProjectiles {
  public EntityGear(World p_i1753_1_) {
    super(p_i1753_1_);
    func_70105_a(1.0F, 0.4F);
  }
  
  public EntityGear(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_) {
    super(p_i1754_1_, p_i1754_2_, p_i1754_4_, p_i1754_6_);
    func_70105_a(1.0F, 0.4F);
  }
  
  public EntityGear(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, Float p_i1755_4_, Float p_i1755_5_) {
    super(p_i1755_1_, p_i1755_2_, p_i1755_3_, p_i1755_4_, p_i1755_5_);
    func_70105_a(1.0F, 0.4F);
  }
  
  public EntityGear(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
    super(p_i1756_1_, p_i1756_2_, p_i1756_3_);
    func_70105_a(1.0F, 0.4F);
  }
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    if (func_85032_ar())
      return false; 
    func_70018_K();
    if (p_70097_1_.func_76346_g() != null && Math.random() >= 0.5D) {
      Vec3 vec3 = p_70097_1_.func_76346_g().func_70040_Z();
      if (vec3 != null) {
        this.field_70159_w = vec3.field_72450_a;
        this.field_70181_x = vec3.field_72448_b;
        this.field_70179_y = vec3.field_72449_c;
      } 
      if (p_70097_1_.func_76346_g() instanceof EntityLivingBase)
        this.shootingEntity = p_70097_1_.func_76346_g(); 
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\projectiles\EntityGear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */