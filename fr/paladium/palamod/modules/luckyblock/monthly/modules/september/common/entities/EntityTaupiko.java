package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.TaupikoUI;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.Interval;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityTaupiko extends EntityMob {
  public static final String ENTITY_ID = "entityTaupiko";
  
  private static final int CUBOID_RADIUS = 2;
  
  private static final long JUMP_PERIOD_MILLIS = TimeUnit.SECONDS.toMillis(1L);
  
  private static final long COOLDOWN_PER_USAGES_MILLIS = TimeUnit.MINUTES.toMillis(5L);
  
  private static final String NOT_OWNER_MESSAGE = "&cVous n'êtes pas le propriétaire de ce taupiko.";
  
  public static final Interval JUMP_INTERVAL = Interval.of(1, 5);
  
  public static final String EXPIRATION_MILLIS_NBT_TAG = "expirationMillis";
  
  public static final String LAST_JUMP_MILLIS_NBT_TAG = "lastJumpMillis";
  
  public static final String COOLDOWN_MILLIS_NBT_TAG = "cooldownMillis";
  
  public static final String CURRENT_JUMP_NBT_TAG = "currentJump";
  
  public static final String CAN_JUMP_NBT_TAG = "canJump";
  
  public static final String JUMPING_NBT_TAG = "jumping";
  
  public static final String OWNER_UNIQUE_ID_NBT_TAG = "ownerUniqueId";
  
  private long expirationMillis;
  
  private long lastJumpMillis;
  
  private long cooldownMillis;
  
  private int currentJump;
  
  private boolean canJump;
  
  private boolean jumping;
  
  private EntityPlayer owner;
  
  private int courseChangeCooldown;
  
  private double waypointX;
  
  private double waypointY;
  
  private double waypointZ;
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public long getLastJumpMillis() {
    return this.lastJumpMillis;
  }
  
  public long getCooldownMillis() {
    return this.cooldownMillis;
  }
  
  public int getCurrentJump() {
    return this.currentJump;
  }
  
  public boolean isCanJump() {
    return this.canJump;
  }
  
  public boolean isJumping() {
    return this.jumping;
  }
  
  public EntityPlayer getOwner() {
    return this.owner;
  }
  
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
  
  public EntityTaupiko(World world) {
    super(world);
    func_70105_a(0.9F, 0.4F);
  }
  
  public EntityTaupiko(World world, EntityPlayer owner, DoubleLocation location) {
    super(world);
    func_70105_a(0.9F, 0.4F);
    this.expirationMillis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2L);
    this.cooldownMillis = 0L;
    this.lastJumpMillis = 0L;
    this.currentJump = 0;
    this.canJump = false;
    this.jumping = false;
    this.owner = owner;
    func_70107_b(location.getX(), location.getY(), location.getZ());
  }
  
  protected void func_110147_ax() {
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111267_a);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111266_c);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111263_d);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0D);
  }
  
  protected void func_70785_a(Entity entity, float f) {}
  
  public boolean func_70652_k(Entity entity) {
    return false;
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    if (player.field_70170_p.field_72995_K) {
      openUI();
      return true;
    } 
    return true;
  }
  
  public boolean func_70097_a(DamageSource damageSource, float p_70097_2_) {
    return false;
  }
  
  protected void func_70665_d(DamageSource source, float f) {}
  
  public void func_70100_b_(EntityPlayer player) {
    super.func_70100_b_(player);
  }
  
  protected boolean func_70650_aV() {
    return false;
  }
  
  public void func_70109_d(NBTTagCompound compound) {
    super.func_70109_d(compound);
    compound.func_74772_a("expirationMillis", this.expirationMillis);
    compound.func_74772_a("cooldownMillis", this.cooldownMillis);
    compound.func_74772_a("lastJumpMillis", this.lastJumpMillis);
    compound.func_74768_a("currentJump", this.currentJump);
    compound.func_74757_a("canJump", this.canJump);
    compound.func_74757_a("jumping", this.jumping);
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    super.func_70020_e(compound);
    if (!compound.func_74764_b("ownerUniqueId") || !compound.func_74764_b("expirationMillis") || 
      !compound.func_74764_b("cooldownMillis") || !compound.func_74764_b("lastJumpMillis") || 
      !compound.func_74764_b("currentJump") || !compound.func_74764_b("canJump") || 
      !compound.func_74764_b("jumping"))
      return; 
    this.expirationMillis = compound.func_74763_f("expirationMillis");
    this.cooldownMillis = compound.func_74763_f("cooldownMillis");
    this.lastJumpMillis = compound.func_74763_f("lastJumpMillis");
    this.currentJump = compound.func_74762_e("currentJump");
    this.canJump = compound.func_74767_n("canJump");
    this.jumping = compound.func_74767_n("jumping");
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    long now = System.currentTimeMillis();
    if (this.field_70170_p.field_72995_K)
      return; 
    if (isExpired(now)) {
      func_70106_y();
      return;
    } 
    if (this.owner == null || this.owner.field_70128_L) {
      func_70106_y();
      return;
    } 
    followOwner();
    DoubleLocation location = new DoubleLocation(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    if (this.jumping && this.currentJump > 0 && this.canJump && 
      
      !this.field_70170_p.func_147437_c(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ())) {
      destroyBlocks();
      this.currentJump--;
      this.jumping = false;
      if (this.currentJump == 0) {
        func_70106_y();
        return;
      } 
    } 
    if (this.currentJump == 0)
      this.canJump = false; 
    if (!canCallJump(now))
      return; 
    this.lastJumpMillis = now;
    if (!this.jumping && this.currentJump > 0 && this.canJump) {
      this.jumping = true;
      func_70107_b(this.field_70165_t, this.field_70163_u + 4.0D, this.field_70161_v);
    } 
  }
  
  public void followOwner() {
    if (this.canJump)
      return; 
    if (func_70032_d((Entity)this.owner) > 10.0F) {
      func_70634_a(this.owner.field_70165_t, this.owner.field_70163_u, this.owner.field_70161_v);
      return;
    } 
    if (func_70032_d((Entity)this.owner) < 2.0F)
      return; 
    double x = this.waypointX - this.field_70165_t;
    double y = this.waypointY - this.field_70163_u;
    double z = this.waypointZ - this.field_70161_v;
    double d3 = x * x + y * y + z * z;
    this
      .field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.owner.field_70165_t, this.owner.field_70161_v)) * 180.0F / 3.1415927F;
    if (d3 < 1.0D || d3 > 3600.0D) {
      this.waypointX = this.owner.field_70165_t;
      this.waypointY = this.owner.field_70163_u;
      this.waypointZ = this.owner.field_70161_v;
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
  
  public void handlePacket(EntityPlayerMP player, int jumpAmount) {
    if (!isOwner((EntityPlayer)player)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'êtes pas le propriétaire de ce taupiko." });
      return;
    } 
    long now = System.currentTimeMillis();
    long cooldown = getCooldown(now);
    if (cooldown > 0L) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&cVous devez attendre %s avant de pouvoir faire ceci !", new Object[] { DurationConverter.fromMillisToString(cooldown) }) });
      return;
    } 
    if (!JUMP_INTERVAL.isInRange(jumpAmount))
      return; 
    this.canJump = true;
    this.jumping = false;
    this.currentJump = jumpAmount;
    this.cooldownMillis = System.currentTimeMillis() + jumpAmount * COOLDOWN_PER_USAGES_MILLIS;
  }
  
  public boolean canCallJump(long now) {
    return (now - this.lastJumpMillis >= JUMP_PERIOD_MILLIS);
  }
  
  public boolean isExpired(long now) {
    return (this.expirationMillis - now <= 0L);
  }
  
  public long getCooldown(long now) {
    return this.cooldownMillis - now;
  }
  
  public boolean isOwner(EntityPlayer player) {
    if (this.owner == null || this.owner.field_70128_L)
      return false; 
    return this.owner.func_110124_au().equals(player.func_110124_au());
  }
  
  public void destroyBlocks() {
    if (this.owner == null || this.owner.field_70128_L) {
      func_70106_y();
      return;
    } 
    DoubleLocation currentLocation = (new DoubleLocation((Entity)this)).add(0.0D, -1.0D, 0.0D);
    if (currentLocation.getBlockY() <= 0)
      return; 
    Cuboid cuboid = new Cuboid(this.field_70170_p, currentLocation.getX() - 2.0D, currentLocation.getY(), currentLocation.getZ() - 2.0D, currentLocation.getX() + 2.0D, currentLocation.getY(), currentLocation.getZ() + 2.0D);
    WorldServer worldServer = (WorldServer)this.field_70170_p;
    cuboid.getLocations().forEach(location -> {
          if (!EventUtils.canInteractUnsafe(this.owner, location))
            return; 
          this.field_70170_p.func_147449_b(location.getBlockX(), location.getBlockY(), location.getBlockZ(), Blocks.field_150350_a);
          worldServer.func_147487_a("explode", location.getBlockX(), location.getBlockY(), location.getBlockZ(), 10, 0.5D, 0.5D, 0.5D, 0.1D);
        });
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
  
  @SideOnly(Side.CLIENT)
  public void openUI() {
    MonthlyUtils.openUI((GuiScreen)new TaupikoUI((Entity)this));
  }
  
  public void func_70645_a(DamageSource damageSource) {
    super.func_70645_a(damageSource);
  }
  
  public boolean func_70601_bi() {
    return true;
  }
  
  public boolean func_85032_ar() {
    return true;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  protected void func_82167_n(Entity p_82167_1_) {}
  
  protected void func_85033_bc() {}
  
  protected boolean func_70780_i() {
    return true;
  }
  
  public ItemStack func_70694_bm() {
    return null;
  }
  
  public ItemStack func_71124_b(int slotID) {
    return null;
  }
  
  public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {}
  
  public ItemStack[] func_70035_c() {
    return super.func_70035_c();
  }
  
  public String func_70005_c_() {
    return "§eTaupiko";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\entities\EntityTaupiko.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */