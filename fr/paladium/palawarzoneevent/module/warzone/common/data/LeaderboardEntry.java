package fr.paladium.palawarzoneevent.module.warzone.common.data;

public class LeaderboardEntry {
  public String factionUUID;
  
  public String factionName;
  
  public long points;
  
  public void setFactionUUID(String factionUUID) {
    this.factionUUID = factionUUID;
  }
  
  public void setFactionName(String factionName) {
    this.factionName = factionName;
  }
  
  public void setPoints(long points) {
    this.points = points;
  }
  
  public LeaderboardEntry() {}
  
  public LeaderboardEntry(String factionUUID, String factionName, long points) {
    this.factionUUID = factionUUID;
    this.factionName = factionName;
    this.points = points;
  }
  
  public String getFactionUUID() {
    return this.factionUUID;
  }
  
  public String getFactionName() {
    return this.factionName;
  }
  
  public long getPoints() {
    return this.points;
  }
  
  public void addPoints(long pointToAdd) {
    this.points += pointToAdd;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\data\LeaderboardEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */