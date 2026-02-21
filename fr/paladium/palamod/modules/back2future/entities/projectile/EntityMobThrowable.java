package fr.paladium.palamod.modules.back2future.entities.projectile;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityMobThrowable extends EntityThrowable {
  private float damage;
  
  public EntityMobThrowable(World world) {
    super(world);
  }
  
  public EntityMobThrowable(World world, EntityLivingBase entity) {
    super(world, entity);
  }
  
  public EntityMobThrowable(World world, double x, double y, double z) {
    super(world, x, y, z);
  }
  
  public EntityMobThrowable(World world, EntityLivingBase shooter, float wobble, float FrontToBack, float YOffset, float SideToSide) {
    super(world, shooter);
    float r3 = this.field_70177_z * 3.1415927F / 180.0F;
    float r11 = MathHelper.func_76134_b(r3);
    float r4 = MathHelper.func_76126_a(r3);
    double xOff = (r11 * -FrontToBack) + (r4 * SideToSide);
    double zOff = (r4 * FrontToBack) + (r11 * SideToSide);
    func_70012_b(shooter.field_70165_t + xOff, this.field_70163_u - YOffset, shooter.field_70161_v + zOff, 0.0F, 0.0F);
  }
  
  public EntityMobThrowable(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float HitBoxSize1, float HitBoxSize2) {
    super(world, shooter);
    if (HitBoxSize1 != 0.0F || HitBoxSize2 != 0.0F)
      func_70105_a(HitBoxSize1, HitBoxSize2); 
    float r3 = this.field_70177_z * 3.1415927F / 180.0F;
    float r11 = MathHelper.func_76134_b(r3);
    float r4 = MathHelper.func_76126_a(r3);
    this.field_70163_u = shooter.field_70163_u + shooter.func_70047_e() - 0.2D;
    double d0 = target.field_70165_t - shooter.field_70165_t;
    double d1 = target.field_70163_u - this.field_70163_u - 2.0D;
    double d2 = target.field_70161_v - shooter.field_70161_v;
    double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
    if (d3 >= 1.0E-7D) {
      float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
      float f3 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
      double d4 = d0 / d3;
      double d5 = d2 / d3;
      double xOff = (r11 * -FrontToBack) + (r4 * SideToSide);
      double zOff = (r4 * FrontToBack) + (r11 * SideToSide);
      func_70012_b(shooter.field_70165_t + xOff, this.field_70163_u - YOffset, shooter.field_70161_v + zOff, f2, f3);
      YOffset = 0.0F;
      float f4 = (float)d3 * 0.2F;
      func_70186_c(d0 - xOff, d1 + f4 + YOffset, d2 - zOff, velocity, wobble);
    } 
  }
  
  public EntityMobThrowable(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float MotionY, float YawOffset) {
    super(world, shooter);
    this.field_70163_u = shooter.field_70163_u + shooter.func_70047_e() - 0.10000000149011612D;
    double d0 = target.field_70165_t - shooter.field_70165_t;
    double d1 = (target.func_70046_E()).field_72338_b + 1.0D - this.field_70163_u;
    double d2 = target.field_70161_v - shooter.field_70161_v;
    double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
    if (d3 >= 1.0E-7D) {
      float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
      float f3 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
      double d4 = d0 / d3;
      double d5 = d2 / d3;
      func_70012_b(shooter.field_70165_t - d4, this.field_70163_u, shooter.field_70161_v - d5, f2, f3);
      float f4 = (float)d3 * 0.2F;
      func_70186_c(d0 * -1.0D, 0.0D, d2 * -1.0D, velocity, wobble);
      float A2 = MathHelper.func_76126_a(YawOffset + shooter.field_70177_z * 3.1415927F / 180.0F);
      float A3 = MathHelper.func_76134_b(YawOffset + shooter.field_70177_z * 3.1415927F / 180.0F);
      this.field_70159_w += (-1.0F * velocity * A2);
      this.field_70181_x += MotionY;
      this.field_70179_y += (velocity * A3);
    } 
  }
  
  public EntityMobThrowable(World world, EntityLivingBase shooter, BlockPos pos, float velocity, float MotionY) {
    super(world, shooter);
    this.field_70163_u = shooter.field_70163_u + shooter.func_70047_e() - 0.10000000149011612D;
    double d0 = pos.getX() - shooter.field_70165_t;
    double d1 = (pos.getY() + 1) - this.field_70163_u;
    double d2 = pos.getZ() - shooter.field_70161_v;
    double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
    if (d3 >= 1.0E-7D) {
      float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
      float f3 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
      double d4 = d0 / d3;
      double d5 = d2 / d3;
      func_70012_b(shooter.field_70165_t - d4, this.field_70163_u, shooter.field_70161_v - d5, f2, f3);
      float f4 = (float)d3 * 0.2F;
      func_70186_c(d0, 0.0D, d2, velocity, 0.0F);
      this.field_70181_x += MotionY;
    } 
  }
  
  public float getDamage() {
    return this.damage;
  }
  
  public EntityMobThrowable setDamage(float amount) {
    this.damage = amount;
    return this;
  }
  
  public void func_70014_b(NBTTagCompound compound) {
    super.func_70014_b(compound);
    compound.func_74776_a("damage", this.damage);
  }
  
  public void func_70037_a(NBTTagCompound compound) {
    super.func_70037_a(compound);
    this.damage = compound.func_74760_g("damage");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\projectile\EntityMobThrowable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */