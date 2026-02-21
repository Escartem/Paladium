package fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class AEntityNPC extends EntityMob {
  public static final long INFINITE_DURATION = -1L;
  
  protected boolean focus;
  
  protected String displayName;
  
  protected long expirationMillis;
  
  protected DoubleLocation spawnLocation;
  
  public AEntityNPC(World world) {
    super(world);
  }
  
  public AEntityNPC(World world, String displayName, double x, double y, double z, long durationMillis, boolean focus) {
    this(world);
    this.focus = focus;
    this.displayName = displayName;
    this.expirationMillis = (durationMillis == -1L) ? -1L : (System.currentTimeMillis() + durationMillis);
    func_94061_f(true);
    func_70634_a(x, y, z);
    this.spawnLocation = new DoubleLocation(x, y, z);
  }
  
  public void spawn(World world) {
    onSpawn(world, this.field_70165_t, this.field_70163_u, this.field_70161_v);
    world.func_72838_d((Entity)this);
  }
  
  protected abstract void onSpawn(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3);
  
  protected abstract void onExpired(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3);
  
  protected void func_70088_a() {
    super.func_70088_a();
  }
  
  public boolean func_70097_a(DamageSource damageSource, float f) {
    return false;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  public boolean func_82171_bF() {
    return false;
  }
  
  public void func_70784_b(Entity entity) {}
  
  protected void func_82164_bB() {}
  
  public boolean func_70685_l(Entity entity) {
    return false;
  }
  
  protected Entity func_70782_k() {
    return null;
  }
  
  public boolean func_70601_bi() {
    return true;
  }
  
  protected boolean func_70650_aV() {
    return false;
  }
  
  public String func_70005_c_() {
    return func_94057_bL();
  }
  
  public void func_82167_n(Entity entity) {}
  
  public void func_85033_bc() {}
  
  public void func_70071_h_() {
    super.func_70071_h_();
    this.field_70181_x = 0.0D;
    this.field_70159_w = 0.0D;
    this.field_70179_y = 0.0D;
    if (this.spawnLocation != null)
      func_70634_a(this.spawnLocation.getX(), this.spawnLocation.getY(), this.spawnLocation.getZ()); 
    if (!this.field_70170_p.field_72995_K) {
      long now = System.currentTimeMillis();
      if (isExpired(now)) {
        onExpired(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        func_70106_y();
        return;
      } 
    } 
    if (this.focus)
      watchClosestPlayer(); 
  }
  
  public boolean isExpired(long now) {
    if (this.expirationMillis == -1L)
      return false; 
    return (this.expirationMillis <= now);
  }
  
  protected void watchClosestPlayer() {
    EntityPlayer closestPlayer = this.field_70170_p.func_72890_a((Entity)this, -1.0D);
    if (closestPlayer != null) {
      double lookX = closestPlayer.field_70165_t - this.field_70165_t;
      double lookY = closestPlayer.field_70163_u + closestPlayer.func_70047_e() - this.field_70163_u;
      double lookZ = closestPlayer.field_70161_v - this.field_70161_v;
      double distance = Math.sqrt(lookX * lookX + lookY * lookY + lookZ * lookZ);
      if (distance > 0.0D) {
        lookX /= distance;
        lookY /= distance;
        lookZ /= distance;
        double yaw = Math.atan2(lookZ, lookX) * 180.0D / Math.PI - 90.0D;
        this.field_70177_z = (float)yaw;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\entity\npc\AEntityNPC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */