package fr.paladium.palamod.modules.luckyblock.profile.dto;

import com.allatori.annotations.ControlFlowObfuscation;
import java.util.List;

@ControlFlowObfuscation("disable")
public class LuckyBlockData {
  private List<LuckyBlock> data;
  
  public LuckyBlockData(List<LuckyBlock> data) {
    this.data = data;
  }
  
  public LuckyBlockData() {}
  
  public List<LuckyBlock> getData() {
    return this.data;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\profile\dto\LuckyBlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */