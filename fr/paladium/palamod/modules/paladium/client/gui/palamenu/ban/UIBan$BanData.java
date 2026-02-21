package fr.paladium.palamod.modules.paladium.client.gui.palamenu.ban;

import com.google.gson.JsonObject;

public class BanData {
  private final boolean ip;
  
  private final String reason;
  
  private final String message;
  
  private final String remaining;
  
  public BanData(boolean ip, String reason, String message, String remaining) {
    this.ip = ip;
    this.reason = reason;
    this.message = message;
    this.remaining = remaining;
  }
  
  public boolean isIp() {
    return this.ip;
  }
  
  public String getReason() {
    return this.reason;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public String getRemaining() {
    return this.remaining;
  }
  
  public boolean isValid() {
    return (this.reason != null && this.remaining != null);
  }
  
  public static BanData parse(JsonObject json) {
    if (json == null)
      return null; 
    return new BanData((json.has("ip") && json.get("ip").getAsBoolean()), 
        json.has("reason") ? json.get("reason").getAsString() : null, 
        json.has("message") ? json.get("message").getAsString() : null, 
        json.has("remaining") ? json.get("remaining").getAsString() : null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\ban\UIBan$BanData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */