package fr.paladium.palamod.modules.palaboss.profile.dto;

import com.allatori.annotations.ControlFlowObfuscation;
import fr.paladium.palajobs.core.pojo.boss.BossPlayerStat;
import java.util.HashMap;
import java.util.Map;

@ControlFlowObfuscation("disable")
public class JobBossStats {
  private final Map<String, BossPlayerStat> stats;
  
  public JobBossStats(Map<String, BossPlayerStat> stats) {
    this.stats = stats;
  }
  
  public Map<String, BossPlayerStat> getStats() {
    return this.stats;
  }
  
  public JobBossStats() {
    this.stats = new HashMap<>();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\profile\dto\JobBossStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */