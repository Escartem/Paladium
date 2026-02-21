package fr.paladium.palarpg.module.dungeon.common.ranking;

import java.util.Objects;

public class DungeonRankingData {
  private final int index;
  
  private final String playerUUID;
  
  private final String playerName;
  
  private final long value;
  
  public String toString() {
    return "DungeonRankingData(index=" + getIndex() + ", playerUUID=" + getPlayerUUID() + ", playerName=" + getPlayerName() + ", value=" + getValue() + ")";
  }
  
  public DungeonRankingData(int index, String playerUUID, String playerName, long value) {
    this.index = index;
    this.playerUUID = playerUUID;
    this.playerName = playerName;
    this.value = value;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public String getPlayerUUID() {
    return this.playerUUID;
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public long getValue() {
    return this.value;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.playerUUID });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    DungeonRankingData other = (DungeonRankingData)obj;
    return Objects.equals(this.playerUUID, other.playerUUID);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\ranking\DungeonRankingData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */