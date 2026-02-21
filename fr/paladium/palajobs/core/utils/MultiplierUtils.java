package fr.paladium.palajobs.core.utils;

import fr.paladium.palajobs.api.type.MultiplierType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.multiplier.Multiplier;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

public class MultiplierUtils {
  public static final Double EMPTY_VALUE = Double.valueOf(0.0D);
  
  public static Multiplier getEmptyMultiplier(MultiplierType type) {
    return new Multiplier(type, EMPTY_VALUE.doubleValue());
  }
  
  public static Multiplier getFactionMultiplier(EntityPlayer player) {
    return new Multiplier(MultiplierType.FACTION, 0.0D);
  }
  
  public static Multiplier getQuestMultiplier(EntityPlayer player, JobsPlayer data) {
    if (data == null)
      return getEmptyMultiplier(MultiplierType.QUEST); 
    return new Multiplier(MultiplierType.QUEST, data.getXpMultiplier());
  }
  
  public static Multiplier getRankMultiplier(EntityPlayer player) {
    double value = EMPTY_VALUE.doubleValue();
    double rankValue = ((Double)PermissionManager.inst().getPermission(PermissibleEntity.from((Entity)player), "palajobs.xp.multiplier.", Double.class).orElse(Double.valueOf(0.0D))).doubleValue();
    double bonusValue = ((Double)PermissionManager.inst().getPermission(PermissibleEntity.from((Entity)player), "palajobs.xp.bonus.", Double.class).orElse(Double.valueOf(0.0D))).doubleValue();
    double totalValue = rankValue + bonusValue;
    JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
    jobsPlayer.setRankMultiplier(totalValue);
    return new Multiplier(MultiplierType.RANK, totalValue);
  }
  
  public static Multiplier getDoubleXpMultiplier(EntityPlayer player) {
    JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
    if (jobsPlayer == null || jobsPlayer.getDoubleXpUntil() < System.currentTimeMillis())
      return new Multiplier(MultiplierType.DOUBLE_XP, 0.0D); 
    return new Multiplier(MultiplierType.DOUBLE_XP, 100.0D);
  }
  
  public static List<Multiplier> getMultipliers(EntityPlayer player, JobsPlayer data) {
    List<Multiplier> multipliers = new ArrayList<>();
    if (data == null)
      return multipliers; 
    Multiplier factionMultiplier = getFactionMultiplier(player);
    if (factionMultiplier.getValue() > 0.0D)
      multipliers.add(factionMultiplier); 
    Multiplier questMultiplier = getQuestMultiplier(player, data);
    if (questMultiplier.getValue() > 0.0D)
      multipliers.add(questMultiplier); 
    Multiplier rankMultiplier = getRankMultiplier(player);
    if (rankMultiplier.getValue() > 0.0D)
      multipliers.add(rankMultiplier); 
    Multiplier doubleXpMultiplier = getDoubleXpMultiplier(player);
    if (doubleXpMultiplier.getValue() > 0.0D)
      multipliers.add(doubleXpMultiplier); 
    return multipliers;
  }
  
  public static Double getMultiplierForPlayer(EntityPlayer player, JobsPlayer data) {
    double multiplierValue = 0.0D;
    for (Multiplier multiplier : getMultipliers(player, data))
      multiplierValue += multiplier.getValue(); 
    return Double.valueOf(multiplierValue);
  }
  
  public static double getExperienceFromMultiplier(double experience, Double multiplier) {
    if (multiplier.doubleValue() <= 0.0D)
      return experience; 
    double result = experience / 100.0D;
    return result * multiplier.doubleValue() + experience;
  }
  
  public static Potion getPotionByName(String name) {
    for (Potion potion : Potion.field_76425_a) {
      if (potion != null && potion.func_76393_a() != null && potion.func_76393_a().equalsIgnoreCase(name))
        return potion; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\cor\\utils\MultiplierUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */