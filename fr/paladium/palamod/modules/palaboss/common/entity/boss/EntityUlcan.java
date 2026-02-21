package fr.paladium.palamod.modules.palaboss.common.entity.boss;

import fr.paladium.palamod.modules.palaboss.common.entity.ia.EntityAIGroundAttack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityUlcan extends EntityBossBase implements IRangedAttackMob, IEntityMultiPart {
  public EntityDragonPart[] dragonPartArray;
  
  public EntityDragonPart dragonPartTail1 = new EntityDragonPart(this, "tail", 4.0F, 4.0F);
  
  public EntityDragonPart dragonPartTail2 = new EntityDragonPart(this, "tail", 4.0F, 4.0F);
  
  public EntityDragonPart dragonPartTail3 = new EntityDragonPart(this, "tail", 4.0F, 4.0F);
  
  public EntityDragonPart dragonPartBody1 = new EntityDragonPart(this, "body", 4.0F, 4.0F);
  
  public EntityDragonPart dragonPartBody2 = new EntityDragonPart(this, "body", 4.0F, 4.0F);
  
  public EntityDragonPart dragonPartBody3 = new EntityDragonPart(this, "body", 4.0F, 4.0F);
  
  public EntityDragonPart dragonPartBody4 = new EntityDragonPart(this, "body", 4.0F, 4.0F);
  
  public EntityDragonPart dragonPartBody5 = new EntityDragonPart(this, "body", 4.0F, 4.0F);
  
  public EntityDragonPart dragonPartHead = new EntityDragonPart(this, "head", 4.0F, 4.0F);
  
  public double[][] ringBuffer = new double[64][3];
  
  public int ringBufferIndex = -1;
  
  public EntityUlcan(World world) {
    super(world);
    this.dragonPartArray = new EntityDragonPart[] { this.dragonPartBody1, this.dragonPartBody2, this.dragonPartBody3, this.dragonPartBody4, this.dragonPartBody5, this.dragonPartHead, this.dragonPartTail1, this.dragonPartTail2, this.dragonPartTail3 };
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIGroundAttack(this, 1.0D, 20, 80, 40.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    func_70105_a(5.0F, 5.0F);
  }
  
  public void func_70030_z() {
    super.func_70030_z();
    float f11 = 0.2F / (MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 10.0F + 1.0F);
    f11 *= (float)Math.pow(2.0D, this.field_70181_x);
    this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
    if (this.ringBufferIndex < 0)
      for (int b = 0; b < this.ringBuffer.length; b++) {
        this.ringBuffer[b][0] = this.field_70177_z;
        this.ringBuffer[b][1] = this.field_70163_u;
      }  
    if (++this.ringBufferIndex == this.ringBuffer.length)
      this.ringBufferIndex = 0; 
    this.ringBuffer[this.ringBufferIndex][0] = this.field_70177_z;
    this.ringBuffer[this.ringBufferIndex][1] = this.field_70163_u;
    this.field_70761_aq = this.field_70177_z;
    this.dragonPartTail1.field_70130_N = 2.0F;
    this.dragonPartTail1.field_70131_O = 2.0F;
    this.dragonPartTail2.field_70130_N = 2.0F;
    this.dragonPartTail2.field_70131_O = 2.0F;
    this.dragonPartTail3.field_70130_N = 2.0F;
    this.dragonPartTail3.field_70131_O = 2.0F;
    this.dragonPartBody1.field_70130_N = 2.0F;
    this.dragonPartBody1.field_70131_O = 2.0F;
    this.dragonPartBody2.field_70130_N = 2.0F;
    this.dragonPartBody2.field_70131_O = 2.0F;
    this.dragonPartBody3.field_70130_N = 2.0F;
    this.dragonPartBody3.field_70131_O = 2.0F;
    this.dragonPartBody4.field_70130_N = 2.0F;
    this.dragonPartBody4.field_70131_O = 2.0F;
    this.dragonPartBody5.field_70130_N = 2.0F;
    this.dragonPartBody5.field_70131_O = 2.0F;
    this.dragonPartHead.field_70130_N = 2.0F;
    this.dragonPartHead.field_70131_O = 2.0F;
    float f14 = (float)(getMovementOffsets(5, 1.0F)[1] - getMovementOffsets(10, 1.0F)[1]) * 10.0F * 0.017453292F;
    float f16 = MathHelper.func_76134_b(f14);
    float f2 = MathHelper.func_76126_a(f14);
    float f17 = this.field_70177_z * 0.017453292F;
    float f3 = MathHelper.func_76126_a(f17);
    float f18 = MathHelper.func_76134_b(f17);
    double[] adouble = getMovementOffsets(5, 1.0F);
    for (int k = 0; k < 9; k++) {
      EntityDragonPart multipartentitypart = null;
      if (k == 0)
        multipartentitypart = this.dragonPartTail1; 
      if (k == 1)
        multipartentitypart = this.dragonPartTail2; 
      if (k == 2)
        multipartentitypart = this.dragonPartTail3; 
      if (k == 3)
        multipartentitypart = this.dragonPartBody1; 
      if (k == 4)
        multipartentitypart = this.dragonPartBody2; 
      if (k == 5)
        multipartentitypart = this.dragonPartBody3; 
      if (k == 6)
        multipartentitypart = this.dragonPartBody4; 
      if (k == 7)
        multipartentitypart = this.dragonPartBody5; 
      if (k == 8)
        multipartentitypart = this.dragonPartHead; 
      double[] adouble1 = getMovementOffsets(12 + k * 2, 1.0F);
      float f21 = this.field_70177_z * 0.017453292F + simplifyAngle(adouble1[0] - adouble[0]) * 0.017453292F;
      float f6 = MathHelper.func_76126_a(f21);
      float f22 = MathHelper.func_76134_b(f21);
      float f24 = (k + -5) * 2.0F;
      multipartentitypart.func_70071_h_();
      multipartentitypart.func_70012_b(this.field_70165_t - ((f3 * 1.5F + f6 * f24) * f16), this.field_70163_u + adouble1[1] - adouble[1] - ((f24 + 1.5F) * f2), this.field_70161_v + ((f18 * 1.5F + f22 * f24) * f16), 0.0F, 0.0F);
    } 
    if (func_70638_az() != null) {
      EntityLivingBase entitylivingbase = func_70638_az();
      if (entitylivingbase != null && func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b, entitylivingbase.field_70161_v) > 40.0D) {
        if (!this.field_70170_p.field_72995_K) {
          this.field_70159_w = (entitylivingbase.field_70165_t - this.field_70165_t) * 0.05000000074505806D;
          this.field_70181_x = (entitylivingbase.field_70163_u - this.field_70163_u) * 0.05000000074505806D;
          this.field_70179_y = (entitylivingbase.field_70161_v - this.field_70161_v) * 0.05000000074505806D;
        } 
      } else {
        this.field_70159_w = 0.0D;
        this.field_70181_x = 0.0D;
        this.field_70179_y = 0.0D;
      } 
    } 
  }
  
  private float simplifyAngle(double p_70973_1_) {
    return (float)MathHelper.func_76138_g(p_70973_1_);
  }
  
  public double[] getMovementOffsets(int p_70974_1_, float p_70974_2_) {
    if (func_110143_aJ() <= 0.0F)
      p_70974_2_ = 0.0F; 
    p_70974_2_ = 1.0F - p_70974_2_;
    int j = this.ringBufferIndex - p_70974_1_ * 1 & 0x3F;
    int k = this.ringBufferIndex - p_70974_1_ * 1 - 1 & 0x3F;
    double[] adouble = new double[3];
    double d0 = this.ringBuffer[j][0];
    double d1 = MathHelper.func_76138_g(this.ringBuffer[k][0] - d0);
    adouble[0] = d0 + d1 * p_70974_2_;
    d0 = this.ringBuffer[j][1];
    d1 = this.ringBuffer[k][1] - d0;
    adouble[1] = d0 + d1 * p_70974_2_;
    adouble[2] = this.ringBuffer[j][2] + (this.ringBuffer[k][2] - this.ringBuffer[j][2]) * p_70974_2_;
    return adouble;
  }
  
  public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {}
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    return super.func_70097_a(p_70097_1_, p_70097_2_);
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  protected boolean func_70692_ba() {
    return false;
  }
  
  public boolean func_70648_aU() {
    return super.func_70648_aU();
  }
  
  public boolean func_70601_bi() {
    return this.field_70170_p.func_72855_b(this.field_70121_D);
  }
  
  public float getEntityWidth() {
    return 8.0F;
  }
  
  public float getEntityHeight() {
    return 2.0F;
  }
  
  public Entity[] func_70021_al() {
    return (Entity[])this.dragonPartArray;
  }
  
  public World func_82194_d() {
    return this.field_70170_p;
  }
  
  public boolean func_70965_a(EntityDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_) {
    func_70097_a(p_70965_2_, p_70965_3_);
    return false;
  }
  
  public boolean func_70067_L() {
    return false;
  }
  
  public String name() {
    return "Ulcan";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\boss\EntityUlcan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */