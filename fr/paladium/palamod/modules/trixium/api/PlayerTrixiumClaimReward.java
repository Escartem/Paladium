package fr.paladium.palamod.modules.trixium.api;

import fr.paladium.palamod.modules.trixium.utils.TrixiumReward;

public class PlayerTrixiumClaimReward {
  public String rewardUUID;
  
  public PlayerTrixiumClaimReward(TrixiumReward reward) {
    this(reward.getUuid());
  }
  
  public PlayerTrixiumClaimReward(String rewardUUID) {
    this.rewardUUID = rewardUUID;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\api\PlayerTrixiumClaimReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */