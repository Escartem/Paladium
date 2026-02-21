package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.ItemsRegister;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityDutchBoat extends EntityBoat {
  public static final String ENTITY_NAME = "entityDutchBoat";
  
  private static final long JUMP_COOLDOWN = TimeUnit.SECONDS.toMillis(1L);
  
  private long lastJump;
  
  private boolean dropped;
  
  public EntityDutchBoat(World world) {
    super(world);
    this.dropped = false;
  }
  
  public EntityDutchBoat(World world, double x, double y, double z) {
    super(world, x, y, z);
    this.dropped = false;
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
  }
  
  public EntityItem func_145778_a(Item item, int p_145778_2_, float p_145778_3_) {
    if (item.equals(Items.field_151055_y) && !this.dropped) {
      this.dropped = true;
      return func_70099_a(new ItemStack(ItemsRegister.DUTCH_BOAT, 1, 0), p_145778_3_);
    } 
    return null;
  }
  
  public void onPacketJumpReceived(EntityPlayer player) {
    Location location = (new Location((Entity)this)).add(0.0D, -1.0D, 0.0D);
    if (!location.getBlock().equals(Blocks.field_150350_a)) {
      long now = System.currentTimeMillis();
      if (!canJump(now))
        return; 
      this.lastJump = now + JUMP_COOLDOWN;
      this.field_70181_x += 1.5D;
      return;
    } 
    this.field_70181_x -= 0.05D;
    if (this.field_70181_x < 0.0D)
      this.field_70181_x = 0.0D; 
  }
  
  private boolean canJump(long now) {
    return (this.lastJump < now);
  }
  
  private boolean isOnGroundOrWater() {
    return (this.field_70122_E || func_70090_H());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\entities\EntityDutchBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */