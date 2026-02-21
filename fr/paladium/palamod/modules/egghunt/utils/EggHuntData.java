package fr.paladium.palamod.modules.egghunt.utils;

public class EggHuntData {
  private boolean active;
  
  private String uuid;
  
  private long startedAt;
  
  private boolean endEvent;
  
  public EggHuntData(boolean active, String uuid, long startedAt, boolean endEvent) {
    this.active = active;
    this.uuid = uuid;
    this.startedAt = startedAt;
    this.endEvent = endEvent;
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
  
  public void setEndEvent(boolean endEvent) {
    this.endEvent = endEvent;
  }
  
  public boolean isActive() {
    return this.active;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public long getStartedAt() {
    return this.startedAt;
  }
  
  public boolean isEndEvent() {
    return this.endEvent;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghun\\utils\EggHuntData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */