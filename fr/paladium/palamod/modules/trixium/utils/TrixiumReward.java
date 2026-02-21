package fr.paladium.palamod.modules.trixium.utils;

import fr.paladium.palamod.modules.trixium.config.TrixiumRewardConfig;
import java.util.List;

public class TrixiumReward {
  private String uuid;
  
  private int value;
  
  private boolean big;
  
  private String image;
  
  private List<String> commands;
  
  private State state;
  
  public TrixiumReward(String uuid, int value, boolean big, String image, List<String> commands, State state) {
    this.uuid = uuid;
    this.value = value;
    this.big = big;
    this.image = image;
    this.commands = commands;
    this.state = state;
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
  
  public void setState(State state) {
    this.state = state;
  }
  
  public State getState() {
    return this.state;
  }
  
  public TrixiumReward(TrixiumRewardConfig config, State state) {
    this.uuid = config.getUuid();
    this.value = config.getValue();
    this.big = config.isBig();
    this.image = config.getImage();
    this.commands = config.getCommands();
    this.state = state;
  }
  
  public enum State {
    NOT_AVAILABLE, AVAILABLE, PICKUP;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixiu\\utils\TrixiumReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */