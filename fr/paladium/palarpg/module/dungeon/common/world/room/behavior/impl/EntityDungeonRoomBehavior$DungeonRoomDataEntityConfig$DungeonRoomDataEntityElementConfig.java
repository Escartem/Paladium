package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl;

import com.google.gson.JsonElement;

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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\EntityDungeonRoomBehavior$DungeonRoomDataEntityConfig$DungeonRoomDataEntityElementConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */