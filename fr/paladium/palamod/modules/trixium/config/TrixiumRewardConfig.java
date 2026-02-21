package fr.paladium.palamod.modules.trixium.config;

import java.util.List;

public class TrixiumRewardConfig {
  private String uuid;
  
  private int value;
  
  private boolean big;
  
  private String image;
  
  private List<String> commands;
  
  public TrixiumRewardConfig(String uuid, int value, boolean big, String image, List<String> commands) {
    this.uuid = uuid;
    this.value = value;
    this.big = big;
    this.image = image;
    this.commands = commands;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public int getValue() {
    return this.value;
  }
  
  public boolean isBig() {
    return this.big;
  }
  
  public String getImage() {
    return this.image;
  }
  
  public List<String> getCommands() {
    return this.commands;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\config\TrixiumRewardConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */