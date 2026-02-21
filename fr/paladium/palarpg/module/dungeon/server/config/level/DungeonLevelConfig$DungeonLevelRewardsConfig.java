package fr.paladium.palarpg.module.dungeon.server.config.level;

import com.google.gson.JsonElement;

public class DungeonLevelRewardsConfig {
  private final DungeonLevelRewardsJobsConfig jobs;
  
  public String toString() {
    return "DungeonLevelConfig.DungeonLevelRewardsConfig(jobs=" + getJobs() + ")";
  }
  
  public DungeonLevelRewardsConfig(DungeonLevelRewardsJobsConfig jobs) {
    this.jobs = jobs;
  }
  
  public DungeonLevelRewardsJobsConfig getJobs() {
    return this.jobs;
  }
  
  public class DungeonLevelRewardsJobsConfig {
    private final String job;
    
    private final JsonElement experience;
    
    public DungeonLevelRewardsJobsConfig(String job, JsonElement experience) {
      this.job = job;
      this.experience = experience;
    }
    
    public String getJob() {
      return this.job;
    }
    
    public JsonElement getExperience() {
      return this.experience;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\level\DungeonLevelConfig$DungeonLevelRewardsConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */