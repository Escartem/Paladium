package fr.paladium.palapass.server.config.reward;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.pojo.reward.RewardLevel;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FreeRewardsConfig implements IConfig {
  private final List<RewardLevel> rewardLevels = new ArrayList<>();
  
  public List<RewardLevel> getRewardLevels() {
    return this.rewardLevels;
  }
  
  private int month = -1;
  
  public int getMonth() {
    return this.month;
  }
  
  public void onLoaded() {
    PalapassManager manager = PalapassManager.getInstance();
    String path = ((ConfigFile)getClass().<ConfigFile>getAnnotation(ConfigFile.class)).path();
    this.month = ConfigItemUtils.parsePathConfigToMonth(path);
    this.rewardLevels.forEach(reward -> {
          if (manager.getRewards().containsKey(Integer.valueOf(this.month))) {
            ((List<RewardLevel>)manager.getRewards().get(Integer.valueOf(this.month))).add(reward);
          } else {
            manager.getRewards().put(Integer.valueOf(this.month), new ArrayList(Arrays.asList((Object[])new RewardLevel[] { reward })));
          } 
        });
  }
  
  public void onReloaded() {
    ((List)PalapassManager.getInstance().getRewards().get(Integer.valueOf(this.month))).clear();
    onLoaded();
  }
  
  public void onFailed() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\config\reward\FreeRewardsConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */