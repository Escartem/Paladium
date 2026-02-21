package fr.paladium.palajobs.core.profile.dto;

public class PlayerJob {
  private final int level;
  
  private final double xp;
  
  public PlayerJob(int level, double xp) {
    this.level = level;
    this.xp = xp;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public double getXp() {
    return this.xp;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\profile\dto\PlayerJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */