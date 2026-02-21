package fr.paladium.palarpg.module.profile.server.manager;

import java.util.HashMap;
import java.util.Map;

public class LevelRewardData {
  private final Map<Integer, RPGLevelManager.LevelRewardObject> rewards = new HashMap<>();
  
  public Map<Integer, RPGLevelManager.LevelRewardObject> getRewards() {
    return this.rewards;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\manager\RPGLevelManager$LevelRewardData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */