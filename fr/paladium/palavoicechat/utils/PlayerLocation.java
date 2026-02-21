package fr.paladium.palavoicechat.utils;

import java.util.UUID;

public class PlayerLocation {
  private final UUID playerUUID;
  
  private final String playerName;
  
  private final String serverName;
  
  private final int dimension;
  
  private final float posX;
  
  private final float posY;
  
  private final float posZ;
  
  private final boolean canSpeak;
  
  public PlayerLocation(UUID playerUUID, String playerName, String serverName, int dimension, float posX, float posY, float posZ, boolean canSpeak) {
    this.playerUUID = playerUUID;
    this.playerName = playerName;
    this.serverName = serverName;
    this.dimension = dimension;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.canSpeak = canSpeak;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof PlayerLocation))
      return false; 
    PlayerLocation other = (PlayerLocation)o;
    if (!other.canEqual(this))
      return false; 
    if (getDimension() != other.getDimension())
      return false; 
    if (Float.compare(getPosX(), other.getPosX()) != 0)
      return false; 
    if (Float.compare(getPosY(), other.getPosY()) != 0)
      return false; 
    if (Float.compare(getPosZ(), other.getPosZ()) != 0)
      return false; 
    if (isCanSpeak() != other.isCanSpeak())
      return false; 
    Object this$playerUUID = getPlayerUUID(), other$playerUUID = other.getPlayerUUID();
    if ((this$playerUUID == null) ? (other$playerUUID != null) : !this$playerUUID.equals(other$playerUUID))
      return false; 
    Object this$playerName = getPlayerName(), other$playerName = other.getPlayerName();
    if ((this$playerName == null) ? (other$playerName != null) : !this$playerName.equals(other$playerName))
      return false; 
    Object this$serverName = getServerName(), other$serverName = other.getServerName();
    return !((this$serverName == null) ? (other$serverName != null) : !this$serverName.equals(other$serverName));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof PlayerLocation;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getDimension();
    result = result * 59 + Float.floatToIntBits(getPosX());
    result = result * 59 + Float.floatToIntBits(getPosY());
    result = result * 59 + Float.floatToIntBits(getPosZ());
    result = result * 59 + (isCanSpeak() ? 79 : 97);
    Object $playerUUID = getPlayerUUID();
    result = result * 59 + (($playerUUID == null) ? 43 : $playerUUID.hashCode());
    Object $playerName = getPlayerName();
    result = result * 59 + (($playerName == null) ? 43 : $playerName.hashCode());
    Object $serverName = getServerName();
    return result * 59 + (($serverName == null) ? 43 : $serverName.hashCode());
  }
  
  public String toString() {
    return "PlayerLocation(playerUUID=" + getPlayerUUID() + ", playerName=" + getPlayerName() + ", serverName=" + getServerName() + ", dimension=" + getDimension() + ", posX=" + getPosX() + ", posY=" + getPosY() + ", posZ=" + getPosZ() + ", canSpeak=" + isCanSpeak() + ")";
  }
  
  public UUID getPlayerUUID() {
    return this.playerUUID;
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public String getServerName() {
    return this.serverName;
  }
  
  public int getDimension() {
    return this.dimension;
  }
  
  public float getPosX() {
    return this.posX;
  }
  
  public float getPosY() {
    return this.posY;
  }
  
  public float getPosZ() {
    return this.posZ;
  }
  
  public boolean isCanSpeak() {
    return this.canSpeak;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicecha\\utils\PlayerLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */