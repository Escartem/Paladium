package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl;

import cpw.mods.fml.common.eventhandler.Cancelable;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;

@Cancelable
public class Pre extends PuzzleDungeonRoomBehavior.DungeonPuzzleRoomResetEvent {
  public Pre(DungeonRoom room) {
    super(room);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\PuzzleDungeonRoomBehavior$DungeonPuzzleRoomResetEvent$Pre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */