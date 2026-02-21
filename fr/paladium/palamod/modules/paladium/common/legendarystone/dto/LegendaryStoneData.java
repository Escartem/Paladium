package fr.paladium.palamod.modules.paladium.common.legendarystone.dto;

import com.allatori.annotations.ControlFlowObfuscation;
import java.util.Map;

@ControlFlowObfuscation("disable")
public class LegendaryStoneData {
  private Map<String, Long> data;
  
  public LegendaryStoneData(Map<String, Long> data) {
    this.data = data;
  }
  
  public LegendaryStoneData() {}
  
  public Map<String, Long> getData() {
    return this.data;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\legendarystone\dto\LegendaryStoneData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */