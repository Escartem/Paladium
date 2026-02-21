package fr.paladium.palamod.modules.luckyblock.entity.may;

import fr.paladium.palamod.modules.luckyblock.entity.EntityFakePlayer;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityNPC extends EntityFakePlayer {
  protected boolean interact = false;
  
  private Long startDate = null;
  
  private long lifeDurationWithoutInteract = 0L;
  
  private long lifeDurationWithInteract = 0L;
  
  protected boolean focus = false;
  
  public EntityNPC(World world) {
    super(world);
  }
  
  public EntityNPC(World world, String skinPath, double x, double y, double z, long lifeDurationWithoutInteract, long lifeDurationWithInteract, ItemStack itemInHand, boolean focus) {
    this(world, skinPath, x, y, z, lifeDurationWithoutInteract, lifeDurationWithInteract);
    func_70062_b(0, itemInHand);
    this.focus = focus;
  }
  
  public EntityNPC(World world, String skinPath, double x, double y, double z, long lifeDurationWithoutInteract, long lifeDurationWithInteract, boolean focus) {
    this(world, skinPath, x, y, z, lifeDurationWithoutInteract, lifeDurationWithInteract);
    this.focus = focus;
  }
  
  public EntityNPC(World world, String displayName, String skinPath, double x, double y, double z, long lifeDurationWithoutInteract, long lifeDurationWithInteract, boolean focus) {
    this(world, skinPath, x, y, z, lifeDurationWithoutInteract, lifeDurationWithInteract);
    this.focus = focus;
    func_94058_c(displayName);
    func_94061_f(true);
  }
  
  public EntityNPC(World world, String skinPath, double x, double y, double z, long lifeDurationWithoutInteract, long lifeDurationWithInteract, ItemStack itemInHand) {
    this(world, skinPath, x, y, z, lifeDurationWithoutInteract, lifeDurationWithInteract);
    func_70062_b(0, itemInHand);
  }
  
  public EntityNPC(World world, String skinPath, double x, double y, double z, long lifeDurationWithoutInteract, long lifeDurationWithInteract) {
    super(world, UUID.randomUUID(), "", skinPath, x, y - 2.0D, z);
    this.startDate = Long.valueOf(System.currentTimeMillis());
    this.lifeDurationWithoutInteract = lifeDurationWithoutInteract;
    this.lifeDurationWithInteract = lifeDurationWithInteract;
    func_94058_c("§bnpc");
    func_94061_f(false);
    if (this.focus)
      this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F)); 
  }
  
  public boolean func_70097_a(DamageSource damageSource, float p_70097_2_) {
    return false;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  public boolean func_82171_bF() {
    return false;
  }
  
  protected void func_82164_bB() {}
  
  public String func_70005_c_() {
    return func_94057_bL();
  }
  
  public void func_82167_n(Entity entity) {}
  
  public void func_85033_bc() {}
  
  public void func_70071_h_() {
    super.func_70071_h_();
    this.field_70181_x = 0.0D;
    if (!this.field_70170_p.field_72995_K) {
      if (this.startDate == null) {
        func_70106_y();
        return;
      } 
      long duration = this.lifeDurationWithoutInteract;
      if (this.interact)
        duration = this.lifeDurationWithInteract; 
      long time = System.currentTimeMillis() - this.startDate.longValue();
      if (duration > -1L && 
        time >= duration)
        func_70106_y(); 
    } 
    if (this.focus) {
      this.field_70159_w = 0.0D;
      this.field_70179_y = 0.0D;
      watchClosestPlayer();
    } 
  }
  
  public void watchClosestPlayer() {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\may\EntityNPC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */