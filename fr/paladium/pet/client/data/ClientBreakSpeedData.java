package fr.paladium.pet.client.data;

public class ClientBreakSpeedData {
  public static final double DEFAULT_BREAK_SPEED = 1.0D;
  
  public static ClientBreakSpeedData INSTANCE;
  
  private double obsidianBreakSpeed;
  
  private long obsidianExpirationMillis;
  
  private double globalBreakSpeed;
  
  private long globalExpirationMillis;
  
  public double getObsidianBreakSpeed() {
    return this.obsidianBreakSpeed;
  }
  
  public long getObsidianExpirationMillis() {
    return this.obsidianExpirationMillis;
  }
  
  public double getGlobalBreakSpeed() {
    return this.globalBreakSpeed;
  }
  
  public long getGlobalExpirationMillis() {
    return this.globalExpirationMillis;
  }
  
  public ClientBreakSpeedData() {
    INSTANCE = this;
    this.obsidianBreakSpeed = 1.0D;
    this.obsidianExpirationMillis = 0L;
    this.globalBreakSpeed = 1.0D;
    this.globalExpirationMillis = 0L;
  }
  
  public static ClientBreakSpeedData get() {
    if (INSTANCE == null)
      INSTANCE = new ClientBreakSpeedData(); 
    return INSTANCE;
  }
  
  public void updateObsidianBreakSpeed(double value, long duration) {
    this.obsidianBreakSpeed = value;
    this.obsidianExpirationMillis = System.currentTimeMillis() + duration;
  }
  
  public void updateGlobalBreakSpeed(double value, long duration) {
    this.globalBreakSpeed = value;
    this.globalExpirationMillis = System.currentTimeMillis() + duration;
  }
  
  public boolean isExpired(long now, long expirationMillis) {
    return (System.currentTimeMillis() >= expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\data\ClientBreakSpeedData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */