package fr.paladium.palaoracle.core.pojo;

import fr.paladium.palaoracle.server.managers.PalaOracleManager;
import java.util.HashMap;

public class PalaOracleEvent {
  public String playerUUID;
  
  public String eventTitle;
  
  public HashMap<String, Object> data = new HashMap<>();
  
  public PalaOracleEvent(String playerUUID, String eventTitle) {
    this.playerUUID = playerUUID;
    this.eventTitle = eventTitle;
  }
  
  public PalaOracleEvent addField(String fieldName, Object object) {
    if (this.data == null)
      this.data = new HashMap<>(); 
    this.data.put(fieldName, object);
    return this;
  }
  
  public boolean capture() {
    if (this.playerUUID.isEmpty() || this.eventTitle.isEmpty() || this.data == null || this.data.isEmpty())
      return false; 
    PalaOracleManager.getInstance().generalCaptureEvent(this);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaoracle\core\pojo\PalaOracleEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */