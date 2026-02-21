package fr.paladium.palamod.modules.luckyblock.entity;

import java.util.Calendar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNervousBat extends EntityMob {
  private ChunkCoordinates spawnPosition;
  
  private static final String __OBFID = "CL_00001637";
  
  public EntityNervousBat(World world) {
    super(world);
    func_70105_a(0.5F, 0.9F);
    setIsBatHanging(true);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0D, false));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIMoveThroughVillage((EntityCreature)this, 1.0D, false));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(16, new Byte((byte)0));
  }
  
  protected float func_70599_aP() {
    return 0.1F;
  }
  
  protected float func_70647_i() {
    return super.func_70647_i() * 0.95F;
  }
  
  protected String func_70639_aQ() {
    return (getIsBatHanging() && this.field_70146_Z.nextInt(4) != 0) ? null : "mob.bat.idle";
  }
  
  protected String func_70621_aR() {
    return "mob.bat.hurt";
  }
  
  protected String func_70673_aS() {
    return "mob.bat.death";
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  protected void func_82167_n(Entity p_82167_1_) {}
  
  protected void func_85033_bc() {}
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(6.0D);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
  }
  
  public boolean getIsBatHanging() {
    return ((this.field_70180_af.func_75683_a(16) & 0x1) != 0);
  }
  
  public void setIsBatHanging(boolean p_82236_1_) {
    byte b0 = this.field_70180_af.func_75683_a(16);
    if (p_82236_1_) {
      this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x1)));
    } else {
      this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
    } 
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    this.field_70159_w = this.field_70181_x = this.field_70179_y = 0.0D;
    this.field_70163_u = MathHelper.func_76128_c(this.field_70163_u) + 1.0D - this.field_70131_O;
    this.field_70181_x *= 0.6000000238418579D;
  }
  
  protected void func_70619_bc() {
    super.func_70619_bc();
    if (getIsBatHanging()) {
      if (!this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), (int)this.field_70163_u + 1, MathHelper.func_76128_c(this.field_70161_v)).func_149721_r()) {
        setIsBatHanging(false);
        this.field_70170_p.func_72889_a((EntityPlayer)null, 1015, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
      } else {
        if (this.field_70146_Z.nextInt(200) == 0)
          this.field_70759_as = this.field_70146_Z.nextInt(360); 
        if (this.field_70170_p.func_72890_a((Entity)this, 4.0D) != null) {
          setIsBatHanging(false);
          this.field_70170_p.func_72889_a((EntityPlayer)null, 1015, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
        } 
      } 
    } else {
      if (this.spawnPosition != null && (!this.field_70170_p.func_147437_c(this.spawnPosition.field_71574_a, this.spawnPosition.field_71572_b, this.spawnPosition.field_71573_c) || this.spawnPosition.field_71572_b < 1))
        this.spawnPosition = null; 
      if (this.spawnPosition == null || this.field_70146_Z.nextInt(30) == 0 || this.spawnPosition
        .func_71569_e((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) < 4.0F)
        this
          
          .spawnPosition = new ChunkCoordinates((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7)); 
      double d0 = this.spawnPosition.field_71574_a + 0.5D - this.field_70165_t;
      double d1 = this.spawnPosition.field_71572_b + 0.1D - this.field_70163_u;
      double d2 = this.spawnPosition.field_71573_c + 0.5D - this.field_70161_v;
      this.field_70159_w += (Math.signum(d0) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
      this.field_70181_x += (Math.signum(d1) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
      this.field_70179_y += (Math.signum(d2) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
      float f = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / Math.PI) - 90.0F;
      float f1 = MathHelper.func_76142_g(f - this.field_70177_z);
      this.field_70701_bs = 0.5F;
      this.field_70177_z += f1;
      if (this.field_70146_Z.nextInt(100) == 0 && this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), (int)this.field_70163_u + 1, 
          MathHelper.func_76128_c(this.field_70161_v)).func_149721_r())
        setIsBatHanging(true); 
    } 
  }
  
  protected boolean func_70041_e_() {
    return false;
  }
  
  protected void func_70069_a(float p_70069_1_) {}
  
  protected void func_70064_a(double p_70064_1_, boolean p_70064_3_) {}
  
  public boolean func_145773_az() {
    return true;
  }
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    if (func_85032_ar())
      return false; 
    if (!this.field_70170_p.field_72995_K && getIsBatHanging())
      setIsBatHanging(false); 
    return super.func_70097_a(p_70097_1_, p_70097_2_);
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_) {
    super.func_70037_a(p_70037_1_);
    this.field_70180_af.func_75692_b(16, Byte.valueOf(p_70037_1_.func_74771_c("BatFlags")));
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    super.func_70014_b(p_70014_1_);
    p_70014_1_.func_74774_a("BatFlags", this.field_70180_af.func_75683_a(16));
  }
  
  public boolean func_70601_bi() {
    int i = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
    if (i >= 63)
      return false; 
    int j = MathHelper.func_76128_c(this.field_70165_t);
    int k = MathHelper.func_76128_c(this.field_70161_v);
    int l = this.field_70170_p.func_72957_l(j, i, k);
    byte b0 = 4;
    Calendar calendar = this.field_70170_p.func_83015_S();
    if ((calendar.get(2) + 1 != 10 || calendar.get(5) < 20) && (calendar
      .get(2) + 1 != 11 || calendar.get(5) > 3)) {
      if (this.field_70146_Z.nextBoolean())
        return false; 
    } else {
      b0 = 7;
    } 
    return (l > this.field_70146_Z.nextInt(b0)) ? false : super.func_70601_bi();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityNervousBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */