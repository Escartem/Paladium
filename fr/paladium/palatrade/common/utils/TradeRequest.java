package fr.paladium.palatrade.common.utils;

import java.util.UUID;

public class TradeRequest {
  private UUID player;
  
  private String username;
  
  private long time;
  
  public TradeRequest(UUID player, String username, long time) {
    this.player = player;
    this.username = username;
    this.time = time;
  }
  
  public UUID getPlayer() {
    return this.player;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public long getTime() {
    return this.time;
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.player == null) ? 0 : this.player.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    TradeRequest other = (TradeRequest)obj;
    if (this.player == null) {
      if (other.player != null)
        return false; 
    } else if (!this.player.equals(other.player)) {
      return false;
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\commo\\utils\TradeRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */