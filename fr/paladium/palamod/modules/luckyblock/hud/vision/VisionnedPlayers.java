package fr.paladium.palamod.modules.luckyblock.hud.vision;

public class VisionnedPlayers {
  private String sender;
  
  private String visionnedName;
  
  private String visionned;
  
  private boolean linked;
  
  public void setSender(String sender) {
    this.sender = sender;
  }
  
  public void setVisionnedName(String visionnedName) {
    this.visionnedName = visionnedName;
  }
  
  public void setVisionned(String visionned) {
    this.visionned = visionned;
  }
  
  public void setLinked(boolean linked) {
    this.linked = linked;
  }
  
  public String getSender() {
    return this.sender;
  }
  
  public String getVisionnedName() {
    return this.visionnedName;
  }
  
  public String getVisionned() {
    return this.visionned;
  }
  
  public boolean isLinked() {
    return this.linked;
  }
  
  public VisionnedPlayers(String sender, String visionned, String visionnedName) {
    this.sender = sender;
    this.visionned = visionned;
    this.visionnedName = visionnedName;
    this.linked = false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\vision\VisionnedPlayers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */