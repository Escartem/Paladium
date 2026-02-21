package fr.paladium.palamod.modules.end.server.utils;

public class EndData {
  private String uuid;
  
  private EndState state;
  
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
  
  public void setState(EndState state) {
    this.state = state;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public EndState getState() {
    return this.state;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\serve\\utils\EndData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */