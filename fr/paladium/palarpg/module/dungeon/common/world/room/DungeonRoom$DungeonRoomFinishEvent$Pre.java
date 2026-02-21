package fr.paladium.palarpg.module.dungeon.common.world.room;

import cpw.mods.fml.common.eventhandler.Cancelable;

@Cancelable
public class Pre extends DungeonRoom.DungeonRoomFinishEvent {
  public Pre(DungeonRoom room, boolean finished) {
    super(room, finished);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\DungeonRoom$DungeonRoomFinishEvent$Pre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */