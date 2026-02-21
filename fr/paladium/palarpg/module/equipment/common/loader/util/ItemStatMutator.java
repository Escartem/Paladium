package fr.paladium.palarpg.module.equipment.common.loader.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.TimedStatCapabilityMutator;

public class ItemStatMutator {
  private final EnumStats statName;
  
  private final Mutator mutator;
  
  private final int tick;
  
  public ItemStatMutator(EnumStats statName, Mutator mutator, int tick) {
    this.statName = statName;
    this.mutator = mutator;
    this.tick = tick;
  }
  
  public EnumStats getStatName() {
    return this.statName;
  }
  
  public Mutator getMutator() {
    return this.mutator;
  }
  
  public int getTick() {
    return this.tick;
  }
  
  public <T> StatCapabilityMutator<T> getMutator(String id) {
    JsonElement value = getMutator().getValue();
    if (value.isJsonPrimitive()) {
      JsonPrimitive primitive = value.getAsJsonPrimitive();
      if (primitive.isNumber()) {
        Number numberValue = primitive.getAsNumber();
        StatCapabilityMutator<Number> mutator = (this.tick > 0) ? (StatCapabilityMutator<Number>)TimedStatCapabilityMutator.create().setTick(this.tick) : StatCapabilityMutator.create();
        mutator
          .setId(id)
          .setMutationType(this.mutator.getType())
          .setValue(numberValue);
        return (StatCapabilityMutator)mutator;
      } 
      if (primitive.isString()) {
        String stringValue = primitive.getAsString();
        StatCapabilityMutator<String> mutator = (this.tick > 0) ? (StatCapabilityMutator<String>)TimedStatCapabilityMutator.create().setTick(this.tick) : StatCapabilityMutator.create();
        mutator
          .setId(id)
          .setMutationType(this.mutator.getType())
          .setValue(stringValue);
        return (StatCapabilityMutator)mutator;
      } 
    } 
    if (value.isJsonObject()) {
      JsonObject jsonObject = value.getAsJsonObject();
      StatCapabilityMutator<JsonObject> mutator = (this.tick > 0) ? (StatCapabilityMutator<JsonObject>)TimedStatCapabilityMutator.create().setTick(this.tick) : StatCapabilityMutator.create();
      mutator
        .setId(id)
        .setMutationType(this.mutator.getType())
        .setValue(jsonObject);
      return (StatCapabilityMutator)mutator;
    } 
    return null;
  }
  
  public static class Mutator {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loade\\util\ItemStatMutator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */