package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums.DanceDirection;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemPaperAirplane;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPaperAirplane extends EntityMob {
  public static final String ENTITY_ID = "entityPaperAirplane";
  
  public static final long MAX_TICK_MILLIS = 72000L;
  
  public static final String NBT_TOTAL_TICK_MILLIS = "totalTickMillis";
  
  public static final String NBT_DROPPED_BOOLEAN = "dropped";
  
  private long totalTickMillis;
  
  private boolean dropped;
  
  public EntityPaperAirplane(World world) {
    super(world);
    func_70105_a(0.9F, 0.4F);
    this.field_70145_X = false;
    this.field_70178_ae = true;
    this.dropped = false;
  }
  
  public void spawn(EntityPlayer player, World world, double x, double y, double z, long totalTickMillis) {
    this.totalTickMillis = totalTickMillis;
    func_70080_a(x, y, z, player.field_70177_z, player.field_70125_A);
    world.func_72838_d((Entity)this);
  }
  
  public void translateIntoItem() {
    if (this.field_70128_L)
      return; 
    func_70106_y();
    if (this.dropped)
      return; 
    ItemUtils.spawnItemAtEntity((Entity)this, ItemPaperAirplane.build(this.totalTickMillis));
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
    if (this.totalTickMillis >= 72000L) {
      func_70106_y();
      return;
    } 
    EntityPlayer player = (EntityPlayer)this.field_70153_n;
    Location currentLocation = new Location((Entity)this);
    Location nextLocation = DanceDirection.getRelativeLocation(currentLocation, DanceDirection.FRONT, 0.8D);
    Block block = nextLocation.getBlock(this.field_70170_p);
    if (block != null && block.func_149688_o() != null && block.func_149688_o().func_76220_a()) {
      Block currentBlock = currentLocation.getBlock(this.field_70170_p);
      if (currentBlock != null && currentBlock.func_149688_o() != null && currentBlock.func_149688_o().func_76220_a()) {
        translateIntoItem();
        return;
      } 
      TeleportUtils.teleport(player, this.field_70165_t, this.field_70163_u, this.field_70161_v);
      translateIntoItem();
      return;
    } 
    func_70634_a(nextLocation.getX(), nextLocation.getY(), nextLocation.getZ());
  }
  
  public void func_70109_d(NBTTagCompound tagCompound) {
    super.func_70109_d(tagCompound);
    tagCompound.func_74772_a("totalTickMillis", this.totalTickMillis);
    tagCompound.func_74757_a("dropped", this.dropped);
  }
  
  public void func_70020_e(NBTTagCompound tagCompound) {
    super.func_70020_e(tagCompound);
    if (tagCompound.func_74764_b("totalTickMillis"))
      this.totalTickMillis = tagCompound.func_74763_f("totalTickMillis"); 
    if (tagCompound.func_74764_b("dropped"))
      this.dropped = tagCompound.func_74767_n("dropped"); 
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\entities\EntityPaperAirplane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */