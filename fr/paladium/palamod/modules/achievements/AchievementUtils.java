package fr.paladium.palamod.modules.achievements;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;

public class AchievementUtils {
  public static void performCheck(Class<? extends Achievement> clazz, EntityPlayer player, int quantity) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(clazz))
      Achievement.incrementStats(achievement, player, quantity); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\AchievementUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */