package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;

public class DungeonPuzzleRoomResetEvent extends Event {
  private final DungeonRoom room;
  
  public DungeonPuzzleRoomResetEvent(DungeonRoom room) {
    this.room = room;
  }
  
  public DungeonRoom getRoom() {
    return this.room;
  }
  
  @Cancelable
  public static class Pre extends DungeonPuzzleRoomResetEvent {
    public Pre(DungeonRoom room) {
      super(room);
    }
  }
  
  public static class Post extends DungeonPuzzleRoomResetEvent {
    public Post(DungeonRoom room) {
      super(room);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\PuzzleDungeonRoomBehavior$DungeonPuzzleRoomResetEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */