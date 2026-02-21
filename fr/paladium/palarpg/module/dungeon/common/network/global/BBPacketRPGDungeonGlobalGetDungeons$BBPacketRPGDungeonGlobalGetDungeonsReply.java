package fr.paladium.palarpg.module.dungeon.common.network.global;

import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import java.util.List;

public class BBPacketRPGDungeonGlobalGetDungeonsReply {
  private final List<DungeonConfig> dungeons;
  
  public BBPacketRPGDungeonGlobalGetDungeonsReply(List<DungeonConfig> dungeons) {
    this.dungeons = dungeons;
  }
  
  public List<DungeonConfig> getDungeons() {
    return this.dungeons;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\global\BBPacketRPGDungeonGlobalGetDungeons$BBPacketRPGDungeonGlobalGetDungeonsReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */