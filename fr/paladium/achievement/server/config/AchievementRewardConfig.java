package fr.paladium.achievement.server.config;

import com.google.common.base.Strings;
import fr.paladium.achievement.AchievementMod;
import fr.paladium.achievement.server.config.additional.AchievementReward;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

@ConfigFile(path = "achievement/reward_config.json", blocking = true, reloadable = true)
public class AchievementRewardConfig implements IConfig {
  private HashMap<String, List<AchievementReward>> rewards;
  
  public HashMap<String, List<AchievementReward>> getRewards() {
    return this.rewards;
  }
  
  public boolean isValid() {
    return true;
  }
  
  public void onLoaded() {}
  
  public void onReloaded() {}
  
  public void onFailed() {}
  
  public boolean giveRewards(EntityPlayerMP player, String achievementId) {
    if (player == null || Strings.isNullOrEmpty(achievementId))
      return false; 
    List<AchievementReward> rewardList = this.rewards.get(achievementId);
    if (rewardList == null || rewardList.isEmpty())
      return false; 
    return rewardList.stream().allMatch(reward -> reward.giveReward(player));
  }
  
  public static AchievementRewardConfig get() {
    return AchievementMod.getServer().getAchievementRewardConfig();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\server\config\AchievementRewardConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */