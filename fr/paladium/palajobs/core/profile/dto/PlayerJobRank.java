package fr.paladium.palajobs.core.profile.dto;

import java.util.Map;

public class PlayerJobRank {
  private final long alchemist;
  
  private final long farmer;
  
  private final long hunter;
  
  private final long miner;
  
  public PlayerJobRank(long alchemist, long farmer, long hunter, long miner) {
    this.alchemist = alchemist;
    this.farmer = farmer;
    this.hunter = hunter;
    this.miner = miner;
  }
  
  public long getAlchemist() {
    return this.alchemist;
  }
  
  public long getFarmer() {
    return this.farmer;
  }
  
  public long getHunter() {
    return this.hunter;
  }
  
  public long getMiner() {
    return this.miner;
  }
  
  public PlayerJobRank(Map<String, Long> body) {
    this.alchemist = ((Long)body.getOrDefault("job-alchemist", Long.valueOf(0L))).longValue();
    this.farmer = ((Long)body.getOrDefault("job-farmer", Long.valueOf(0L))).longValue();
    this.hunter = ((Long)body.getOrDefault("job-hunter", Long.valueOf(0L))).longValue();
    this.miner = ((Long)body.getOrDefault("job-miner", Long.valueOf(0L))).longValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\profile\dto\PlayerJobRank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */