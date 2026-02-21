package fr.paladium.palarpg.module.dungeon.common.network.hub;

public class BBPacketDungeonSearchPlayerElement {
  private final String username;
  
  private final String uuid;
  
  private final String rank;
  
  private final int score;
  
  public BBPacketDungeonSearchPlayerElement(String username, String uuid, String rank, int score) {
    this.username = username;
    this.uuid = uuid;
    this.rank = rank;
    this.score = score;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public String getRank() {
    return this.rank;
  }
  
  public int getScore() {
    return this.score;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\hub\BBPacketRPGDungeonHubSearchPlayer$BBPacketDungeonSearchPlayerResult$BBPacketDungeonSearchPlayerElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */