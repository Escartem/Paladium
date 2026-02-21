package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.boost;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;

public class DungeonRoomDataBoostFountainConfigElement {
  private final EnumStats stats;
  
  private final JsonElement value;
  
  private final JsonElement duration;
  
  private final StatMutationType type;
  
  public DungeonRoomDataBoostFountainConfigElement(EnumStats stats, JsonElement value, JsonElement duration, StatMutationType type) {
    this.stats = stats;
    this.value = value;
    this.duration = duration;
    this.type = type;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\boost\BoostFountainDungeonRoomBehavior$DungeonRoomDataBoostFountainConfig$DungeonRoomDataBoostFountainConfigElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */