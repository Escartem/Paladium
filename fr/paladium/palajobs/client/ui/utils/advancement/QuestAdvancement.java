package fr.paladium.palajobs.client.ui.utils.advancement;

import fr.paladium.palajobs.api.quest.IQuestPlayerData;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.quest.AbstractQuest;

public class QuestAdvancement {
  private final String id;
  
  private final JobType job;
  
  private final QuestType questType;
  
  private final String name;
  
  private final String description;
  
  private final int objective;
  
  private final double earnedExperience;
  
  private final String icon;
  
  private boolean completed;
  
  private int advancement;
  
  public String getId() {
    return this.id;
  }
  
  public JobType getJob() {
    return this.job;
  }
  
  public QuestType getQuestType() {
    return this.questType;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public int getObjective() {
    return this.objective;
  }
  
  public double getEarnedExperience() {
    return this.earnedExperience;
  }
  
  public String getIcon() {
    return this.icon;
  }
  
  public boolean isCompleted() {
    return this.completed;
  }
  
  public int getAdvancement() {
    return this.advancement;
  }
  
  public QuestAdvancement(JobsPlayer data, AbstractQuest quest) {
    this.id = quest.getId();
    this.job = quest.getJob();
    this.questType = quest.getType();
    this.name = quest.getName();
    this.description = quest.getDescription();
    this.earnedExperience = quest.getEarnedExperience();
    this.icon = quest.getIcon();
    IQuestPlayerData questData = data.getQuest(quest.getId());
    if (questData != null) {
      this.completed = questData.isCompleted();
      this.advancement = questData.getProgress();
    } 
    this.objective = quest.getQuantity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\u\\utils\advancement\QuestAdvancement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */