package fr.paladium.palajobs.core.profile.dto;

public class PlayerJobs {
  private final PlayerJob alchemist;
  
  private final PlayerJob farmer;
  
  private final PlayerJob hunter;
  
  private final PlayerJob miner;
  
  public PlayerJobs(PlayerJob alchemist, PlayerJob farmer, PlayerJob hunter, PlayerJob miner) {
    this.alchemist = alchemist;
    this.farmer = farmer;
    this.hunter = hunter;
    this.miner = miner;
  }
  
  public PlayerJob getAlchemist() {
    return this.alchemist;
  }
  
  public PlayerJob getFarmer() {
    return this.farmer;
  }
  
  public PlayerJob getHunter() {
    return this.hunter;
  }
  
  public PlayerJob getMiner() {
    return this.miner;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\profile\dto\PlayerJobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */