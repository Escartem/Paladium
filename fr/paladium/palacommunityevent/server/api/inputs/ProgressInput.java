package fr.paladium.palacommunityevent.server.api.inputs;

public class ProgressInput {
  public String playerUUID;
  
  public String eventId;
  
  public int progress;
  
  public ProgressInput() {}
  
  public ProgressInput(String playerUUID, String eventId, int progress) {
    this.playerUUID = playerUUID;
    this.eventId = eventId;
    this.progress = progress;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\server\api\inputs\ProgressInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */