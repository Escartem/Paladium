package fr.paladium.palajobs.core.jobs.type;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.AbstractJob;

public class FarmerJob extends AbstractJob {
  public static FarmerJobBuilder builder() {
    return new FarmerJobBuilder();
  }
  
  public static class FarmerJobBuilder {
    public FarmerJob build() {
      return new FarmerJob();
    }
    
    public String toString() {
      return "FarmerJob.FarmerJobBuilder()";
    }
  }
  
  public FarmerJob() {
    super(JobType.FARMER);
  }
  
  public void registerObjectives() {}
  
  public void registerRewards() {}
  
  public void registerBlacklistedCrafts() {}
  
  public void registerBlacklistedUsages() {}
  
  public void registerLvlTokensRewards() {}
  
  public void registerRequirements() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\type\FarmerJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */