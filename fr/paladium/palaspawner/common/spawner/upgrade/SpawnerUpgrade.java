package fr.paladium.palaspawner.common.spawner.upgrade;

import fr.paladium.palajobs.api.type.JobType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpawnerUpgrade {
  private final String id;
  
  private final List<JobRequirement> requirements;
  
  public String getId() {
    return this.id;
  }
  
  public List<JobRequirement> getRequirements() {
    return this.requirements;
  }
  
  public SpawnerUpgrade(String id) {
    this.id = id;
    this.requirements = new ArrayList<>();
  }
  
  public static SpawnerUpgrade of(String id) {
    return new SpawnerUpgrade(id);
  }
  
  public SpawnerUpgrade addRequirement(JobRequirement requirement) {
    this.requirements.add(requirement);
    return this;
  }
  
  public boolean hasRequirements(Map<JobType, Integer> jobsData) {
    if (jobsData.isEmpty())
      return false; 
    for (Map.Entry<JobType, Integer> entry : jobsData.entrySet()) {
      JobType jobType = entry.getKey();
      Integer level = entry.getValue();
      for (JobRequirement requirement : this.requirements) {
        if (requirement.getJobType().equals(jobType) && level.intValue() < requirement.getMinLevel())
          return false; 
      } 
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawne\\upgrade\SpawnerUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */