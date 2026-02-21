package fr.paladium.palarpg.module.equipment.common.rune;

import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;

public class RPGRuneStatRange {
  private EnumStats stat;
  
  private StatMutationType mutationType;
  
  private double min;
  
  private double max;
  
  public EnumStats getStat() {
    return this.stat;
  }
  
  public StatMutationType getMutationType() {
    return this.mutationType;
  }
  
  public double getMin() {
    return this.min;
  }
  
  public double getMax() {
    return this.max;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\rune\RPGRuneStatRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */