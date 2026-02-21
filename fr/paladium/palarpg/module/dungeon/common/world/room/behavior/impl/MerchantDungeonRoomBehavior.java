package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl;

import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;
import fr.paladium.palarpg.module.dungeon.common.network.room.merchant.SCPacketRPGDungeonRoomMerchantAnimation;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.DungeonRoomBehavior;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class MerchantDungeonRoomBehavior extends DungeonRoomBehavior {
  private final boolean mephisto;
  
  private UUID entity;
  
  private DoubleLocation location;
  
  public MerchantDungeonRoomBehavior(@NonNull DungeonRoom room) {
    super(room);
    if (room == null)
      throw new NullPointerException("room is marked non-null but is null"); 
    this.mephisto = (room.getConfig().getData("mephisto", Boolean.class) != null && ((Boolean)room.getConfig().getData("mephisto", Boolean.class)).booleanValue());
  }
  
  public void onPaste(@NonNull Block block, @NonNull BlockPos position) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    if (position == null)
      throw new NullPointerException("position is marked non-null but is null"); 
    if (block == BlocksRegister.DUNGEON_ROOM_ENTITY) {
      BlockLocation blockLocation = new BlockLocation(getRoom().getWorld().getWorld(), position.getX(), position.getY(), position.getZ());
      TileEntityDungeonRoom tileEntity = (TileEntityDungeonRoom)blockLocation.getTileEntity();
      this.location = new DoubleLocation(blockLocation.getX() + 0.5D, blockLocation.getY(), blockLocation.getZ() + 0.5D, (tileEntity == null) ? 0.0F : tileEntity.getRotation(), 0.0F);
      blockLocation.setBlockToAir();
    } 
  }
  
  public void onLoad() {}
  
  public void onReset() {}
  
  public void onRemove() {
    if (this.entity == null)
      return; 
    World world = getRoom().getWorld().getWorld();
    for (Object loadedEntityObj : world.field_72996_f) {
      Entity loadedEntity = (Entity)loadedEntityObj;
      if (this.entity.equals(loadedEntity.func_110124_au())) {
        world.func_72900_e(loadedEntity);
        break;
      } 
    } 
  }
  
  public void onJoin() {
    if (this.entity != null)
      return; 
    getRoom().setFinished(true);
    if (this.location == null)
      return; 
    World world = getRoom().getWorld().getWorld();
    EntityDungeonMerchant merchant = new EntityDungeonMerchant(world);
    merchant.setMephisto(this.mephisto);
    merchant.func_70080_a(this.location.getX(), this.location.getY(), this.location.getZ(), this.location.getYaw(), this.location.getPitch());
    world.func_72838_d((Entity)merchant);
    this.entity = merchant.func_110124_au();
    (new SCPacketRPGDungeonRoomMerchantAnimation(merchant.func_145782_y(), "spawn")).send(PlayerSelector.WORLD(merchant.field_70170_p));
  }
  
  public void onLeave() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\MerchantDungeonRoomBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */