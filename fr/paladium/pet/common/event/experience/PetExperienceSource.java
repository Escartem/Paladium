package fr.paladium.pet.common.event.experience;

import fr.paladium.palajobs.api.type.JobType;

public enum PetExperienceSource {
  MINER_LEVEL_UP, HUNTER_LEVEL_UP, FARMER_LEVEL_UP, ALCHEMIST_LEVEL_UP, CONNECTION, ASSIGNMENT, ADMIN;
  
  public static PetExperienceSource getByJobName(String name) {
    JobType type = JobType.getByName(name);
    if (type == null)
      return null; 
    return getByJobType(type);
  }
  
  public static PetExperienceSource getByJobType(JobType jobType) {
    switch (jobType) {
      case MINER:
        return MINER_LEVEL_UP;
      case HUNTER:
        return HUNTER_LEVEL_UP;
      case FARMER:
        return FARMER_LEVEL_UP;
      case ALCHEMIST:
        return ALCHEMIST_LEVEL_UP;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\experience\PetExperienceSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */