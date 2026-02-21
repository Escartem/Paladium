package fr.paladium.palamod.pulsar.pulse;

public class PulseMeta {
  private String id;
  
  private String description;
  
  private boolean forced;
  
  private boolean enabled;
  
  public PulseMeta(String id, String description, boolean forced, boolean enabled) {
    this.id = id;
    this.description = description;
    this.forced = forced;
    this.enabled = enabled;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public boolean isForced() {
    return this.forced;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\pulsar\pulse\PulseMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */