package fr.paladium.palamod.modules.pvp.profile.dto;

import com.allatori.annotations.ControlFlowObfuscation;
import fr.paladium.palamod.modules.pvp.dto.PvpStat;
import java.util.Map;

@ControlFlowObfuscation("disable")
public class PlayerPvpData {
  private Map<String, PvpStat> stats;
  
  public PlayerPvpData() {}
  
  public PlayerPvpData(Map<String, PvpStat> stats) {
    this.stats = stats;
  }
  
  public Map<String, PvpStat> getStats() {
    return this.stats;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pvp\profile\dto\PlayerPvpData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */