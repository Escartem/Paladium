package fr.paladium.palaspawner.common.spawner.upgrade;

import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;

public class JobRequirement {
  private final JobType jobType;
  
  private final int minLevel;
  
  private JobRequirement(JobType jobType, int minLevel) {
    this.jobType = jobType;
    this.minLevel = minLevel;
  }
  
  public static JobRequirement of(JobType jobType, int minLevel) {
    return new JobRequirement(jobType, minLevel);
  }
  
  public JobType getJobType() {
    return this.jobType;
  }
  
  public int getMinLevel() {
    return this.minLevel;
  }
  
  public boolean hasRequirement(IJobsPlayer jobsPlayer) {
    return (jobsPlayer.getLevel(this.jobType) >= this.minLevel);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawne\\upgrade\JobRequirement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */