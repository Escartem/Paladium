package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities;

import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWorker extends EntityFlying {
  public static final String ENTITY_ID = "entityWorker";
  
  public static final String ENTITY_NAME = "Steve";
  
  private static final long MAX_DISTANCE_TO_TARGET = 10L;
  
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
  
  public void setTarget(EntityPlayer target) {
    this.target = target;
  }
  
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  private static final long EXPIRATION_DURATION = TimeUnit.SECONDS.toMillis(30L);
  
  private int courseChangeCooldown;
  
  private double waypointX;
  
  private double waypointY;
  
  private double waypointZ;
  
  private EntityPlayer target;
  
  private long expirationMillis;
  
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
  
  public EntityPlayer getTarget() {
    return this.target;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public EntityWorker(World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
  }
  
  public EntityWorker(World world, EntityPlayerMP player) {
    super(world);
    func_70105_a(0.6F, 1.8F);
    this.target = (EntityPlayer)player;
    this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.expirationMillis = System.currentTimeMillis() + EXPIRATION_DURATION;
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  public boolean func_70097_a(DamageSource source, float f) {
    return false;
  }
  
  protected void func_70665_d(DamageSource source, float f) {}
  
  public void func_70100_b_(EntityPlayer player) {
    super.func_70100_b_(player);
  }
  
  public String func_70005_c_() {
    return "Steve";
  }
  
  public void func_70636_d() {
    long now = System.currentTimeMillis();
    if (!this.field_70170_p.field_72995_K && isExpired(now)) {
      func_70106_y();
      return;
    } 
    super.func_70636_d();
    double x = this.waypointX - this.field_70165_t;
    double y = this.waypointY - this.field_70163_u;
    double z = this.waypointZ - this.field_70161_v;
    double d3 = x * x + y * y + z * z;
    if (this.target == null || this.target.field_70128_L)
      return; 
    if (func_70032_d((Entity)this.target) > 10.0F) {
      func_70634_a(this.target.field_70165_t, this.target.field_70163_u, this.target.field_70161_v);
      return;
    } 
    if (this.target != null) {
      this
        .field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.target.field_70165_t, this.target.field_70161_v)) * 180.0F / 3.1415927F;
      if (d3 < 1.0D || d3 > 3600.0D) {
        this.waypointX = this.target.field_70165_t;
        this.waypointY = this.target.field_70163_u;
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
  
  private boolean isExpired(long now) {
    return (now > this.expirationMillis);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\entities\EntityWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */