package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ChunkUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityMimic extends EntityZombie {
  public static final String ENTITY_ID = "entityMimic";
  
  private static final String EMPTY_STRING = "";
  
  private static final double DEFAULT_HEALTH = 100.0D;
  
  private static final double DEFAULT_DAMAGE = 15.0D;
  
  private static final long DEFAULT_ATTACK_COOLDOWN = TimeUnit.SECONDS.toMillis(1L);
  
  private static final long DEFAULT_EXPIRATION_COOLDOWN = TimeUnit.SECONDS.toMillis(5L);
  
  private boolean attacked;
  
  private long lastAttackMillis;
  
  private long firstAttackMillis;
  
  public EntityMimic(World world) {
    super(world);
    func_70105_a(0.9F, 0.4F);
    this.attacked = false;
    this.lastAttackMillis = 0L;
    this.firstAttackMillis = 0L;
  }
  
  private boolean canAttack(long now) {
    return (now - this.lastAttackMillis >= DEFAULT_ATTACK_COOLDOWN);
  }
  
  private boolean isExpired(long now) {
    return (now - this.firstAttackMillis >= DEFAULT_EXPIRATION_COOLDOWN);
  }
  
  public void spawn(World world, DoubleLocation location) {
    func_70634_a(location.getX(), location.getY(), location.getZ());
    world.func_72838_d((Entity)this);
    applyPotionEffects();
  }
  
  public void applyPotionEffects() {
    func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 2400, 0));
    func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 2400, 0));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(15.0D);
  }
  
  public boolean func_70097_a(DamageSource source, float f) {
    Entity entity = source.func_76346_g();
    if (this.attacked)
      return false; 
    this.attacked = true;
    this.firstAttackMillis = System.currentTimeMillis();
    return false;
  }
  
  public void func_70645_a(DamageSource source) {
    super.func_70645_a(source);
    if (this.field_70170_p.field_72995_K)
      return; 
  }
  
  protected void func_70665_d(DamageSource source, float f) {
    Entity entity = source.func_76346_g();
    this.attacked = true;
  }
  
  public boolean func_70652_k(Entity entity) {
    if (!this.attacked)
      return false; 
    return false;
  }
  
  protected String func_70639_aQ() {
    return "";
  }
  
  protected String func_70621_aR() {
    return "";
  }
  
  protected String func_70673_aS() {
    return "";
  }
  
  protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {}
  
  protected Item func_146068_u() {
    return null;
  }
  
  private void attack(long now) {
    double radius = 3.0D;
    Cuboid cuboid = new Cuboid(this.field_70170_p, this.field_70165_t - radius, this.field_70163_u, this.field_70161_v - radius, this.field_70165_t + radius, this.field_70163_u + 1.0D, this.field_70161_v + radius);
    WorldServer worldServer = (WorldServer)this.field_70170_p;
    cuboid.getLocations().forEach(location -> worldServer.func_147487_a("explode", location.getBlockX(), location.getBlockY(), location.getBlockZ(), 5, 0.5D, 0.5D, 0.5D, 0.1D));
    ChunkUtils.getPlayers(cuboid).forEach(player -> player.func_70097_a(DamageSource.field_76376_m, 3.0F));
    this.lastAttackMillis = now;
  }
  
  public String func_70005_c_() {
    return "§bMimic";
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    func_70066_B();
    if (this.field_70170_p.field_72995_K)
      return; 
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.0D;
    this.field_70179_y = 0.0D;
    if (!this.attacked)
      return; 
    long now = System.currentTimeMillis();
    if (isExpired(now)) {
      func_70106_y();
      ItemUtils.spawnItemInWorld(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(BlocksRegister.FAKE_CORRUPTED_CHEST, 1));
      return;
    } 
    if (canAttack(now))
      attack(now); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\entities\EntityMimic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */