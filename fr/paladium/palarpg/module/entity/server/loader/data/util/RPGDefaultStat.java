package fr.paladium.palarpg.module.entity.server.loader.data.util;

import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;

public class RPGDefaultStat {
  private final EnumStats stat;
  
  private final float value;
  
  public String toString() {
    return "RPGDefaultStat(stat=" + getStat() + ", value=" + getValue() + ")";
  }
  
  public RPGDefaultStat(EnumStats stat, float value) {
    this.stat = stat;
    this.value = value;
  }
  
  public EnumStats getStat() {
    return this.stat;
  }
  
  public float getValue() {
    return this.value;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\loader\dat\\util\RPGDefaultStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */