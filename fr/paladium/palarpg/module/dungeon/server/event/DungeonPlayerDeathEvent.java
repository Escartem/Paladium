package fr.paladium.palarpg.module.dungeon.server.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import lombok.NonNull;

public class DungeonPlayerDeathEvent extends Event {
  private final DungeonPlayer player;
  
  public DungeonPlayerDeathEvent(DungeonPlayer player) {
    this.player = player;
  }
  
  public DungeonPlayer getPlayer() {
    return this.player;
  }
  
  @Cancelable
  public static class Pre extends DungeonPlayerDeathEvent {
    public Pre(@NonNull DungeonPlayer player) {
      super(player);
      if (player == null)
        throw new NullPointerException("player is marked non-null but is null"); 
    }
  }
  
  public static class Post extends DungeonPlayerDeathEvent {
    public Post(@NonNull DungeonPlayer player) {
      super(player);
      if (player == null)
        throw new NullPointerException("player is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\event\DungeonPlayerDeathEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */