package fr.paladium.palarpg.module.dungeon.server.config.level;

import com.google.gson.JsonElement;

public class DungeonLevelGenerationConfig {
  private final JsonElement min;
  
  private final JsonElement max;
  
  private final JsonElement weight;
  
  public DungeonLevelGenerationConfig(JsonElement min, JsonElement max, JsonElement weight) {
    this.min = min;
    this.max = max;
    this.weight = weight;
  }
  
  public JsonElement getMin() {
    return this.min;
  }
  
  public JsonElement getMax() {
    return this.max;
  }
  
  public JsonElement getWeight() {
    return this.weight;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\level\DungeonLevelConfig$DungeonLevelGenerationConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */