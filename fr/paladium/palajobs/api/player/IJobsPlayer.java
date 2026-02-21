package fr.paladium.palajobs.api.player;

import fr.paladium.palajobs.api.quest.IQuestPlayerData;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IJobsPlayer {
  void addXp(JobType paramJobType, ObjectiveType paramObjectiveType, double paramDouble, EntityPlayer paramEntityPlayer);
  
  void addXp(JobType paramJobType, ObjectiveType paramObjectiveType, double paramDouble1, EntityPlayer paramEntityPlayer, double paramDouble2);
  
  void incrementExperience(JobType paramJobType, double paramDouble);
  
  void decrementExperience(JobType paramJobType, double paramDouble);
  
  double getExperience(JobType paramJobType);
  
  void setExperience(JobType paramJobType, double paramDouble);
  
  int getLevel(JobType paramJobType);
  
  boolean setLevel(JobType paramJobType, int paramInt);
  
  void resetExperience(JobType paramJobType);
  
  void reset();
  
  void setDoubleXpUntil(long paramLong);
  
  void setRankMultiplier(double paramDouble);
  
  void checkDailyQuests();
  
  void incrementQuestsStats(AbstractQuest paramAbstractQuest, int paramInt, EntityPlayer paramEntityPlayer);
  
  IQuestPlayerData getQuest(String paramString);
  
  boolean canCraft(@NonNull ItemStack paramItemStack);
  
  boolean canUseItem(@NonNull ItemStack paramItemStack);
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\player\IJobsPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */