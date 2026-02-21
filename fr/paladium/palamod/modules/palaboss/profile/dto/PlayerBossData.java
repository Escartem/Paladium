package fr.paladium.palamod.modules.palaboss.profile.dto;

import com.allatori.annotations.ControlFlowObfuscation;

@ControlFlowObfuscation("disable")
public class PlayerBossData {
  private BossStats bossStats;
  
  private JobBossStats jobBossStats;
  
  public PlayerBossData(BossStats bossStats, JobBossStats jobBossStats) {
    this.bossStats = bossStats;
    this.jobBossStats = jobBossStats;
  }
  
  public BossStats getBossStats() {
    return this.bossStats;
  }
  
  public JobBossStats getJobBossStats() {
    return this.jobBossStats;
  }
  
  public PlayerBossData() {
    this.bossStats = new BossStats();
    this.jobBossStats = new JobBossStats();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\profile\dto\PlayerBossData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */