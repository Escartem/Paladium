package fr.paladium.palajobs.client.ui.utils;

import fr.paladium.palajobs.api.quest.IQuestPlayerData;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import fr.paladium.palajobs.client.ui.utils.advancement.QuestAdvancement;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.core.quest.QuestRegistry;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palajobs.core.tokens.LvlToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class JobUIData {
  private List<QuestAdvancement> quests;
  
  private JobAdvancement farmerAdvancement;
  
  private JobAdvancement minerAdvancement;
  
  private JobAdvancement hunterAdvancement;
  
  private JobAdvancement alchemistAdvancement;
  
  private double multiplier;
  
  private double questMultiplier;
  
  private long doubleXpUntil;
  
  private double rankMultiplier;
  
  public boolean hasPendingToken;
  
  public List<QuestAdvancement> getQuests() {
    return this.quests;
  }
  
  public JobAdvancement getFarmerAdvancement() {
    return this.farmerAdvancement;
  }
  
  public JobAdvancement getMinerAdvancement() {
    return this.minerAdvancement;
  }
  
  public JobAdvancement getHunterAdvancement() {
    return this.hunterAdvancement;
  }
  
  public JobAdvancement getAlchemistAdvancement() {
    return this.alchemistAdvancement;
  }
  
  public double getMultiplier() {
    return this.multiplier;
  }
  
  public double getQuestMultiplier() {
    return this.questMultiplier;
  }
  
  public long getDoubleXpUntil() {
    return this.doubleXpUntil;
  }
  
  public double getRankMultiplier() {
    return this.rankMultiplier;
  }
  
  public boolean isHasPendingToken() {
    return this.hasPendingToken;
  }
  
  public JobUIData(EntityPlayer player) {
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null)
      return; 
    this.questMultiplier = data.getXpMultiplier();
    this.doubleXpUntil = data.getDoubleXpUntil();
    this.rankMultiplier = data.getRankMultiplier();
    this.multiplier = this.questMultiplier + ((this.doubleXpUntil > System.currentTimeMillis()) ? 100 : false) + this.rankMultiplier;
    this.hasPendingToken = (data.getLvlTokens().stream().filter(j -> (j.getJobType() == JobType.HUNTER)).count() > 0L);
    initQuests(data);
    initJobs(data);
  }
  
  private void initJobs(JobsPlayer data) {
    JobRegistry registry = JobRegistry.getInstance();
    this.farmerAdvancement = new JobAdvancement(data, (AbstractJob)registry.getFarmerJob());
    this.minerAdvancement = new JobAdvancement(data, (AbstractJob)registry.getMinerJob());
    this.hunterAdvancement = new JobAdvancement(data, (AbstractJob)registry.getHunterJob());
    this.alchemistAdvancement = new JobAdvancement(data, (AbstractJob)registry.getAlchemistJob());
  }
  
  private void initQuests(JobsPlayer data) {
    this.quests = new ArrayList<>();
    QuestRegistry registry = QuestRegistry.getInstance();
    for (IQuestPlayerData quest : data.getDailyQuests()) {
      Optional<AbstractQuest> abstractQuest = registry.getQuestById(quest.getQuestId());
      if (!abstractQuest.isPresent())
        continue; 
      QuestAdvancement advancement = new QuestAdvancement(data, abstractQuest.get());
      this.quests.add(advancement);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\u\\utils\JobUIData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */