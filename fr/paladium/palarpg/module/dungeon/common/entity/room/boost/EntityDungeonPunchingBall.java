package fr.paladium.palarpg.module.dungeon.common.entity.room.boost;

import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.network.room.boost.SCPacketRPGDungeonRoomBoostPunchingBallAnimation;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDungeonPunchingBall extends Entity {
  public EntityDungeonPunchingBall(World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
    this.field_70178_ae = true;
  }
  
  protected void func_70088_a() {
    this.field_70180_af.func_75682_a(2, Integer.valueOf(20));
    this.field_70180_af.func_75682_a(3, Integer.valueOf(0));
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (this.field_70170_p.field_72995_K)
      return false; 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(this.field_70170_p);
    if (!optionalDungeonWorld.isPresent())
      return false; 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!(source.func_76364_f() instanceof EntityPlayer))
      return false; 
    EntityPlayer player = (EntityPlayer)source.func_76364_f();
    Optional<DungeonPlayer> optionalDungeonPlayer = dungeonWorld.getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return false; 
    DungeonPlayer dungeonPlayer = optionalDungeonPlayer.get();
    if (!dungeonPlayer.isAlive())
      return false; 
    setHealth(Math.max(0, getHealth() - 1));
    if (getHealth() <= 0) {
      func_70106_y();
    } else {
      (new SCPacketRPGDungeonRoomBoostPunchingBallAnimation(func_145782_y())).send(PlayerSelector.WORLD());
    } 
    return false;
  }
  
  protected void func_70037_a(NBTTagCompound nbt) {}
  
  protected void func_70014_b(NBTTagCompound nbt) {}
  
  public boolean func_70067_L() {
    return true;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  public int getHealth() {
    return this.field_70180_af.func_75679_c(2);
  }
  
  public void setHealth(int health) {
    this.field_70180_af.func_75692_b(2, Integer.valueOf(health));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\entity\room\boost\EntityDungeonPunchingBall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */