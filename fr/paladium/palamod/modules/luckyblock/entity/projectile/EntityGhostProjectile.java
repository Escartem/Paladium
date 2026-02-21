package fr.paladium.palamod.modules.luckyblock.entity.projectile;

import fr.paladium.palamod.modules.back2future.entities.ai.Vec3i;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGhostProjectile extends Entity implements IProjectile {
  EntityLivingBase shootingEntity;
  
  private int ticksInGround;
  
  private int ticksInAir;
  
  private Vec3i startPos;
  
  private int maxDistance = 30;
  
  public EntityGhostProjectile(World p_i1582_1_) {
    super(p_i1582_1_);
    this.field_70155_l = 10.0D;
    func_70105_a(0.5F, 1.0F);
  }
  
  public EntityGhostProjectile(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
    super(p_i1756_1_);
    this.field_70155_l = 10.0D;
    this.shootingEntity = p_i1756_2_;
    this.startPos = new Vec3i(p_i1756_2_.field_70165_t, p_i1756_2_.field_70163_u + p_i1756_2_.func_70047_e(), p_i1756_2_.field_70161_v);
    func_70105_a(0.5F, 1.0F);
    func_70012_b(p_i1756_2_.field_70165_t, p_i1756_2_.field_70163_u + p_i1756_2_.func_70047_e(), p_i1756_2_.field_70161_v, p_i1756_2_.field_70177_z, p_i1756_2_.field_70125_A);
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
  }
  
  public void func_70186_c(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
    float f2 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
    p_70186_1_ /= f2;
    p_70186_3_ /= f2;
    p_70186_5_ /= f2;
    p_70186_1_ += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : true) * 0.007499999832361937D * p_70186_8_;
    p_70186_3_ += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : true) * 0.007499999832361937D * p_70186_8_;
    p_70186_5_ += this.field_70146_Z.nextGaussian() * (this.field_70146_Z.nextBoolean() ? -1 : true) * 0.007499999832361937D * p_70186_8_;
    p_70186_1_ *= p_70186_7_;
    p_70186_3_ *= p_70186_7_;
    p_70186_5_ *= p_70186_7_;
    this.field_70159_w = p_70186_1_;
    this.field_70181_x = p_70186_3_;
    this.field_70179_y = p_70186_5_;
    float f3 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
    this
      .field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
    this
      .field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70186_3_, f3) * 180.0D / Math.PI);
    this.ticksInGround = 0;
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.startPos != null && 
      Math.sqrt(this.startPos.distanceSq(this.field_70165_t, this.field_70163_u, this.field_70161_v)) > this.maxDistance) {
      func_70106_y();
      return;
    } 
    if (this.startPos == null)
      this.startPos = new Vec3i(this.field_70165_t, this.field_70163_u, this.field_70161_v); 
    if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
      float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this
        .field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
      this
        .field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0D / Math.PI);
    } 
    this.ticksInAir++;
    Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
    MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec31, vec3, false, true, false);
    vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
    if (movingobjectposition != null)
      vec3 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c); 
    Entity entity = null;
    List<Entity> list = this.field_70170_p.func_72839_b(this, this.field_70121_D
        .func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
    double d0 = 0.0D;
    for (int i = 0; i < list.size(); i++) {
      Entity entity1 = list.get(i);
      if (entity1.func_70067_L() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
        float f = 0.3F;
        AxisAlignedBB axisalignedbb1 = entity1.field_70121_D.func_72314_b(f, f, f);
        MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec31, vec3);
        if (movingobjectposition1 != null) {
          double d1 = vec31.func_72438_d(movingobjectposition1.field_72307_f);
          if (d1 < d0 || d0 == 0.0D) {
            entity = entity1;
            d0 = d1;
          } 
        } 
      } 
    } 
    if (entity != null)
      movingobjectposition = new MovingObjectPosition(entity); 
    if (movingobjectposition != null && movingobjectposition.field_72308_g != null && movingobjectposition.field_72308_g instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
      if (entityplayer.field_71075_bZ.field_75102_a || (this.shootingEntity instanceof EntityPlayer && 
        !((EntityPlayer)this.shootingEntity).func_96122_a(entityplayer)))
        movingobjectposition = null; 
    } 
    if (movingobjectposition != null && this.shootingEntity != null && 
      movingobjectposition.field_72308_g != null && 
      movingobjectposition.field_72308_g instanceof EntityPlayer && 
      !this.shootingEntity.equals(movingobjectposition.field_72308_g)) {
      ((EntityLivingBase)movingobjectposition.field_72308_g)
        .func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 60, 4));
      func_70106_y();
      return;
    } 
    this.field_70165_t += this.field_70159_w;
    this.field_70163_u += this.field_70181_x;
    this.field_70161_v += this.field_70179_y;
    float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
    this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
    this
      .field_70125_A = (float)(Math.atan2(this.field_70181_x, f2) * 180.0D / Math.PI);
    for (; this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F);
    while (this.field_70125_A - this.field_70127_C >= 180.0F)
      this.field_70127_C += 360.0F; 
    while (this.field_70177_z - this.field_70126_B < -180.0F)
      this.field_70126_B -= 360.0F; 
    while (this.field_70177_z - this.field_70126_B >= 180.0F)
      this.field_70126_B += 360.0F; 
    this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
    this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
    float f3 = 0.99F;
    float f1 = 0.05F;
    if (func_70090_H()) {
      for (int l = 0; l < 4; l++) {
        float f4 = 0.25F;
        this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f4, this.field_70163_u - this.field_70181_x * f4, this.field_70161_v - this.field_70179_y * f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
      } 
      f3 = 0.8F;
    } 
    if (func_70026_G())
      func_70066_B(); 
    this.field_70159_w *= f3;
    this.field_70179_y *= f3;
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    func_145775_I();
  }
  
  protected void func_70088_a() {}
  
  protected void func_70037_a(NBTTagCompound p_70037_1_) {}
  
  protected void func_70014_b(NBTTagCompound p_70014_1_) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\projectile\EntityGhostProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */