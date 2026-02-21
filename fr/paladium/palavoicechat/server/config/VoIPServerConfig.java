package fr.paladium.palavoicechat.server.config;

public class VoIPServerConfig {
  public String getVoipServerIp() {
    return this.voipServerIp;
  }
  
  public int getVoipServerPort() {
    return this.voipServerPort;
  }
  
  public String getSecret() {
    return this.secret;
  }
  
  public long getPlayerPositionRefreshRate() {
    return this.playerPositionRefreshRate;
  }
  
  public int getMaxLocationUpdatePerPacket() {
    return this.maxLocationUpdatePerPacket;
  }
  
  public String getModApiUrl() {
    return this.modApiUrl;
  }
  
  private final String voipServerIp = "localhost";
  
  private final int voipServerPort = 5060;
  
  private final String secret = "";
  
  private final long playerPositionRefreshRate = 250L;
  
  private final int maxLocationUpdatePerPacket = 15;
  
  private final String modApiUrl = "http://localhost:8080";
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\config\VoIPServerConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */