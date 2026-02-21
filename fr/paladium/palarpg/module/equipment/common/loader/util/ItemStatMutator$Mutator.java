package fr.paladium.palarpg.module.equipment.common.loader.util;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;

public class Mutator {
  private JsonElement value;
  
  private StatMutationType type;
  
  public void setValue(JsonElement value) {
    this.value = value;
  }
  
  public void setType(StatMutationType type) {
    this.type = type;
  }
  
  public Mutator(JsonElement value, StatMutationType type) {
    this.value = value;
    this.type = type;
  }
  
  public JsonElement getValue() {
    return this.value;
  }
  
  public StatMutationType getType() {
    return this.type;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loade\\util\ItemStatMutator$Mutator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */