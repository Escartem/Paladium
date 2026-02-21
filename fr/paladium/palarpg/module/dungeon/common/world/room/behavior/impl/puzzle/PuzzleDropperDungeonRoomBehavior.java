package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.puzzle;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.PuzzleDungeonRoomBehavior;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class PuzzleDropperDungeonRoomBehavior extends PuzzleDungeonRoomBehavior {
  public PuzzleDropperDungeonRoomBehavior(@NonNull DungeonRoom room) {
    super(room);
    if (room == null)
      throw new NullPointerException("room is marked non-null but is null"); 
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
  public void onFallDamage(LivingAttackEvent event) {
    if (event.source != DamageSource.field_76379_h || !(event.entity instanceof EntityPlayer) || !getRoom().isActive())
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    Optional<DungeonPlayer> optionalDungeonPlayer = getRoom().getWorld().getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent() || !((DungeonPlayer)optionalDungeonPlayer.get()).isAlive())
      return; 
    getRoom().teleport(player);
    event.setCanceled(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\puzzle\PuzzleDropperDungeonRoomBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */