package fr.paladium.achievement.core.utils;

import fr.paladium.achievement.core.data.ExtendedAchievementPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class AchievementUtils {
  private static final Minecraft mc = Minecraft.func_71410_x();
  
  public static int getAchievementStatFromEEP(String id) {
    ExtendedAchievementPlayerData eep = ExtendedAchievementPlayerData.get((Entity)mc.field_71439_g);
    if (eep.achievementStats != null && eep.achievementStats.containsKey(id))
      return ((Integer)eep.achievementStats.get(id)).intValue(); 
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\cor\\utils\AchievementUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */