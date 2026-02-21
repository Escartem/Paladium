package fr.paladium.palamod.modules.paladium.common.entities.projectiles;

import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.ExplosionSound;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class DynamiteNinjaEntity extends EntityArrow implements IThrowableEntity {
  public static final int NO_PICKUP = 0;
  
  public static final int PICKUP_ALL = 1;
  
  public static final int PICKUP_CREATIVE = 2;
  
  public static final int PICKUP_OWNER = 3;
  
  protected int xTile;
  
  protected int yTile;
  
  protected int zTile;
  
  protected Block inTile;
  
  protected int field_70253_h;
  
  protected boolean field_70254_i;
  
  public int pickupMode;
  
  protected int field_70252_j;
  
  protected int field_70257_an;
  
  public boolean beenInGround;
  
  public float extraDamage;
  
  public int knockBack;
  
  private int explodefuse;
  
  private boolean extinguished;
  
  public boolean stuck = true;
  
  public static int DEFAULT = 0;
  
  public static int NINJA = 1;
  
  public static int BIG = 2;
  
  int type;
  
  public DynamiteNinjaEntity(World world) {
    super(world);
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
    this.inTile = null;
    this.field_70253_h = 0;
    this.field_70254_i = false;
    this.field_70249_b = 0;
    this.field_70257_an = 0;
    this.field_70129_M = 0.0F;
    this.pickupMode = 0;
    setPickupMode(0);
    this.extinguished = false;
    this.explodefuse = 500;
    this.extraDamage = 0.0F;
    this.knockBack = 0;
    func_70105_a(0.5F, 0.5F);
  }
  
  public DynamiteNinjaEntity(World world, double d, double d1, double d2) {
    this(world);
    func_70107_b(d, d1, d2);
  }
  
  public DynamiteNinjaEntity(World world, EntityLivingBase entityliving, int i, int type) {
    this(world);
    this.type = type;
    this.field_70250_c = (Entity)entityliving;
    func_70012_b(entityliving.field_70165_t, entityliving.field_70163_u + entityliving.func_70047_e(), entityliving.field_70161_v, entityliving.field_70177_z, entityliving.field_70125_A);
    this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.141593F) * 0.16F);
    this.field_70163_u -= 0.1D;
    this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.141593F) * 0.16F);
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    this
      .field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.141593F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.141593F));
    this
      .field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.141593F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.141593F));
    this.field_70181_x = -MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.141593F);
    func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 0.7F, 4.0F);
    this.explodefuse = i;
  }
  
  protected void func_70088_a() {}
  
  public Entity getThrower() {
    return this.field_70250_c;
  }
  
  public void setThrower(Entity entity) {
    this.field_70250_c = entity;
  }
  
  protected void setPickupModeFromEntity(EntityLivingBase entityliving) {
    if (entityliving instanceof EntityPlayer) {
      if (((EntityPlayer)entityliving).field_71075_bZ.field_75098_d)
        setPickupMode(2); 
    } else {
      setPickupMode(0);
    } 
  }
  
  public void func_70186_c(double x, double y, double z, float speed, float deviation) {
    float f2 = MathHelper.func_76133_a(x * x + y * y + z * z);
    x /= f2;
    y /= f2;
    z /= f2;
    x += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * deviation;
    y += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * deviation;
    z += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * deviation;
    x *= speed;
    y *= speed;
    z *= speed;
    this.field_70159_w = x;
    this.field_70181_x = y;
    this.field_70179_y = z;
    float f3 = MathHelper.func_76133_a(x * x + z * z);
    this.field_70126_B = this.field_70177_z = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
    this.field_70127_C = this.field_70125_A = (float)(Math.atan2(y, f3) * 180.0D / Math.PI);
    this.field_70252_j = 0;
  }
  
  public void func_70016_h(double d, double d1, double d2) {
    this.field_70159_w = d;
    this.field_70181_x = d1;
    this.field_70179_y = d2;
    if (aimRotation() && this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
      float f = MathHelper.func_76133_a(d * d + d2 * d2);
      this.field_70126_B = this.field_70177_z = (float)(Math.atan2(d, d2) * 180.0D / Math.PI);
      this.field_70127_C = this.field_70125_A = (float)(Math.atan2(d1, f) * 180.0D / Math.PI);
      func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
      this.field_70252_j = 0;
    } 
  }
  
  public void func_70071_h_() {
    func_70030_z();
    if (!this.field_70254_i && !this.beenInGround) {
      this.field_70125_A -= 50.0F;
    } else {
      this.field_70125_A = 180.0F;
    } 
    if (func_70090_H() && !this.extinguished) {
      this.extinguished = true;
      if (this.type != NINJA)
        this.field_70170_p.func_72956_a((Entity)this, "random.fizz", 1.0F, 1.2F / (this.field_70146_Z
            .nextFloat() * 0.2F + 0.9F)); 
      for (int k = 0; k < 8; k++) {
        float f6 = 0.25F;
        this.field_70170_p.func_72869_a("explode", this.field_70165_t - this.field_70159_w * f6, this.field_70163_u - this.field_70181_x * f6, this.field_70161_v - this.field_70179_y * f6, this.field_70159_w, this.field_70181_x, this.field_70179_y);
      } 
    } 
    this.explodefuse--;
    if (this.explodefuse <= 0) {
      detonate();
      func_70106_y();
    } else if (this.explodefuse > 0) {
      this.field_70170_p.func_72869_a("smoke", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D);
    } 
  }
  
  public void func_70030_z() {
    super.func_70030_z();
    if (aimRotation()) {
      float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this
        .field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
      this
        .field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0D / Math.PI);
    } 
    Block i = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
    if (i != null) {
      i.func_149719_a((IBlockAccess)this.field_70170_p, this.xTile, this.yTile, this.zTile);
      AxisAlignedBB axisalignedbb = i.func_149668_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
      if (axisalignedbb != null && axisalignedbb
        .func_72318_a(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v)))
        this.field_70254_i = true; 
    } 
    if (this.field_70249_b > 0)
      this.field_70249_b--; 
    if (this.field_70254_i) {
      Block j = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
      int k = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
      if (j == this.inTile && k == this.field_70253_h) {
        this.field_70252_j++;
        int t = getMaxLifetime();
        if (t != 0 && this.field_70252_j >= t)
          func_70106_y(); 
      } else {
        this.field_70254_i = false;
        this.field_70159_w *= (this.field_70146_Z.nextFloat() * 0.2F);
        this.field_70181_x *= (this.field_70146_Z.nextFloat() * 0.2F);
        this.field_70179_y *= (this.field_70146_Z.nextFloat() * 0.2F);
        this.field_70252_j = 0;
        this.field_70257_an = 0;
      } 
      return;
    } 
    this.field_70257_an++;
    Vec3 vec3d = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    Vec3 vec3d1 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
    MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec3d, vec3d1, false, true, false);
    vec3d = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    vec3d1 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
    if (movingobjectposition != null)
      vec3d1 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c); 
    Entity entity = null;
    List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D
        .func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
    double d = 0.0D;
    for (int l = 0; l < list.size(); l++) {
      Entity entity1 = list.get(l);
      if (entity1.func_70067_L() && (entity1 != this.field_70250_c || this.field_70257_an >= 5)) {
        float f4 = 0.3F;
        AxisAlignedBB axisalignedbb1 = entity1.field_70121_D.func_72314_b(f4, f4, f4);
        MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec3d, vec3d1);
        if (movingobjectposition1 != null) {
          double d1 = vec3d.func_72438_d(movingobjectposition1.field_72307_f);
          if (d1 < d || d == 0.0D) {
            entity = entity1;
            d = d1;
          } 
        } 
      } 
    } 
    if (entity != null)
      movingobjectposition = new MovingObjectPosition(entity); 
    if (movingobjectposition != null)
      if (movingobjectposition.field_72308_g != null) {
        onEntityHit(movingobjectposition.field_72308_g);
      } else {
        onGroundHit(movingobjectposition);
      }  
    if (func_70241_g())
      for (int i1 = 0; i1 < 2; i1++)
        this.field_70170_p.func_72869_a("crit", this.field_70165_t + this.field_70159_w * i1 / 4.0D, this.field_70163_u + this.field_70181_x * i1 / 4.0D, this.field_70161_v + this.field_70179_y * i1 / 4.0D, -this.field_70159_w, -this.field_70181_x + 0.2D, -this.field_70179_y);  
    this.field_70165_t += this.field_70159_w;
    this.field_70163_u += this.field_70181_x;
    this.field_70161_v += this.field_70179_y;
    if (aimRotation()) {
      float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
      this
        .field_70125_A = (float)(Math.atan2(this.field_70181_x, f2) * 180.0D / Math.PI);
      for (; this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F);
      for (; this.field_70125_A - this.field_70127_C >= 180.0F; this.field_70127_C += 360.0F);
      for (; this.field_70177_z - this.field_70126_B < -180.0F; this.field_70126_B -= 360.0F);
      for (; this.field_70177_z - this.field_70126_B >= 180.0F; this.field_70126_B += 360.0F);
      this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
      this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
    } 
    float res = getAirResistance();
    float grav = getGravity();
    if (func_70090_H()) {
      this.beenInGround = true;
      for (int i1 = 0; i1 < 4; i1++) {
        float f6 = 0.25F;
        this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f6, this.field_70163_u - this.field_70181_x * f6, this.field_70161_v - this.field_70179_y * f6, this.field_70159_w, this.field_70181_x, this.field_70179_y);
      } 
      res *= 0.8080808F;
    } 
    this.field_70159_w *= res;
    this.field_70181_x *= res;
    this.field_70179_y *= res;
    this.field_70181_x -= grav;
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    func_145775_I();
  }
  
  public void onEntityHit(Entity entity) {
    bounceBack();
    applyEntityHitEffects(entity);
  }
  
  public void applyEntityHitEffects(Entity entity) {
    if (func_70027_ad() && !(entity instanceof net.minecraft.entity.monster.EntityEnderman))
      entity.func_70015_d(5); 
    if (entity instanceof EntityLivingBase) {
      EntityLivingBase entityliving = (EntityLivingBase)entity;
      if (this.knockBack > 0) {
        float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        if (f > 0.0F)
          entity.func_70024_g(this.field_70159_w * this.knockBack * 0.6D / f, 0.1D, this.field_70179_y * this.knockBack * 0.6D / f); 
      } 
      if (this.field_70250_c instanceof EntityLivingBase) {
        EnchantmentHelper.func_151384_a(entityliving, this.field_70250_c);
        EnchantmentHelper.func_151385_b((EntityLivingBase)this.field_70250_c, (Entity)entityliving);
      } 
      if (this.field_70250_c instanceof EntityPlayerMP && this.field_70250_c != entity && entity instanceof EntityPlayer)
        ((EntityPlayerMP)this.field_70250_c).field_71135_a
          .func_147359_a((Packet)new S2BPacketChangeGameState(6, 0.0F)); 
    } 
  }
  
  public void onGroundHit(MovingObjectPosition mop) {
    this.xTile = mop.field_72311_b;
    this.yTile = mop.field_72312_c;
    this.zTile = mop.field_72309_d;
    this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
    this.field_70159_w = (float)(mop.field_72307_f.field_72450_a - this.field_70165_t);
    this.field_70181_x = (float)(mop.field_72307_f.field_72448_b - this.field_70163_u);
    this.field_70179_y = (float)(mop.field_72307_f.field_72449_c - this.field_70161_v);
    float f1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
    this.field_70165_t -= this.field_70159_w / f1 * 0.05D;
    this.field_70163_u -= this.field_70181_x / f1 * 0.05D;
    this.field_70161_v -= this.field_70179_y / f1 * 0.05D;
    this.field_70159_w *= -0.2D;
    this.field_70179_y *= -0.2D;
    if (mop.field_72310_e == 1) {
      this.field_70254_i = true;
      this.beenInGround = true;
    } else {
      this.field_70254_i = false;
      if (this.type != NINJA)
        this.field_70170_p.func_72956_a((Entity)this, "random.fizz", 1.0F, 1.2F / (this.field_70146_Z
            .nextFloat() * 0.2F + 0.9F)); 
    } 
    if (this.inTile != null)
      this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, (Entity)this); 
  }
  
  private void detonate() {
    if (this.field_70163_u <= 0.0D)
      return; 
    float f = 3.0F;
    if (this.type == BIG)
      f = 4.5F; 
    if (this.type == DEFAULT)
      f = 2.5F; 
    if (this.type != NINJA) {
      this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, f, true);
    } else {
      silentExplosion(this.field_70170_p, (Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, f, false, true);
    } 
  }
  
  private void silentExplosion(World world, Entity p_72885_1_, double p_72885_2_, double p_72885_4_, double p_72885_6_, float p_72885_8_, boolean p_72885_9_, boolean p_72885_10_) {
    Explosion explosion = new Explosion(world, p_72885_1_, p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_);
    explosion.field_77286_a = p_72885_9_;
    explosion.field_82755_b = p_72885_10_;
    ((ExplosionSound)explosion).setHasSound(false);
    if (ForgeEventFactory.onExplosionStart(world, explosion))
      return; 
    explosion.func_77278_a();
    explosion.func_77279_a(true);
  }
  
  protected void bounceBack() {
    this.field_70159_w *= -0.1D;
    this.field_70181_x *= -0.1D;
    this.field_70179_y *= -0.1D;
    this.field_70177_z += 180.0F;
    this.field_70126_B += 180.0F;
    this.field_70257_an = 0;
  }
  
  public int getType() {
    return this.type;
  }
  
  public final double getTotalVelocity() {
    return Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
  }
  
  public boolean aimRotation() {
    return false;
  }
  
  public int getMaxLifetime() {
    return 1200;
  }
  
  public ItemStack getPickupItem() {
    return null;
  }
  
  public float getAirResistance() {
    return 0.99F;
  }
  
  public float getGravity() {
    return 0.05F;
  }
  
  public int getMaxArrowShake() {
    return 0;
  }
  
  public void playHitSound() {
    if (this.type != NINJA)
      this.field_70170_p.func_72956_a((Entity)this, "random.fizz", 1.0F, 1.2F / (this.field_70146_Z
          .nextFloat() * 0.2F + 0.9F)); 
  }
  
  public boolean canBeCritical() {
    return false;
  }
  
  public void func_70243_d(boolean flag) {
    if (canBeCritical())
      this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(flag ? 1 : 0))); 
  }
  
  public boolean func_70241_g() {
    return (canBeCritical() && this.field_70180_af.func_75683_a(16) != 0);
  }
  
  public void setExtraDamage(float f) {
    this.extraDamage = f;
  }
  
  public void func_70240_a(int i) {
    this.knockBack = i;
  }
  
  public void setPickupMode(int i) {
    this.pickupMode = i;
  }
  
  public int getPickupMode() {
    return this.pickupMode;
  }
  
  public boolean canPickup(EntityPlayer entityplayer) {
    if (this.pickupMode == 1)
      return true; 
    if (this.pickupMode == 2)
      return entityplayer.field_71075_bZ.field_75098_d; 
    if (this.pickupMode == 3)
      return (entityplayer == this.field_70250_c); 
    return false;
  }
  
  public void func_70100_b_(EntityPlayer entityplayer) {
    if (this.field_70254_i && this.field_70249_b <= 0 && 
      canPickup(entityplayer) && 
      !this.field_70170_p.field_72995_K) {
      ItemStack item = getPickupItem();
      if (item == null)
        return; 
      if ((this.pickupMode == 2 && entityplayer.field_71075_bZ.field_75098_d) || entityplayer.field_71071_by
        .func_70441_a(item)) {
        if (this.type != NINJA)
          this.field_70170_p.func_72956_a((Entity)this, "random.pop", 0.2F, ((this.field_70146_Z
              .nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F); 
        onItemPickup(entityplayer);
        func_70106_y();
      } 
    } 
  }
  
  protected void onItemPickup(EntityPlayer entityplayer) {
    entityplayer.func_71001_a((Entity)this, 1);
  }
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R() {
    return 0.0F;
  }
  
  protected boolean func_70041_e_() {
    return false;
  }
  
  public void func_70014_b(NBTTagCompound nbttagcompound) {
    nbttagcompound.func_74777_a("xTile", (short)this.xTile);
    nbttagcompound.func_74777_a("yTile", (short)this.yTile);
    nbttagcompound.func_74777_a("zTile", (short)this.zTile);
    nbttagcompound.func_74774_a("inTile", (byte)Block.func_149682_b(this.inTile));
    nbttagcompound.func_74774_a("inData", (byte)this.field_70253_h);
    nbttagcompound.func_74774_a("shake", (byte)this.field_70249_b);
    nbttagcompound.func_74757_a("inGround", this.field_70254_i);
    nbttagcompound.func_74757_a("beenInGround", this.beenInGround);
    nbttagcompound.func_74774_a("pickup", (byte)this.pickupMode);
    nbttagcompound.func_74774_a("fuse", (byte)this.explodefuse);
    nbttagcompound.func_74757_a("off", this.extinguished);
  }
  
  public void func_70037_a(NBTTagCompound nbttagcompound) {
    this.xTile = nbttagcompound.func_74765_d("xTile");
    this.yTile = nbttagcompound.func_74765_d("yTile");
    this.zTile = nbttagcompound.func_74765_d("zTile");
    this.inTile = Block.func_149729_e(nbttagcompound.func_74771_c("inTile") & 0xFF);
    this.field_70253_h = nbttagcompound.func_74771_c("inData") & 0xFF;
    this.field_70249_b = nbttagcompound.func_74771_c("shake") & 0xFF;
    this.field_70254_i = nbttagcompound.func_74767_n("inGround");
    this.beenInGround = nbttagcompound.func_74767_n("beenInGrond");
    this.pickupMode = nbttagcompound.func_74771_c("pickup");
    this.explodefuse = nbttagcompound.func_74771_c("fuse");
    this.extinguished = nbttagcompound.func_74767_n("off");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\entities\projectiles\DynamiteNinjaEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */