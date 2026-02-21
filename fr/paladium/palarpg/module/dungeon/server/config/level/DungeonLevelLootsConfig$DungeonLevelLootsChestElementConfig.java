package fr.paladium.palarpg.module.dungeon.server.config.level;

import com.google.gson.JsonElement;

public class DungeonLevelLootsChestElementConfig {
  private final JsonElement bonus;
  
  private final JsonElement weight;
  
  public String toString() {
    return "DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig(bonus=" + getBonus() + ", weight=" + getWeight() + ")";
  }
  
  public DungeonLevelLootsChestElementConfig(JsonElement bonus, JsonElement weight) {
    this.bonus = bonus;
    this.weight = weight;
  }
  
  public JsonElement getBonus() {
    return this.bonus;
  }
  
  public JsonElement getWeight() {
    return this.weight;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\level\DungeonLevelLootsConfig$DungeonLevelLootsChestElementConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */