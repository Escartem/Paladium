package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities;

import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMosquito extends EntityFlying {
  public static final String ENTITY_ID = "entityMosquito";
  
  public void setCourseChangeCooldown(int courseChangeCooldown) {
    this.courseChangeCooldown = courseChangeCooldown;
  }
  
  public void setWaypointX(double waypointX) {
    this.waypointX = waypointX;
  }
  
  public void setWaypointY(double waypointY) {
    this.waypointY = waypointY;
  }
  
  public void setWaypointZ(double waypointZ) {
    this.waypointZ = waypointZ;
  }
  
  public void setTarget(Entity target) {
    this.target = target;
  }
  
  private static final long MIN_ATTACK_INTERVAL = TimeUnit.SECONDS.toMillis(15L);
  
  private static final long MAX_ATTACK_INTERVAL = TimeUnit.SECONDS.toMillis(60L);
  
  private static final long TARGET_CHANGE_DURATION = TimeUnit.SECONDS.toMillis(10L);
  
  private int courseChangeCooldown;
  
  private double waypointX;
  
  private double waypointY;
  
  private double waypointZ;
  
  private Entity target;
  
  private long nextAttackMillis;
  
  private long lastTargetChangeMillis;
  
  public int getCourseChangeCooldown() {
    return this.courseChangeCooldown;
  }
  
  public double getWaypointX() {
    return this.waypointX;
  }
  
  public double getWaypointY() {
    return this.waypointY;
  }
  
  public double getWaypointZ() {
    return this.waypointZ;
  }
  
  public Entity getTarget() {
    return this.target;
  }
  
  public long getNextAttackMillis() {
    return this.nextAttackMillis;
  }
  
  public long getLastTargetChangeMillis() {
    return this.lastTargetChangeMillis;
  }
  
  public EntityMosquito(World world) {
    super(world);
    func_70105_a(0.7F, 0.7F);
    this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  public boolean isAttackedByHand(DamageSource source) {
    Entity entity = source.func_76346_g();
    if (!(entity instanceof EntityPlayer))
      return false; 
    EntityPlayer player = (EntityPlayer)entity;
    return (player.func_70694_bm() == null);
  }
  
  public boolean func_70097_a(DamageSource source, float f) {
    if (isAttackedByHand(source)) {
      func_70106_y();
      return true;
    } 
    return false;
  }
  
  protected void func_70665_d(DamageSource source, float f) {
    if (isAttackedByHand(source)) {
      func_70106_y();
      return;
    } 
    super.func_70665_d(source, f);
  }
  
  public void func_70100_b_(EntityPlayer player) {
    if (getTarget() != null && !player.field_71075_bZ.field_75098_d) {
      long now = System.currentTimeMillis();
      if (canAttack(now)) {
        setNextAttackMillis(now);
        player.func_70606_j(player.func_110143_aJ() - 2.0F);
      } 
    } 
  }
  
  public void func_70636_d() {
    long now = System.currentTimeMillis();
    if (getTarget() == null || !getTarget().func_70089_S() || canChangeTarget(now)) {
      AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(this.field_70165_t - 5.0D, this.field_70163_u - 5.0D, this.field_70161_v - 5.0D, this.field_70165_t + 5.0D, this.field_70163_u + 5.0D, this.field_70161_v + 5.0D);
      List entitys = this.field_70170_p.func_72839_b((Entity)this, scanAbove);
      for (Object entity : entitys) {
        if (entity instanceof EntityPlayer) {
          this.target = (Entity)entity;
          setLastTargetChangeMillis(now);
        } 
      } 
    } 
    super.func_70636_d();
    double x = this.waypointX - this.field_70165_t;
    double y = this.waypointY - this.field_70163_u;
    double z = this.waypointZ - this.field_70161_v;
    double d3 = x * x + y * y + z * z;
    if (getTarget() != null) {
      this
        .field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.target.field_70165_t, this.target.field_70161_v)) * 180.0F / 3.1415927F;
      if (d3 < 1.0D || d3 > 3600.0D) {
        this.waypointX = this.target.field_70165_t;
        this.waypointY = this.target.field_70163_u + 1.0D;
        this.waypointZ = this.target.field_70161_v;
      } 
    } 
    if (this.courseChangeCooldown-- <= 0) {
      this.courseChangeCooldown += this.field_70146_Z.nextInt(5) + 2;
      d3 = MathHelper.func_76133_a(d3);
      if (isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
        this.field_70159_w += x / d3 * 0.1D;
        this.field_70181_x += y / d3 * 0.1D;
        this.field_70179_y += z / d3 * 0.1D;
      } else {
        this.waypointX = this.field_70165_t;
        this.waypointY = this.field_70163_u;
        this.waypointZ = this.field_70161_v;
      } 
    } 
    this
      .field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / 3.1415927F;
  }
  
  public boolean canAttack(long now) {
    return (now >= this.nextAttackMillis);
  }
  
  public boolean canChangeTarget(long now) {
    return (now >= this.lastTargetChangeMillis + TARGET_CHANGE_DURATION);
  }
  
  public void setLastTargetChangeMillis(long now) {
    this.lastTargetChangeMillis = now;
  }
  
  public void setNextAttackMillis(long now) {
    this.nextAttackMillis = now + MIN_ATTACK_INTERVAL + this.field_70170_p.field_73012_v.nextInt((int)(MAX_ATTACK_INTERVAL - MIN_ATTACK_INTERVAL));
  }
  
  private boolean isCourseTraversable(double p_70790_1_, double p_70790_3_, double p_70790_5_, double p_70790_7_) {
    double d4 = (this.waypointX - this.field_70165_t) / p_70790_7_;
    double d5 = (this.waypointY - this.field_70163_u) / p_70790_7_;
    double d6 = (this.waypointZ - this.field_70161_v) / p_70790_7_;
    AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
    for (int i = 1; i < p_70790_7_; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      if (!this.field_70170_p.func_72945_a((Entity)this, axisalignedbb).isEmpty())
        return false; 
    } 
    return true;
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\entities\EntityMosquito.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */