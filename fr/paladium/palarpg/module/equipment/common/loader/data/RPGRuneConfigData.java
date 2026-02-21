package fr.paladium.palarpg.module.equipment.common.loader.data;

import fr.paladium.palarpg.module.equipment.common.rune.RPGRuneStatRange;
import java.util.ArrayList;
import java.util.List;

public class RPGRuneConfigData {
  public String toString() {
    return "RPGRuneConfigData(config=" + getConfig() + ")";
  }
  
  private final List<RPGRuneStatRange> config = new ArrayList<>();
  
  public List<RPGRuneStatRange> getConfig() {
    return this.config;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\RPGRuneConfigData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */