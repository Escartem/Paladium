package fr.paladium.palashop.provider.box.common.network.client;

import fr.paladium.palashop.provider.box.common.dto.box.BoxReward;
import java.util.List;

public class SCPacketBoxWaitData {
  private String playerName;
  
  private boolean fast;
  
  private List<BoxReward> availableRewards;
  
  private List<List<BoxReward>> rewards;
  
  private int currentSpin;
  
  public SCPacketBoxWaitData() {}
  
  public SCPacketBoxWaitData(String playerName, boolean fast, List<BoxReward> availableRewards, List<List<BoxReward>> rewards, int currentSpin) {
    this.playerName = playerName;
    this.fast = fast;
    this.availableRewards = availableRewards;
    this.rewards = rewards;
    this.currentSpin = currentSpin;
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public boolean isFast() {
    return this.fast;
  }
  
  public List<BoxReward> getAvailableRewards() {
    return this.availableRewards;
  }
  
  public List<List<BoxReward>> getRewards() {
    return this.rewards;
  }
  
  public int getCurrentSpin() {
    return this.currentSpin;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\network\client\SCPacketBoxWait$SCPacketBoxWaitData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */