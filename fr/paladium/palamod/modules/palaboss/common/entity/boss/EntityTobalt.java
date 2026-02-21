package fr.paladium.palamod.modules.palaboss.common.entity.boss;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.palaboss.common.entity.ia.EntityAIGroundAttack;
import fr.paladium.palamod.modules.palaboss.common.network.MessageSyncTobaltToClient;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityTobalt extends EntityBossBase implements IRangedAttackMob {
  private boolean inCharge;
  
  @SideOnly(Side.CLIENT)
  private boolean clientStunt;
  
  private boolean wasStunt;
  
  public List<BlockPos> placedEruptionBlock = new ArrayList<>();
  
  public EntityTobalt(World world) {
    super(world);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIGroundAttack(this, 1.0D, 20, 30, 5.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
  }
  
  protected void func_70088_a() {
    this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
    super.func_70088_a();
  }
  
  public void func_70015_d(int p_70015_1_) {}
  
  protected void func_70044_A() {}
  
  public void func_70071_h_() {
    if (!this.field_70170_p.field_72995_K && 
      func_70089_S()) {
      if (this.field_70173_aa % 20 == 0)
        for (int ox = -3; ox < 4; ox++) {
          for (int oy = -4; oy < 5; oy++) {
            for (int oz = -3; oz < 4; oz++) {
              BlockPos pos = new BlockPos(this.field_70165_t + ox, this.field_70163_u + oy, this.field_70161_v + oz);
              if (this.placedEruptionBlock.contains(pos))
                this.field_70170_p.func_147468_f(pos.getX(), pos.getY(), pos.getZ()); 
            } 
          } 
        }  
      if (this.inCharge) {
        checkCollisions();
        for (Entity entity : getEntitiesIn(3)) {
          if (entity instanceof EntityLivingBase && !(entity instanceof EntityTobalt)) {
            entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 
                getAttributes().getAttributeFloat("chargeDamages", 20.0F));
            setCharge(false);
            updateDatas();
          } 
        } 
      } 
      if (this.wasStunt && func_70660_b(Potion.field_76421_d) == null) {
        updateDatas();
        this.wasStunt = false;
      } 
      checkCollisions();
    } 
    super.func_70071_h_();
  }
  
  protected void checkCollisions() {
    if (!this.field_70170_p.field_72995_K) {
      Vec3 playerLookVector = func_70040_Z();
      double x = this.field_70165_t + 5.0D * playerLookVector.field_72450_a;
      double z = this.field_70161_v + 5.0D * playerLookVector.field_72449_c;
      double y = this.field_70163_u;
      Block block = this.field_70170_p.func_147439_a((int)x, (int)y, (int)z);
      if (this.inCharge) {
        if (!(block instanceof net.minecraft.block.BlockAir) && !(block instanceof net.minecraft.block.BlockGrass) && !(block instanceof net.minecraft.block.BlockDirt)) {
          float stuntTime = block.func_149712_f(this.field_70170_p, (int)x, (int)y, (int)z) / 2.0F;
          this.field_70170_p.func_147468_f((int)x, (int)y, (int)z);
          setCharge(false);
          int potionEffectTime = (int)(stuntTime * 20.0F);
          func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, potionEffectTime, 10, true));
          updateDatas();
          this.wasStunt = true;
        } 
      } else if ((block instanceof net.minecraft.block.BlockObsidian || block instanceof net.minecraft.block.BlockStone) && 
        this.field_70170_p.func_72805_g((int)x, (int)y, (int)z) == 1) {
        this.field_70170_p.func_147468_f((int)x, (int)y, (int)z);
      } 
    } 
  }
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    if (p_70097_1_.func_76346_g() != null && !isStunt()) {
      Entity sourceDamages = p_70097_1_.func_76364_f();
      if (sourceDamages instanceof EntityTobalt)
        return false; 
      if (sourceDamages instanceof EntityArrow) {
        EntityArrow arrow = (EntityArrow)p_70097_1_.func_76364_f();
        sourceDamages = arrow.field_70250_c;
      } 
      if (sourceDamages == null)
        return false; 
      sourceDamages.func_70097_a(DamageSource.func_92087_a((Entity)this), 
          getAttributes().getAttributeFloat("thornDamages", 20.0F));
    } 
    if (this.inCharge)
      return defaultAttackEntityFrom(p_70097_1_, p_70097_2_); 
    return super.func_70097_a(p_70097_1_, p_70097_2_);
  }
  
  public boolean defaultAttackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
    if (ForgeHooks.onLivingAttack((EntityLivingBase)this, p_70097_1_, p_70097_2_))
      return false; 
    if (func_85032_ar())
      return false; 
    if (this.field_70170_p.field_72995_K)
      return false; 
    this.field_70708_bq = 0;
    if (func_110143_aJ() <= 0.0F)
      return false; 
    if (p_70097_1_.func_76347_k() && func_70644_a(Potion.field_76426_n))
      return false; 
    if ((p_70097_1_ == DamageSource.field_82728_o || p_70097_1_ == DamageSource.field_82729_p) && 
      func_71124_b(4) != null) {
      func_71124_b(4).func_77972_a(
          (int)(p_70097_2_ * 4.0F + this.field_70146_Z.nextFloat() * p_70097_2_ * 2.0F), (EntityLivingBase)this);
      p_70097_2_ *= 0.75F;
    } 
    this.field_70721_aZ = 1.5F;
    boolean flag = true;
    if (this.field_70172_ad > this.field_70771_an / 2.0F) {
      if (p_70097_2_ <= this.field_110153_bc)
        return false; 
      func_70665_d(p_70097_1_, p_70097_2_ - this.field_110153_bc);
      this.field_110153_bc = p_70097_2_;
      flag = false;
    } else {
      this.field_110153_bc = p_70097_2_;
      this.field_70735_aL = func_110143_aJ();
      this.field_70172_ad = this.field_70771_an;
      func_70665_d(p_70097_1_, p_70097_2_);
      this.field_70737_aN = this.field_70738_aO = 10;
    } 
    this.field_70739_aP = 0.0F;
    Entity entity = p_70097_1_.func_76346_g();
    if (entity != null) {
      if (entity instanceof EntityLivingBase);
      if (entity instanceof EntityPlayer) {
        this.field_70718_bc = 100;
        this.field_70717_bb = (EntityPlayer)entity;
      } else if (entity instanceof EntityTameable) {
        EntityTameable entitywolf = (EntityTameable)entity;
        if (entitywolf.func_70909_n()) {
          this.field_70718_bc = 100;
          this.field_70717_bb = null;
        } 
      } 
    } 
    if (flag) {
      this.field_70170_p.func_72960_a((Entity)this, (byte)2);
      if (p_70097_1_ != DamageSource.field_76369_e)
        func_70018_K(); 
      if (entity != null) {
        double d1 = entity.field_70165_t - this.field_70165_t;
        double d0;
        for (d0 = entity.field_70161_v - this.field_70161_v; d1 * d1 + d0 * d0 < 1.0E-4D; 
          d0 = (Math.random() - Math.random()) * 0.01D)
          d1 = (Math.random() - Math.random()) * 0.01D; 
        this.field_70739_aP = (float)(Math.atan2(d0, d1) * 180.0D / Math.PI) - this.field_70177_z;
      } else {
        this.field_70739_aP = ((int)(Math.random() * 2.0D) * 180);
      } 
    } 
    if (func_110143_aJ() <= 0.0F) {
      String s = func_70673_aS();
      if (flag && s != null)
        func_85030_a(s, func_70599_aP(), func_70647_i()); 
      func_70645_a(p_70097_1_);
    } else {
      String s = func_70621_aR();
      if (flag && s != null)
        func_85030_a(s, func_70599_aP(), func_70647_i()); 
    } 
    return true;
  }
  
  public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {}
  
  public List<Entity> getEntitiesIn(int radius) {
    return this.field_70170_p.func_72872_a(Entity.class, this.field_70121_D
        .func_72314_b(radius, radius, radius));
  }
  
  public float func_70689_ay() {
    return (this.inCharge && !isStunt()) ? (super.func_70689_ay() * 3.0F) : super.func_70689_ay();
  }
  
  protected boolean func_70650_aV() {
    boolean stunt = isStunt();
    return !stunt;
  }
  
  public boolean isStunt() {
    if (!func_70644_a(Potion.field_76421_d))
      return false; 
    return (func_70660_b(Potion.field_76421_d).func_76458_c() == 10);
  }
  
  protected boolean func_70692_ba() {
    return false;
  }
  
  public float getEntityWidth() {
    return 5.5F;
  }
  
  public float getEntityHeight() {
    return 4.2F;
  }
  
  public boolean isInCharge() {
    return this.inCharge;
  }
  
  public void setCharge(boolean charge) {
    if (this.inCharge == charge)
      return; 
    this.inCharge = charge;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isClientStunt() {
    return this.clientStunt;
  }
  
  public void updateDatas() {
    MessageSyncTobaltToClient message = new MessageSyncTobaltToClient(func_145782_y(), this.inCharge, isStunt());
    PPalaBoss.network.sendToAllAround((IMessage)message, new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 100.0D));
  }
  
  @SideOnly(Side.CLIENT)
  public void syncTobaltData(MessageSyncTobaltToClient message) {
    this.inCharge = message.inCharge;
    this.clientStunt = message.isStunt;
  }
  
  public String name() {
    return "Tobalt";
  }
  
  public void func_70645_a(DamageSource p_70645_1_) {
    for (BlockPos pos : this.placedEruptionBlock)
      this.field_70170_p.func_147468_f(pos.getX(), pos.getY(), pos.getZ()); 
    super.func_70645_a(p_70645_1_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\boss\EntityTobalt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */