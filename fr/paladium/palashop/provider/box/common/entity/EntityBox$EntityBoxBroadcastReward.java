package fr.paladium.palashop.provider.box.common.entity;

import fr.paladium.palashop.provider.box.common.dto.box.BoxReward;

public class EntityBoxBroadcastReward {
  private final BoxReward reward;
  
  private boolean broadcasted;
  
  public void setBroadcasted(boolean broadcasted) {
    this.broadcasted = broadcasted;
  }
  
  public EntityBoxBroadcastReward(BoxReward reward) {
    this.reward = reward;
  }
  
  public BoxReward getReward() {
    return this.reward;
  }
  
  public boolean isBroadcasted() {
    return this.broadcasted;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\entity\EntityBox$EntityBoxBroadcastReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */