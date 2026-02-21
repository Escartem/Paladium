package fr.paladium.common.utils;

public class ServerStatus {
  private ServerState state;
  
  private String restartReason;
  
  private int onlinePlayers;
  
  private int maxPlayers;
  
  public ServerStatus(ServerState state, String restartReason, int onlinePlayers, int maxPlayers) {
    this.state = state;
    this.restartReason = restartReason;
    this.onlinePlayers = onlinePlayers;
    this.maxPlayers = maxPlayers;
  }
  
  public void setState(ServerState state) {
    this.state = state;
  }
  
  public void setRestartReason(String restartReason) {
    this.restartReason = restartReason;
  }
  
  public void setOnlinePlayers(int onlinePlayers) {
    this.onlinePlayers = onlinePlayers;
  }
  
  public void setMaxPlayers(int maxPlayers) {
    this.maxPlayers = maxPlayers;
  }
  
  public ServerState getState() {
    return this.state;
  }
  
  public String getRestartReason() {
    return this.restartReason;
  }
  
  public int getOnlinePlayers() {
    return this.onlinePlayers;
  }
  
  public int getMaxPlayers() {
    return this.maxPlayers;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\commo\\utils\ServerStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */