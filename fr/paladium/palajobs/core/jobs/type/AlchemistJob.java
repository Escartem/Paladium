package fr.paladium.palajobs.core.jobs.type;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.AbstractJob;

public class AlchemistJob extends AbstractJob {
  public static AlchemistJobBuilder builder() {
    return new AlchemistJobBuilder();
  }
  
  public static class AlchemistJobBuilder {
    public AlchemistJob build() {
      return new AlchemistJob();
    }
    
    public String toString() {
      return "AlchemistJob.AlchemistJobBuilder()";
    }
  }
  
  public AlchemistJob() {
    super(JobType.ALCHEMIST);
  }
  
  public void registerObjectives() {}
  
  public void registerRewards() {}
  
  public void registerBlacklistedCrafts() {}
  
  public void registerBlacklistedUsages() {}
  
  public void registerLvlTokensRewards() {}
  
  public void registerRequirements() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\type\AlchemistJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */