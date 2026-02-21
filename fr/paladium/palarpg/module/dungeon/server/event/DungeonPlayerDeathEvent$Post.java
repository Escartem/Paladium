package fr.paladium.palarpg.module.dungeon.server.event;

import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import lombok.NonNull;

public class Post extends DungeonPlayerDeathEvent {
  public Post(@NonNull DungeonPlayer player) {
    super(player);
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\event\DungeonPlayerDeathEvent$Post.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */