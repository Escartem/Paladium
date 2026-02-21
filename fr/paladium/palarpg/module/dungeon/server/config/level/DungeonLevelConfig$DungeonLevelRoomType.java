package fr.paladium.palarpg.module.dungeon.server.config.level;

import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import lombok.NonNull;

public enum DungeonLevelRoomType {
  ROOM, MINIBOSS, BOSS;
  
  @NonNull
  public static DungeonLevelRoomType from(@NonNull DungeonRoomConfig.DungeonRoomType type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    switch (DungeonLevelConfig.null.$SwitchMap$fr$paladium$palarpg$module$dungeon$server$config$room$DungeonRoomConfig$DungeonRoomType[type.ordinal()]) {
      case 1:
        return MINIBOSS;
      case 2:
        return BOSS;
    } 
    return ROOM;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\level\DungeonLevelConfig$DungeonLevelRoomType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */