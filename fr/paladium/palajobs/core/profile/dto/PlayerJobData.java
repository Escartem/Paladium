package fr.paladium.palajobs.core.profile.dto;

import java.util.Map;

public class PlayerJobData {
  private final PlayerJobs current;
  
  private final PlayerJobRank rank;
  
  private final Map<String, Map<String, PlayerJob>> history;
  
  public PlayerJobData(PlayerJobs current, PlayerJobRank rank, Map<String, Map<String, PlayerJob>> history) {
    this.current = current;
    this.rank = rank;
    this.history = history;
  }
  
  public PlayerJobs getCurrent() {
    return this.current;
  }
  
  public PlayerJobRank getRank() {
    return this.rank;
  }
  
  public Map<String, Map<String, PlayerJob>> getHistory() {
    return this.history;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\profile\dto\PlayerJobData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */