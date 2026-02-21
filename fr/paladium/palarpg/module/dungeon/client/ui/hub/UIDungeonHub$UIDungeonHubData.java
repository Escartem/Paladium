package fr.paladium.palarpg.module.dungeon.client.ui.hub;

import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import java.util.List;

public class UIDungeonHubData {
  private final boolean leader;
  
  private final List<DungeonConfig> dungeons;
  
  public UIDungeonHubData(boolean leader, List<DungeonConfig> dungeons) {
    this.leader = leader;
    this.dungeons = dungeons;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\hub\UIDungeonHub$UIDungeonHubData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */