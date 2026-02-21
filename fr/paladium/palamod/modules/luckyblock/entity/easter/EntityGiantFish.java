package fr.paladium.palamod.modules.luckyblock.entity.easter;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGiantFish extends EntityMob {
  public static final int CUBOID_RADIUS = 15;
  
  public static final String EXPIRATION_MILLIS_NBT_TAG = "expirationMillis";
  
  private static final long DESTROY_DELAY_MILLIS = 250L;
  
  private long expirationMillis;
  
  private long lastDestroyMillis;
  
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
  
  public long getLastDestroyMillis() {
    return this.lastDestroyMillis;
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
  
  public EntityGiantFish(World world) {
    super(world);
    func_70105_a(0.9F, 0.4F);
  }
  
  public EntityGiantFish(EntityPlayer owner) {
    super(owner.field_70170_p);
    func_70105_a(0.9F, 0.4F);
    this.expirationMillis = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2L);
    this.owner = owner;
    func_70107_b(owner.field_70165_t, owner.field_70163_u, owner.field_70161_v);
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
    return super.func_70085_c(player);
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
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    super.func_70020_e(compound);
    if (!compound.func_74764_b("expirationMillis"))
      return; 
    this.expirationMillis = compound.func_74763_f("expirationMillis");
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
    this.owner.field_70143_R = 0.0F;
    destroyBlocks(now);
  }
  
  public boolean isExpired(long now) {
    return (this.expirationMillis - now <= 0L);
  }
  
  private boolean canDestroyBlocks(long now) {
    return (now - this.lastDestroyMillis >= 250L);
  }
  
  public void destroyBlocks(long now) {
    if (!canDestroyBlocks(now))
      return; 
    this.lastDestroyMillis = now;
    DoubleLocation currentLocation = (new DoubleLocation((Entity)this)).add(0.0D, -1.0D, 0.0D);
    Cuboid cuboid = new Cuboid(this.field_70170_p, currentLocation.getX() - 15.0D, currentLocation.getY(), currentLocation.getZ() - 15.0D, currentLocation.getX() + 15.0D, currentLocation.getY(), currentLocation.getZ() + 15.0D);
    for (Location location : cuboid.getLocations()) {
      if (!EventUtils.canInteractUnsafe(this.owner, location) || location.getBlockY() <= 0) {
        if (!this.field_70128_L)
          func_70106_y(); 
        return;
      } 
      if (location.getBlockY() > 0)
        this.field_70170_p.func_147449_b(location.getBlockX(), location.getBlockY(), location.getBlockZ(), Blocks.field_150350_a); 
    } 
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
    return "§eGiant Fish";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\easter\EntityGiantFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */