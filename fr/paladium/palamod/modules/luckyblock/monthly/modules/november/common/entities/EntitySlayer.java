package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.utils.RandomItemStackPicker;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySlayer extends EntityMob {
  public static final String ENTITY_ID = "entitySlayer";
  
  private static final long EXPIRATION_MILLIS = TimeUnit.SECONDS.toMillis(60L);
  
  private long spawnMillis;
  
  private RandomItemStackPicker picker;
  
  public EntitySlayer(World world) {
    super(world);
    func_70105_a(0.9F, 0.4F);
    this.field_70145_X = false;
    this.field_70178_ae = true;
    this.spawnMillis = System.currentTimeMillis();
  }
  
  public boolean spawn(EntityPlayer player, World world, double x, double y, double z) {
    func_70107_b(x, y, z);
    world.func_72838_d((Entity)this);
    return true;
  }
  
  private boolean isExpired(long now) {
    return (now - this.spawnMillis > EXPIRATION_MILLIS);
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    World world = player.field_70170_p;
    if (world.field_72995_K || this.field_70128_L)
      return true; 
    ItemStack stack = this.picker.pick();
    if (stack == null)
      return true; 
    ItemUtils.spawnItemInWorld(world, this.field_70165_t, this.field_70163_u, this.field_70161_v, stack);
    func_70106_y();
    return true;
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    if (this.field_70170_p.field_72995_K)
      return; 
    this.field_70181_x *= 0.0D;
    this.field_70159_w *= 0.0D;
    this.field_70179_y *= 0.0D;
    long now = System.currentTimeMillis();
    if (isExpired(now)) {
      func_70106_y();
      return;
    } 
  }
  
  public void func_70109_d(NBTTagCompound tagCompound) {
    super.func_70109_d(tagCompound);
    tagCompound.func_74772_a("spawnMillis", this.spawnMillis);
  }
  
  public void func_70020_e(NBTTagCompound tagCompound) {
    super.func_70020_e(tagCompound);
    this.spawnMillis = tagCompound.func_74763_f("spawnMillis");
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\entities\EntitySlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */