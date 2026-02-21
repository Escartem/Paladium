package fr.paladium.palamod.modules.palaboss.profile.dto;

import com.allatori.annotations.ControlFlowObfuscation;
import fr.paladium.palamod.modules.palaboss.common.eep.BossPlayerStat;
import java.util.HashMap;
import java.util.Map;

@ControlFlowObfuscation("disable")
public class BossStats {
  private final Map<String, BossPlayerStat> stats;
  
  public BossStats(Map<String, BossPlayerStat> stats) {
    this.stats = stats;
  }
  
  public Map<String, BossPlayerStat> getStats() {
    return this.stats;
  }
  
  public BossStats() {
    this.stats = new HashMap<>();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\profile\dto\BossStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */