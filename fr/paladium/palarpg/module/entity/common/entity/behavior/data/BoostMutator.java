package fr.paladium.palarpg.module.entity.common.entity.behavior.data;

import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import java.util.Map;

public class BoostMutator {
  private final Object value;
  
  private final StatMutationType type;
  
  private BoostMutator(Object value, StatMutationType type) {
    this.value = value;
    this.type = type;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public StatMutationType getType() {
    return this.type;
  }
  
  public static BoostMutator of(Map<String, Object> mapData) {
    if (!mapData.containsKey("value") || !mapData.containsKey("type"))
      throw new RuntimeException("No value or type specified for the boost effect"); 
    return new BoostMutator(mapData.getOrDefault("value", Double.valueOf(1.0D)), StatMutationType.fromString((String)mapData.getOrDefault("type", "ADDITIVE")));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\data\BoostMutator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */