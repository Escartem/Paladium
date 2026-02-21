package fr.paladium.palajobs.client.ui.utils.advancement;

import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.jobs.Requirement;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import java.util.List;
import java.util.Map;

public class JobAdvancement {
  private final AbstractJob job;
  
  private final int level;
  
  private double experience;
  
  private final double nextLevelExperience;
  
  private final double progressBarValue;
  
  private final Map<Integer, List<IReward>> rewards;
  
  private final Requirement<?, ?> requirement;
  
  private final int requirementProgress;
  
  public AbstractJob getJob() {
    return this.job;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public double getExperience() {
    return this.experience;
  }
  
  public double getNextLevelExperience() {
    return this.nextLevelExperience;
  }
  
  public double getProgressBarValue() {
    return this.progressBarValue;
  }
  
  public Map<Integer, List<IReward>> getRewards() {
    return this.rewards;
  }
  
  public Requirement<?, ?> getRequirement() {
    return this.requirement;
  }
  
  public int getRequirementProgress() {
    return this.requirementProgress;
  }
  
  public JobAdvancement(JobsPlayer data, AbstractJob job) {
    this.job = job;
    this.level = data.getLevel(job.getType());
    this.experience = data.getExperience(job.getType()) - JobExpUtils.getNeededXpForLvl(this.level);
    this.nextLevelExperience = (JobExpUtils.getNeededXpForLvl(this.level + 1) - JobExpUtils.getNeededXpForLvl(this.level));
    if (this.experience > this.nextLevelExperience)
      this.experience = this.nextLevelExperience; 
    this.progressBarValue = this.experience / this.nextLevelExperience * 100.0D;
    this.rewards = job.getRewards();
    this.requirement = data.getRequirement(job.getType(), data.getLevel(job.getType()) + 1).orElse(null);
    this.requirementProgress = Math.min(((Integer)data.getJobsRequirements().getOrDefault(Integer.valueOf(job.getType().ordinal()), Integer.valueOf(0))).intValue(), (this.requirement != null) ? this.requirement.getValue() : 0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\u\\utils\advancement\JobAdvancement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */