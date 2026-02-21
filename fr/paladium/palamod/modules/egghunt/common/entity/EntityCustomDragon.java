package fr.paladium.palamod.modules.egghunt.common.entity;

import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.achievements.types.EggHuntActionAchievement;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerNameInput;
import fr.paladium.palamod.modules.egghunt.utils.PERegisterer;
import fr.paladium.palamod.modules.end.server.manager.EndManager;
import fr.paladium.palamod.modules.end.server.utils.EndState;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.io.IOException;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import retrofit2.Response;

public class EntityCustomDragon extends EntityLiving implements IBossDisplayData, IEntityMultiPart, IMob {
  private static int targetRadius = 50;
  
  private int homeX;
  
  private int homeY;
  
  private int homeZ;
  
  public double targetX;
  
  public double targetY;
  
  public double targetZ;
  
  public double[][] ringBuffer = new double[64][3];
  
  public int ringBufferIndex = -1;
  
  public EntityDragonPart[] dragonPartArray;
  
  public EntityDragonPart dragonPartHead;
  
  public EntityDragonPart dragonPartBody;
  
  public EntityDragonPart dragonPartTail1;
  
  public EntityDragonPart dragonPartTail2;
  
  public EntityDragonPart dragonPartTail3;
  
  public EntityDragonPart dragonPartWing1;
  
  public EntityDragonPart dragonPartWing2;
  
  public float prevAnimTime;
  
  public float animTime;
  
  public boolean forceNewTarget;
  
  public boolean slowed;
  
  private Entity target;
  
  public int deathTicks;
  
  public EntityEnderCrystal healingEnderCrystal;
  
  private EntityPlayer lastAttackingPlayer;
  
  public EntityCustomDragon(World world) {
    super(world);
    this.dragonPartArray = new EntityDragonPart[] { this.dragonPartHead = new EntityDragonPart(this, "head", 6.0F, 6.0F), this.dragonPartBody = new EntityDragonPart(this, "body", 8.0F, 8.0F), this.dragonPartTail1 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail2 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail3 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartWing1 = new EntityDragonPart(this, "wing", 4.0F, 4.0F), this.dragonPartWing2 = new EntityDragonPart(this, "wing", 4.0F, 4.0F) };
    func_70606_j(func_110138_aP());
    func_70105_a(16.0F, 8.0F);
    this.field_70145_X = true;
    this.field_70178_ae = true;
    this.targetY = 100.0D;
    this.field_70158_ak = true;
  }
  
  public void setHome(int x, int y, int z) {
    this.homeX = x;
    this.homeY = y;
    this.homeZ = z;
    func_70012_b(x, y, z, this.field_70170_p.field_73012_v.nextFloat() * 360.0F, 0.0F);
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(250.0D);
  }
  
  public void func_70109_d(NBTTagCompound p_70109_1_) {
    super.func_70109_d(p_70109_1_);
    p_70109_1_.func_74768_a("homeX", this.homeX);
    p_70109_1_.func_74768_a("homeY", this.homeY);
    p_70109_1_.func_74768_a("homeZ", this.homeZ);
  }
  
  public void func_70020_e(NBTTagCompound p_70020_1_) {
    super.func_70020_e(p_70020_1_);
    this.homeX = p_70020_1_.func_74762_e("homeX");
    this.homeY = p_70020_1_.func_74762_e("homeY");
    this.homeZ = p_70020_1_.func_74762_e("homeZ");
  }
  
  private void attackEntitiesInList(List<?> p_70971_1_) {
    for (int i = 0; i < p_70971_1_.size(); i++) {
      Entity entity = (Entity)p_70971_1_.get(i);
      if (entity instanceof EntityLivingBase)
        entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 16.0F); 
    } 
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
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (!this.field_70170_p.field_72995_K && !EndManager.getInstance().isActive())
      this.homeY = this.field_70170_p.func_72976_f(this.homeX, this.homeZ); 
  }
  
  public void func_70636_d() {
    if (this.field_70170_p.field_72995_K) {
      float f = MathHelper.func_76134_b(this.animTime * 3.1415927F * 2.0F);
      float f1 = MathHelper.func_76134_b(this.prevAnimTime * 3.1415927F * 2.0F);
      if (f1 <= -0.3F && f >= -0.3F)
        this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "mob.enderdragon.wings", 5.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.3F, false); 
    } 
    this.prevAnimTime = this.animTime;
    if (func_110143_aJ() <= 0.0F) {
      float f = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
      float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
      float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
      this.field_70170_p.func_72869_a("largeexplode", this.field_70165_t + f, this.field_70163_u + 2.0D + f1, this.field_70161_v + f2, 0.0D, 0.0D, 0.0D);
    } else {
      float f = 0.2F / (MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 10.0F + 1.0F);
      f *= (float)Math.pow(2.0D, this.field_70181_x);
      if (this.slowed) {
        this.animTime += f * 0.5F;
      } else {
        this.animTime += f;
      } 
      this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
      if (this.ringBufferIndex < 0)
        for (int i = 0; i < this.ringBuffer.length; i++) {
          this.ringBuffer[i][0] = this.field_70177_z;
          this.ringBuffer[i][1] = this.field_70163_u;
        }  
      if (++this.ringBufferIndex == this.ringBuffer.length)
        this.ringBufferIndex = 0; 
      this.ringBuffer[this.ringBufferIndex][0] = this.field_70177_z;
      this.ringBuffer[this.ringBufferIndex][1] = this.field_70163_u;
      if (this.field_70170_p.field_72995_K) {
        if (this.field_70716_bi > 0) {
          double d10 = this.field_70165_t + (this.field_70709_bj - this.field_70165_t) / this.field_70716_bi;
          double d0 = this.field_70163_u + (this.field_70710_bk - this.field_70163_u) / this.field_70716_bi;
          double d1 = this.field_70161_v + (this.field_110152_bk - this.field_70161_v) / this.field_70716_bi;
          double d2 = MathHelper.func_76138_g(this.field_70712_bm - this.field_70177_z);
          this.field_70177_z = (float)(this.field_70177_z + d2 / this.field_70716_bi);
          this.field_70125_A = (float)(this.field_70125_A + (this.field_70705_bn - this.field_70125_A) / this.field_70716_bi);
          this.field_70716_bi--;
          func_70107_b(d10, d0, d1);
          func_70101_b(this.field_70177_z, this.field_70125_A);
        } 
      } else {
        double d10 = this.targetX - this.field_70165_t;
        double d0 = this.targetY - this.field_70163_u;
        double d1 = this.targetZ - this.field_70161_v;
        double d2 = d10 * d10 + d0 * d0 + d1 * d1;
        if (this.target != null) {
          this.targetX = this.target.field_70165_t;
          this.targetZ = this.target.field_70161_v;
          double d3 = this.targetX - this.field_70165_t;
          double d5 = this.targetZ - this.field_70161_v;
          double d7 = Math.sqrt(d3 * d3 + d5 * d5);
          double d8 = 0.4000000059604645D + d7 / 80.0D - 1.0D;
          if (d8 > 10.0D)
            d8 = 10.0D; 
          this.targetY = this.target.field_70121_D.field_72338_b + d8;
        } else {
          this.targetX += this.field_70146_Z.nextGaussian() * 2.0D;
          this.targetZ += this.field_70146_Z.nextGaussian() * 2.0D;
        } 
        if (this.forceNewTarget || d2 < 100.0D || d2 > 22500.0D || this.field_70123_F || this.field_70124_G)
          setNewTarget(); 
        d0 /= MathHelper.func_76133_a(d10 * d10 + d1 * d1);
        float f14 = 0.6F;
        if (d0 < -f14)
          d0 = -f14; 
        if (d0 > f14)
          d0 = f14; 
        this.field_70181_x += d0 * 0.10000000149011612D;
        this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
        double d4 = 180.0D - Math.atan2(d10, d1) * 180.0D / Math.PI;
        double d6 = MathHelper.func_76138_g(d4 - this.field_70177_z);
        if (d6 > 50.0D)
          d6 = 50.0D; 
        if (d6 < -50.0D)
          d6 = -50.0D; 
        Vec3 vec3 = Vec3.func_72443_a(this.targetX - this.field_70165_t, this.targetY - this.field_70163_u, this.targetZ - this.field_70161_v).func_72432_b();
        Vec3 vec32 = Vec3.func_72443_a(MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F), this.field_70181_x, -MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F)).func_72432_b();
        float f5 = (float)(vec32.func_72430_b(vec3) + 0.5D) / 1.5F;
        if (f5 < 0.0F)
          f5 = 0.0F; 
        this.field_70704_bt *= 0.8F;
        float f6 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0F + 1.0F;
        double d9 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0D + 1.0D;
        if (d9 > 40.0D)
          d9 = 40.0D; 
        this.field_70704_bt = (float)(this.field_70704_bt + d6 * 0.699999988079071D / d9 / f6);
        this.field_70177_z += this.field_70704_bt * 0.1F;
        float f7 = (float)(2.0D / (d9 + 1.0D));
        float f8 = 0.06F;
        func_70060_a(0.0F, -1.0F, 0.06F * (f5 * f7 + 1.0F - f7));
        if (this.slowed) {
          func_70091_d(this.field_70159_w * 0.800000011920929D, this.field_70181_x * 0.800000011920929D, this.field_70179_y * 0.800000011920929D);
        } else {
          func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        } 
        Vec3 vec31 = Vec3.func_72443_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72432_b();
        float f9 = (float)(vec31.func_72430_b(vec32) + 1.0D) / 2.0F;
        f9 = 0.8F + 0.15F * f9;
        this.field_70159_w *= f9;
        this.field_70179_y *= f9;
        this.field_70181_x *= 0.9100000262260437D;
      } 
      this.field_70761_aq = this.field_70177_z;
      this.dragonPartHead.field_70130_N = this.dragonPartHead.field_70131_O = 3.0F;
      this.dragonPartTail1.field_70130_N = this.dragonPartTail1.field_70131_O = 2.0F;
      this.dragonPartTail2.field_70130_N = this.dragonPartTail2.field_70131_O = 2.0F;
      this.dragonPartTail3.field_70130_N = this.dragonPartTail3.field_70131_O = 2.0F;
      this.dragonPartBody.field_70131_O = 3.0F;
      this.dragonPartBody.field_70130_N = 5.0F;
      this.dragonPartWing1.field_70131_O = 2.0F;
      this.dragonPartWing1.field_70130_N = 4.0F;
      this.dragonPartWing2.field_70131_O = 3.0F;
      this.dragonPartWing2.field_70130_N = 4.0F;
      float f1 = (float)(getMovementOffsets(5, 1.0F)[1] - getMovementOffsets(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
      float f2 = MathHelper.func_76134_b(f1);
      float f10 = -MathHelper.func_76126_a(f1);
      float f3 = this.field_70177_z * 3.1415927F / 180.0F;
      float f11 = MathHelper.func_76126_a(f3);
      float f4 = MathHelper.func_76134_b(f3);
      this.dragonPartBody.func_70071_h_();
      this.dragonPartBody.func_70012_b(this.field_70165_t + (f11 * 0.5F), this.field_70163_u, this.field_70161_v - (f4 * 0.5F), 0.0F, 0.0F);
      this.dragonPartWing1.func_70071_h_();
      this.dragonPartWing1.func_70012_b(this.field_70165_t + (f4 * 4.5F), this.field_70163_u + 2.0D, this.field_70161_v + (f11 * 4.5F), 0.0F, 0.0F);
      this.dragonPartWing2.func_70071_h_();
      this.dragonPartWing2.func_70012_b(this.field_70165_t - (f4 * 4.5F), this.field_70163_u + 2.0D, this.field_70161_v - (f11 * 4.5F), 0.0F, 0.0F);
      if (!this.field_70170_p.field_72995_K && this.field_70737_aN == 0) {
        collideWithEntities(this.field_70170_p.func_72839_b((Entity)this, this.dragonPartWing1.field_70121_D.func_72314_b(4.0D, 2.0D, 4.0D).func_72317_d(0.0D, -2.0D, 0.0D)));
        collideWithEntities(this.field_70170_p.func_72839_b((Entity)this, this.dragonPartWing2.field_70121_D.func_72314_b(4.0D, 2.0D, 4.0D).func_72317_d(0.0D, -2.0D, 0.0D)));
        attackEntitiesInList(this.field_70170_p.func_72839_b((Entity)this, this.dragonPartHead.field_70121_D.func_72314_b(1.0D, 1.0D, 1.0D)));
      } 
      double[] adouble1 = getMovementOffsets(5, 1.0F);
      double[] adouble = getMovementOffsets(0, 1.0F);
      float f12 = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F - this.field_70704_bt * 0.01F);
      float f13 = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F - this.field_70704_bt * 0.01F);
      this.dragonPartHead.func_70071_h_();
      this.dragonPartHead.func_70012_b(this.field_70165_t + (f12 * 5.5F * f2), this.field_70163_u + (adouble[1] - adouble1[1]) * 1.0D + (f10 * 5.5F), this.field_70161_v - (f13 * 5.5F * f2), 0.0F, 0.0F);
      for (int j = 0; j < 3; j++) {
        EntityDragonPart entitydragonpart = null;
        if (j == 0)
          entitydragonpart = this.dragonPartTail1; 
        if (j == 1)
          entitydragonpart = this.dragonPartTail2; 
        if (j == 2)
          entitydragonpart = this.dragonPartTail3; 
        double[] adouble2 = getMovementOffsets(12 + j * 2, 1.0F);
        float f14 = this.field_70177_z * 3.1415927F / 180.0F + simplifyAngle(adouble2[0] - adouble1[0]) * 3.1415927F / 180.0F * 1.0F;
        float f15 = MathHelper.func_76126_a(f14);
        float f16 = MathHelper.func_76134_b(f14);
        float f17 = 1.5F;
        float f18 = (j + 1) * 2.0F;
        entitydragonpart.func_70071_h_();
        entitydragonpart.func_70012_b(this.field_70165_t - ((f11 * 1.5F + f15 * f18) * f2), this.field_70163_u + (adouble2[1] - adouble1[1]) * 1.0D - ((f18 + 1.5F) * f10) + 1.5D, this.field_70161_v + ((f4 * 1.5F + f16 * f18) * f2), 0.0F, 0.0F);
      } 
    } 
  }
  
  private void collideWithEntities(List<?> p_70970_1_) {}
  
  private void setNewTarget() {
    targetRadius = 45;
    this.forceNewTarget = false;
    if (this.field_70146_Z.nextInt(2) == 0 && !this.field_70170_p.field_73010_i.isEmpty()) {
      this.target = this.field_70170_p.field_73010_i.get(this.field_70146_Z.nextInt(this.field_70170_p.field_73010_i.size()));
      if (this.target.func_70011_f(this.homeX, this.homeY, this.homeZ) > targetRadius) {
        int i = 0;
        do {
          this.target = this.field_70170_p.field_73010_i.get(i);
          i++;
        } while (this.target.func_70011_f(this.homeX, this.homeY, this.homeZ) > targetRadius && i < this.field_70170_p.field_73010_i.size());
        if (this.target.func_70011_f(this.homeX, this.homeY, this.homeZ) > targetRadius)
          this.target = null; 
      } 
    } 
    if (this.target == null) {
      boolean flag = false;
      do {
        this.targetX = (this.field_70146_Z.nextFloat() * targetRadius - (targetRadius / 2) + this.homeX);
        this.targetY = (this.field_70146_Z.nextFloat() * 50.0F + this.homeY);
        this.targetZ = (this.field_70146_Z.nextFloat() * targetRadius - (targetRadius / 2) + this.homeZ);
        double d0 = this.field_70165_t - this.targetX;
        double d1 = this.field_70163_u - this.targetY;
        double d2 = this.field_70161_v - this.targetZ;
        flag = (d0 * d0 + d1 * d1 + d2 * d2 > 30.0D);
      } while (!flag);
      this.target = null;
    } 
  }
  
  private float simplifyAngle(double p_70973_1_) {
    return (float)MathHelper.func_76138_g(p_70973_1_);
  }
  
  public boolean func_70965_a(EntityDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_) {
    if (p_70965_1_ != this.dragonPartHead)
      p_70965_3_ = p_70965_3_ / 4.0F + 1.0F; 
    float f1 = this.field_70177_z * 3.1415927F / 180.0F;
    float f2 = MathHelper.func_76126_a(f1);
    float f3 = MathHelper.func_76134_b(f1);
    this.targetX = this.field_70165_t + (f2 * 5.0F) + ((this.field_70146_Z.nextFloat() - 0.5F) * 2.0F);
    this.targetY = this.field_70163_u + (this.field_70146_Z.nextFloat() * 3.0F) + 1.0D;
    this.targetZ = this.field_70161_v - (f3 * 5.0F) + ((this.field_70146_Z.nextFloat() - 0.5F) * 2.0F);
    this.target = null;
    if (p_70965_2_.func_76346_g() instanceof EntityPlayer || p_70965_2_.func_94541_c())
      func_82195_e(p_70965_2_, p_70965_3_); 
    return true;
  }
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    return false;
  }
  
  protected boolean func_82195_e(DamageSource source, float amount) {
    if (amount > 0.0F && source.func_76346_g() instanceof EntityPlayer) {
      EntityPlayer p = (EntityPlayer)source.func_76346_g();
      this.lastAttackingPlayer = (EntityPlayer)source.func_76346_g();
      EggHuntActionAchievement.performCheck(p, 1);
    } 
    return super.func_70097_a(source, amount);
  }
  
  public void func_70645_a(DamageSource source) {
    if (this.field_70170_p.field_72995_K)
      return; 
    if (EndManager.getInstance().isActive() && EndManager.getInstance().getState() == EndState.DRAGON) {
      func_145779_a(ItemsRegister.TRIXIUM, 450);
      func_145779_a(ItemsRegister.PALADIUM_INGOT, 350);
      ChatComponentText text = new ChatComponentText("§8[§dEnd§8] §eLe dragon a été vaincu.");
      if (this.lastAttackingPlayer != null) {
        text = new ChatComponentText("§8[§dEnd§8] §eLe dragon a été vaincu par §c" + this.lastAttackingPlayer.func_70005_c_());
      } else {
        text = new ChatComponentText("§8[§dEnd§8] §eLe dragon a été vaincu par un joueur inconnu.");
      } 
      EndManager.getInstance().startEgghunt(this.lastAttackingPlayer);
      ChatStyle style = new ChatStyle();
      style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bCliquez pour vous téléporter au §d/warp end")));
      style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp end"));
      text.func_150255_a(style);
      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
      for (Object playerObj : this.field_70170_p.field_73010_i) {
        if (!(playerObj instanceof EntityPlayerMP))
          continue; 
        EntityPlayerMP player = (EntityPlayerMP)playerObj;
        S29PacketSoundEffect packet = new S29PacketSoundEffect("mob.enderdragon.end", player.field_70165_t, player.field_70163_u, player.field_70161_v, func_70599_aP(), 4.0F);
        player.field_71135_a.func_147359_a((Packet)packet);
        ModuleTitle.getInstance().sendTitle(new MinecraftTitle("§dEvent END", "§cLe dragon est mort", 500L, 3000L, 500L), player);
      } 
      return;
    } 
    if (PEggHunt.status == null || PEggHunt.serverInput == null || !PEggHunt.data.isActive()) {
      System.err.println("[EggHunt] Status is null, removing egg from this server.");
      this.lastAttackingPlayer.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cCe dragon est inconnu à notre monde, aucune chasse n'aura donc lieu."));
      return;
    } 
    PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
          try {
            Response<Void> response = ApiServices.Http.getEggHuntService().setKiller(new EggHuntPlayerNameInput(this.lastAttackingPlayer)).execute();
            if (!response.isSuccessful()) {
              if (this.lastAttackingPlayer != null)
                this.lastAttackingPlayer.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cCe dragon est inconnu à notre monde, aucune chasse n'aura donc lieu.")); 
              System.err.println("[EggHunt] Error with API during dragon death, removing egg from this server. [" + response.code() + "]");
              if (PEggHunt.data.isActive())
                this.field_70170_p.func_147468_f(PEggHunt.config.getEggPosition().getX(), PEggHunt.config.getEggPosition().getY(), PEggHunt.config.getEggPosition().getZ()); 
            } else if (this.lastAttackingPlayer != null) {
              PEggHunt.status.setDragonKiller(this.lastAttackingPlayer.func_70005_c_());
              this.lastAttackingPlayer.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§bVous venez de vaincre le dragon, courez allez chercher l'oeuf qui vous attend en §e" + PEggHunt.config.getEggPosition().format()));
            } else {
              PEggHunt.status.setDragonKiller(null);
            } 
          } catch (IOException e) {
            if (this.lastAttackingPlayer != null)
              this.lastAttackingPlayer.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cCe dragon est inconnu à notre monde, aucune chasse n'aura donc lieu.")); 
            System.err.println("[EggHunt] Error with API during dragon death, removing egg from this server.");
            if (PEggHunt.data.isActive())
              this.field_70170_p.func_147468_f(PEggHunt.config.getEggPosition().getX(), PEggHunt.config.getEggPosition().getY(), PEggHunt.config.getEggPosition().getZ()); 
            e.printStackTrace();
          } 
        }0L);
    super.func_70645_a(source);
  }
  
  protected void func_70609_aI() {
    this.deathTicks++;
    if (this.deathTicks >= 180 && this.deathTicks <= 200) {
      float f = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
      float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
      float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
      this.field_70170_p.func_72869_a("hugeexplosion", this.field_70165_t + f, this.field_70163_u + 2.0D + f1, this.field_70161_v + f2, 0.0D, 0.0D, 0.0D);
    } 
    if (!this.field_70170_p.field_72995_K && 
      this.deathTicks > 150 && this.deathTicks % 5 == 0) {
      int i = 1000;
      while (i > 0) {
        int j = EntityXPOrb.func_70527_a(i);
        i -= j;
        this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
      } 
      func_145779_a(PERegisterer.dragonScales, 10);
    } 
    func_70091_d(0.0D, 0.10000000149011612D, 0.0D);
    this.field_70761_aq = this.field_70177_z += 20.0F;
    if (this.deathTicks == 200 && !this.field_70170_p.field_72995_K) {
      int i = 2000;
      while (i > 0) {
        int j = EntityXPOrb.func_70527_a(i);
        i -= j;
        this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
      } 
      func_70106_y();
    } 
  }
  
  protected void func_70623_bb() {}
  
  public Entity[] func_70021_al() {
    return (Entity[])this.dragonPartArray;
  }
  
  public boolean func_70067_L() {
    return false;
  }
  
  public World func_82194_d() {
    return this.field_70170_p;
  }
  
  protected String func_70639_aQ() {
    return "mob.enderdragon.growl";
  }
  
  protected String func_70621_aR() {
    return "mob.enderdragon.hit";
  }
  
  protected float func_70599_aP() {
    return 5.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\entity\EntityCustomDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */