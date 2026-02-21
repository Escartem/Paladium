package fr.paladium.palavoicechat.server.api.pojo;

public enum PunishmentType {
  KICK(false, false),
  BAN(true, true),
  MUTE(true, false),
  WARN(false, false);
  
  private final boolean timeBased;
  
  private final boolean ip;
  
  public boolean isTimeBased() {
    return this.timeBased;
  }
  
  public boolean isIp() {
    return this.ip;
  }
  
  PunishmentType(boolean timeBased, boolean ip) {
    this.timeBased = timeBased;
    this.ip = ip;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\api\pojo\PunishmentType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */