package fr.paladium.palarpg.module.dungeon.server.config.level;

import com.google.gson.JsonObject;
import java.util.Map;

public class DungeonLevelEntitiesConfig {
  private final Map<String, JsonObject> entities;
  
  public String toString() {
    return "DungeonLevelEntitiesConfig(entities=" + getEntities() + ")";
  }
  
  public DungeonLevelEntitiesConfig(Map<String, JsonObject> entities) {
    this.entities = entities;
  }
  
  public Map<String, JsonObject> getEntities() {
    return this.entities;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\level\DungeonLevelEntitiesConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */