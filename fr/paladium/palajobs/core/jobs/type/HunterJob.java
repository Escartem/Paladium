package fr.paladium.palajobs.core.jobs.type;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.AbstractJob;

public class HunterJob extends AbstractJob {
  public static HunterJobBuilder builder() {
    return new HunterJobBuilder();
  }
  
  public static class HunterJobBuilder {
    public HunterJob build() {
      return new HunterJob();
    }
    
    public String toString() {
      return "HunterJob.HunterJobBuilder()";
    }
  }
  
  public HunterJob() {
    super(JobType.HUNTER);
  }
  
  public void registerObjectives() {}
  
  public void registerRewards() {}
  
  public void registerBlacklistedCrafts() {}
  
  public void registerBlacklistedUsages() {}
  
  public void registerLvlTokensRewards() {}
  
  public void registerRequirements() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\type\HunterJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */