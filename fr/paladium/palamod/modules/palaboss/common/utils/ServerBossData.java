package fr.paladium.palamod.modules.palaboss.common.utils;

public class ServerBossData {
  private boolean active;
  
  private String uuid;
  
  private long startedAt;
  
  private String serverUUID;
  
  private String bossName;
  
  private BossLocation location;
  
  public String getUuid() {
    return this.uuid;
  }
  
  public long getStartedAt() {
    return this.startedAt;
  }
  
  public String getServerUUID() {
    return this.serverUUID;
  }
  
  public String getBossName() {
    return this.bossName;
  }
  
  public BossLocation getLocation() {
    return this.location;
  }
  
  public ServerBossData(String serverUUID, String bossName) {
    this.serverUUID = serverUUID;
    this.bossName = bossName;
    this.active = false;
    this.uuid = null;
    this.startedAt = -1L;
  }
  
  public boolean isActive() {
    return (this.active && this.location != null && this.bossName != null && !this.bossName.isEmpty());
  }
  
  public void setActive(boolean active) {
    this.active = active;
  }
  
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
  
  public void setStartedAt(long startedAt) {
    this.startedAt = startedAt;
  }
  
  public void setServerUUID(String serverUUID) {
    this.serverUUID = serverUUID;
  }
  
  public void setBossName(String bossName) {
    this.bossName = bossName;
  }
  
  public void setLocation(BossLocation location) {
    this.location = location;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\commo\\utils\ServerBossData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */