package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DungeonRoomDataEntityConfig {
  @SerializedName("entity_count")
  private final JsonElement entityCount;
  
  private final List<DungeonRoomDataEntityElementConfig> entities;
  
  public DungeonRoomDataEntityConfig(JsonElement entityCount, List<DungeonRoomDataEntityElementConfig> entities) {
    this.entityCount = entityCount;
    this.entities = entities;
  }
  
  public JsonElement getEntityCount() {
    return this.entityCount;
  }
  
  public List<DungeonRoomDataEntityElementConfig> getEntities() {
    return this.entities;
  }
  
  public class DungeonRoomDataEntityElementConfig {
    private final String entity;
    
    private final JsonElement weight;
    
    private final JsonElement minCount;
    
    private final JsonElement maxCount;
    
    public DungeonRoomDataEntityElementConfig(String entity, JsonElement weight, JsonElement minCount, JsonElement maxCount) {
      this.entity = entity;
      this.weight = weight;
      this.minCount = minCount;
      this.maxCount = maxCount;
    }
    
    public String getEntity() {
      return this.entity;
    }
    
    public JsonElement getWeight() {
      return this.weight;
    }
    
    public JsonElement getMinCount() {
      return this.minCount;
    }
    
    public JsonElement getMaxCount() {
      return this.maxCount;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\EntityDungeonRoomBehavior$DungeonRoomDataEntityConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */