package fr.paladium.palamod.modules.luckyblock.utils;

public class BungeeCordCommandTPServerCoords {
  private final String playerUUID;
  
  private final String server;
  
  private final double x;
  
  private final double y;
  
  private final double z;
  
  public BungeeCordCommandTPServerCoords(String playerUUID, String server, double x, double y, double z) {
    this.playerUUID = playerUUID;
    this.server = server;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public String getPlayerUUID() {
    return this.playerUUID;
  }
  
  public String getServer() {
    return this.server;
  }
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public double getZ() {
    return this.z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\BungeeCordPlugin$BungeeCordCommandTPServerCoords.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */