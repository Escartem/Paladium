package fr.paladium.palarpg.module.dungeon.common.world.room;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

public class DungeonRoomFinishEvent extends Event {
  private final DungeonRoom room;
  
  private final boolean finished;
  
  public DungeonRoomFinishEvent(DungeonRoom room, boolean finished) {
    this.room = room;
    this.finished = finished;
  }
  
  public DungeonRoom getRoom() {
    return this.room;
  }
  
  public boolean isFinished() {
    return this.finished;
  }
  
  @Cancelable
  public static class Pre extends DungeonRoomFinishEvent {
    public Pre(DungeonRoom room, boolean finished) {
      super(room, finished);
    }
  }
  
  public static class Post extends DungeonRoomFinishEvent {
    public Post(DungeonRoom room, boolean finished) {
      super(room, finished);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\DungeonRoom$DungeonRoomFinishEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */