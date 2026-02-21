package fr.paladium.palarpg.module.dungeon.server.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import lombok.NonNull;

@Cancelable
public class Pre extends DungeonPlayerDeathEvent {
  public Pre(@NonNull DungeonPlayer player) {
    super(player);
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\event\DungeonPlayerDeathEvent$Pre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */