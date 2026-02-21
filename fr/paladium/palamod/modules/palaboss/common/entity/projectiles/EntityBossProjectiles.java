package fr.paladium.palamod.modules.palaboss.common.entity.projectiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityBossProjectiles extends Entity implements IProjectile {
  private float damage = 10.0F;
  
  private int field_145791_d = -1;
  
  private int field_145792_e = -1;
  
  private int field_145789_f = -1;
  
  private Block field_145790_g;
  
  private int inData;
  
  public int arrowShake;
  
  public Entity shootingEntity;
  
  private int ticksInAir;
  
  private int knockbackStrength;
  
  private static final String __OBFID = "CL_00001715";
  
  public EntityBossProjectiles(World p_i1753_1_) {
    super(p_i1753_1_);
    this.field_70155_l = 10.0D;
  }
  
  public EntityBossProjectiles(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_) {
    super(p_i1754_1_);
    this.field_70155_l = 10.0D;
    func_70107_b(p_i1754_2_, p_i1754_4_, p_i1754_6_);
    this.field_70129_M = 0.0F;
  }
  
  public EntityBossProjectiles(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, Float p_i1755_4_, Float p_i1755_5_) {
    super(p_i1755_1_);
    this.field_70155_l = 10.0D;
    this.shootingEntity = (Entity)p_i1755_2_;
    this.field_70163_u = p_i1755_2_.field_70163_u + p_i1755_2_.func_70047_e() - 0.10000000149011612D;
    double d0 = p_i1755_3_.field_70165_t - p_i1755_2_.field_70165_t;
    double d1 = p_i1755_3_.field_70121_D.field_72338_b + (p_i1755_3_.field_70131_O / 3.0F) - this.field_70163_u;
    double d2 = p_i1755_3_.field_70161_v - p_i1755_2_.field_70161_v;
    double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
    if (d3 >= 1.0E-7D) {
      float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
      float f3 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
      double d4 = d0 / d3;
      double d5 = d2 / d3;
      func_70012_b(p_i1755_2_.field_70165_t + d4, this.field_70163_u, p_i1755_2_.field_70161_v + d5, f2, f3);
      this.field_70129_M = 0.0F;
      float f4 = (float)d3 * 0.2F;
      func_70186_c(d0, d1 + f4, d2, p_i1755_4_.floatValue(), p_i1755_5_.floatValue());
    } 
  }
  
  public EntityBossProjectiles(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
    super(p_i1756_1_);
    this.field_70155_l = 10.0D;
    this.shootingEntity = (Entity)p_i1756_2_;
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
  
  public void setDamage(float damage) {
    this.damage = damage;
  }
  
  protected void func_70088_a() {
    this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
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
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
    func_70107_b(p_70056_1_, p_70056_3_, p_70056_5_);
    func_70101_b(p_70056_7_, p_70056_8_);
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
      this.field_70127_C = this.field_70125_A;
      this.field_70126_B = this.field_70177_z;
      func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
    } 
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
      float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this
        .field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
      this
        .field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0D / Math.PI);
    } 
    Block block = this.field_70170_p.func_147439_a(this.field_145791_d, this.field_145792_e, this.field_145789_f);
    if (block.func_149688_o() != Material.field_151579_a) {
      block.func_149719_a((IBlockAccess)this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
      AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
      if (axisalignedbb != null && axisalignedbb
        .func_72318_a(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v))) {
        func_70106_y();
        return;
      } 
    } 
    if (this.arrowShake > 0)
      this.arrowShake--; 
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
    if (movingobjectposition != null)
      if (movingobjectposition.field_72308_g != null) {
        float f4 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
        float k = this.damage;
        DamageSource damagesource = null;
        if (this.shootingEntity == null) {
          damagesource = DamageSource.func_76356_a(this, this);
        } else {
          damagesource = DamageSource.func_76356_a(this, this.shootingEntity);
        } 
        if (!(movingobjectposition.field_72308_g instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase))
          if (movingobjectposition.field_72308_g.func_70097_a(DamageSource.field_76376_m, k)) {
            if (movingobjectposition.field_72308_g instanceof EntityLivingBase) {
              EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
              if (!this.field_70170_p.field_72995_K)
                entitylivingbase
                  .func_85034_r(entitylivingbase.func_85035_bI() + 1); 
              if (this.knockbackStrength > 0) {
                float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                if (f > 0.0F)
                  movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * this.knockbackStrength * 0.6000000238418579D / f, 0.1D, this.field_70179_y * this.knockbackStrength * 0.6000000238418579D / f); 
              } 
              if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
                EnchantmentHelper.func_151384_a(entitylivingbase, this.shootingEntity);
                EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, (Entity)entitylivingbase);
              } 
              if (this.shootingEntity != null && movingobjectposition.field_72308_g != this.shootingEntity && movingobjectposition.field_72308_g instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                ((EntityPlayerMP)this.shootingEntity).field_71135_a
                  .func_147359_a((Packet)new S2BPacketChangeGameState(6, 0.0F)); 
            } 
            if (!(movingobjectposition.field_72308_g instanceof net.minecraft.entity.monster.EntityEnderman))
              func_70106_y(); 
          } else {
            this.field_70159_w *= -0.10000000149011612D;
            this.field_70181_x *= -0.10000000149011612D;
            this.field_70179_y *= -0.10000000149011612D;
            this.field_70177_z += 180.0F;
            this.field_70126_B += 180.0F;
            this.ticksInAir = 0;
          }  
      } else {
        this.field_145791_d = movingobjectposition.field_72311_b;
        this.field_145792_e = movingobjectposition.field_72312_c;
        this.field_145789_f = movingobjectposition.field_72309_d;
        this
          .field_145790_g = this.field_70170_p.func_147439_a(this.field_145791_d, this.field_145792_e, this.field_145789_f);
        this.inData = this.field_70170_p.func_72805_g(this.field_145791_d, this.field_145792_e, this.field_145789_f);
        this.field_70159_w = (float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t);
        this.field_70181_x = (float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u);
        this.field_70179_y = (float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v);
        float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
        this.field_70165_t -= this.field_70159_w / f * 0.05000000074505806D;
        this.field_70163_u -= this.field_70181_x / f * 0.05000000074505806D;
        this.field_70161_v -= this.field_70179_y / f * 0.05000000074505806D;
        func_70106_y();
        onImpact(this);
        this.arrowShake = 7;
        if (this.field_145790_g.func_149688_o() != Material.field_151579_a)
          this.field_145790_g.func_149670_a(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f, this); 
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
    this.field_70181_x *= f3;
    this.field_70179_y *= f3;
    this.field_70181_x -= f1;
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    func_145775_I();
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    p_70014_1_.func_74777_a("xTile", (short)this.field_145791_d);
    p_70014_1_.func_74777_a("yTile", (short)this.field_145792_e);
    p_70014_1_.func_74777_a("zTile", (short)this.field_145789_f);
    p_70014_1_.func_74774_a("inTile", (byte)Block.func_149682_b(this.field_145790_g));
    p_70014_1_.func_74774_a("inData", (byte)this.inData);
    p_70014_1_.func_74774_a("shake", (byte)this.arrowShake);
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_) {
    this.field_145791_d = p_70037_1_.func_74765_d("xTile");
    this.field_145792_e = p_70037_1_.func_74765_d("yTile");
    this.field_145789_f = p_70037_1_.func_74765_d("zTile");
    this.field_145790_g = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 0xFF);
    this.inData = p_70037_1_.func_74771_c("inData") & 0xFF;
    this.arrowShake = p_70037_1_.func_74771_c("shake") & 0xFF;
  }
  
  public void onImpact(Entity entityHit) {}
  
  protected boolean func_70041_e_() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R() {
    return 0.0F;
  }
  
  public void setKnockbackStrength(int p_70240_1_) {
    this.knockbackStrength = p_70240_1_;
  }
  
  public boolean func_70075_an() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\projectiles\EntityBossProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */