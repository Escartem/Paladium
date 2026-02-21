package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.ItemSupersonicRocket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.RelativeDirection;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntitySupersonicRocket extends EntityMob implements IAnimatable, IAnimationTickable {
  public static final long MAX_TICK_MILLIS = 12000L;
  
  public static final String NEED_RIDER = "needRider";
  
  public static final String NBT_TOTAL_TICK_MILLIS = "totalTickMillis";
  
  public static final String NBT_DROPPED_BOOLEAN = "dropped";
  
  private long totalTickMillis;
  
  private boolean dropped;
  
  private boolean needRider;
  
  private AnimationFactory factory = new AnimationFactory(this);
  
  public EntitySupersonicRocket(World world) {
    super(world);
    func_70105_a(0.9F, 0.4F);
    this.field_70145_X = false;
    this.field_70178_ae = true;
    this.dropped = false;
  }
  
  public void registerControllers(AnimationData data) {}
  
  public void spawn(EntityPlayer player, World world, double x, double y, double z, long totalTickMillis, boolean needRider) {
    this.totalTickMillis = totalTickMillis;
    this.needRider = needRider;
    func_70080_a(x, y, z, player.field_70177_z, player.field_70125_A);
    world.func_72838_d((Entity)this);
  }
  
  public void translateIntoItem() {
    if (this.field_70128_L)
      return; 
    func_70106_y();
    if (this.dropped)
      return; 
    ItemUtils.spawnItemAtEntity((Entity)this, ItemSupersonicRocket.build(this.totalTickMillis));
    this.dropped = true;
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    if (!player.func_70115_ae()) {
      player.func_70078_a((Entity)this);
      return true;
    } 
    return false;
  }
  
  public boolean func_70097_a(DamageSource damageSource, float p_70097_2_) {
    if (this.field_70170_p.field_72995_K)
      return true; 
    translateIntoItem();
    return false;
  }
  
  protected void func_70665_d(DamageSource source, float f) {}
  
  public void func_70636_d() {
    super.func_70636_d();
    if (this.field_70170_p.field_72995_K)
      return; 
    this.field_70181_x *= 0.0D;
    this.field_70159_w *= 0.0D;
    this.field_70179_y *= 0.0D;
    if (this.field_70153_n == null || !(this.field_70153_n instanceof EntityPlayer))
      return; 
    this.totalTickMillis++;
    if (this.totalTickMillis >= 12000L) {
      func_70106_y();
      return;
    } 
    EntityPlayer player = (EntityPlayer)this.field_70153_n;
    DoubleLocation currentLocation = new DoubleLocation((Entity)this);
    DoubleLocation nextLocation = RelativeDirection.getRelativeLocation(currentLocation, RelativeDirection.FRONT, 0.8D);
    Block block = this.field_70170_p.func_147439_a(nextLocation.getBlockX(), nextLocation.getBlockY(), nextLocation.getBlockZ());
    if (block != null && block.func_149688_o() != null && block.func_149688_o().func_76220_a()) {
      Block currentBlock = this.field_70170_p.func_147439_a(currentLocation.getBlockX(), currentLocation.getBlockY(), currentLocation.getBlockZ());
      if (currentBlock != null && currentBlock.func_149688_o() != null && currentBlock.func_149688_o().func_76220_a()) {
        translateIntoItem();
        return;
      } 
      MonthlyUtils.teleport(player, this.field_70165_t, this.field_70163_u, this.field_70161_v);
      translateIntoItem();
      return;
    } 
    func_70634_a(nextLocation.getX(), nextLocation.getY(), nextLocation.getZ());
  }
  
  public void func_70109_d(NBTTagCompound tagCompound) {
    super.func_70109_d(tagCompound);
    tagCompound.func_74772_a("totalTickMillis", this.totalTickMillis);
    tagCompound.func_74757_a("dropped", this.dropped);
    tagCompound.func_74757_a("needRider", this.needRider);
  }
  
  public void func_70020_e(NBTTagCompound tagCompound) {
    super.func_70020_e(tagCompound);
    if (tagCompound.func_74764_b("totalTickMillis"))
      this.totalTickMillis = tagCompound.func_74763_f("totalTickMillis"); 
    if (tagCompound.func_74764_b("dropped"))
      this.dropped = tagCompound.func_74767_n("dropped"); 
    if (tagCompound.func_74764_b("needRider"))
      this.needRider = tagCompound.func_74767_n("needRider"); 
  }
  
  public void func_70612_e(float strafe, float forward) {}
  
  public boolean func_70617_f_() {
    return false;
  }
  
  public boolean func_70067_L() {
    return true;
  }
  
  protected boolean func_70610_aX() {
    return true;
  }
  
  public void func_70024_g(double x, double y, double z) {}
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
  
  public AnimationFactory getFactory() {
    return this.factory;
  }
  
  public int tickTimer() {
    return this.field_70173_aa;
  }
  
  public void tick() {
    func_70071_h_();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\entity\EntitySupersonicRocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */