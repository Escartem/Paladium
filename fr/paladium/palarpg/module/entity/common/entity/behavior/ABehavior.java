package fr.paladium.palarpg.module.entity.common.entity.behavior;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.entity.common.condition.IMolangParserHolder;
import fr.paladium.palarpg.module.entity.common.condition.RPGConditionParser;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.RPGEntityLoader;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ABehavior implements IBehavior {
  public void setBehaviorData(Map<String, JsonElement> behaviorData) {
    this.behaviorData = behaviorData;
  }
  
  public void setConditions(List<String> conditions) {
    this.conditions = conditions;
  }
  
  public String toString() {
    return "ABehavior(behaviorData=" + getBehaviorData() + ", behaviorOwner=" + getBehaviorOwner() + ", conditions=" + getConditions() + ")";
  }
  
  private Map<String, JsonElement> behaviorData = new HashMap<>();
  
  private RPGMobEntity behaviorOwner;
  
  private List<String> conditions;
  
  public Map<String, JsonElement> getBehaviorData() {
    return this.behaviorData;
  }
  
  public RPGMobEntity getBehaviorOwner() {
    return this.behaviorOwner;
  }
  
  public List<String> getConditions() {
    return this.conditions;
  }
  
  protected ABehavior(Map<String, JsonElement> behaviorData) {
    if (behaviorData != null)
      this.behaviorData = behaviorData; 
    this.conditions = getData("conditions", new ArrayList<>());
  }
  
  public boolean canExecute() {
    return !this.conditions.isEmpty() ? RPGConditionParser.evaluates((IMolangParserHolder)this.behaviorOwner, this.conditions) : true;
  }
  
  public ABehavior setBehaviorOwner(RPGMobEntity owner) {
    this.behaviorOwner = owner;
    return this;
  }
  
  public <T> T getData(String dataID, Class<T> clazz) {
    JsonElement elem = this.behaviorData.get(dataID);
    if (elem == null || elem.isJsonNull())
      return null; 
    if (RPGEntityData.class.isAssignableFrom(clazz) && DungeonWorld.get((getBehaviorOwner()).field_70170_p).isPresent())
      return (T)((DungeonWorld)DungeonWorld.get((getBehaviorOwner()).field_70170_p).get()).getGson().fromJson(elem, clazz); 
    return (T)RPGEntityLoader.GSON.fromJson(elem, clazz);
  }
  
  public <T> T getData(String dataID, T defaultValue) {
    JsonElement elem = this.behaviorData.get(dataID);
    if (elem == null || elem.isJsonNull())
      return defaultValue; 
    return (T)RPGEntityLoader.GSON.fromJson(elem, (defaultValue == null) ? Object.class : defaultValue.getClass());
  }
  
  public abstract String getID();
  
  public abstract ABehavior copy(Map<String, JsonElement> paramMap, RPGMobEntity paramRPGMobEntity);
  
  public ABehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\ABehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */