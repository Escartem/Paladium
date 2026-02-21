package fr.paladium.palarpg.module.entity.server.loader.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fr.paladium.palaboss.common.entity.properties.EntityProperties;
import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.entity.server.loader.cache.RPGEntityCache;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGDefaultStat;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGEntitySize;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RPGEntityDataGsonTypeAdapter implements JsonDeserializer<RPGEntityData> {
  public RPGEntityData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    if (jsonObject.has("id")) {
      String id = (String)context.deserialize(jsonObject.get("id"), String.class);
      RPGEntityData data = RPGEntityCache.get(id);
      if (data == null)
        throw new RuntimeException("EntityData for id \"" + id + "\" doesn't exist"); 
      data = data.copy();
      if (jsonObject.size() == 1)
        return data; 
      if (jsonObject.has("properties")) {
        JsonObject properties = jsonObject.getAsJsonObject("properties");
        if (properties.has("speed"))
          data.getProperties().setSpeed(((Double)context.deserialize(properties.get("speed"), double.class)).doubleValue()); 
        if (properties.has("health"))
          data.getProperties().setHealth(((Double)context.deserialize(properties.get("health"), double.class)).doubleValue()); 
        if (properties.has("followRange"))
          data.getProperties().setFollowRange(((Double)context.deserialize(properties.get("followRange"), double.class)).doubleValue()); 
        if (properties.has("knockback"))
          data.getProperties().setKnockback(((Float)context.deserialize(properties.get("knockback"), float.class)).floatValue()); 
        if (properties.has("explosion"))
          data.getProperties().setExplosion(((Float)context.deserialize(properties.get("explosion"), float.class)).floatValue()); 
        if (properties.has("fire"))
          data.getProperties().setFire(((Float)context.deserialize(properties.get("fire"), float.class)).floatValue()); 
        if (properties.has("attackDelay"))
          data.getProperties().setAttackDelay(((Integer)context.deserialize(properties.get("attackDelay"), int.class)).intValue()); 
      } 
      if (jsonObject.has("model"))
        data.setModel((String)context.deserialize(jsonObject.get("model"), String.class)); 
      if (jsonObject.has("spawnAnimation"))
        data.setSpawnAnimation((String)context.deserialize(jsonObject.get("spawnAnimation"), String.class)); 
      if (jsonObject.has("spawnAnimationDuration"))
        data.setSpawnAnimationDuration(((Long)context.deserialize(jsonObject.get("spawnAnimationDuration"), Long.class)).longValue()); 
      if (jsonObject.has("deathAnimationDuration"))
        data.setDeathAnimationDuration(((Long)context.deserialize(jsonObject.get("deathAnimationDuration"), Long.class)).longValue()); 
      if (jsonObject.has("size")) {
        JsonObject size = jsonObject.getAsJsonObject("size");
        if (size.has("width"))
          data.getSize().setWidth(((Float)context.deserialize(size.get("width"), float.class)).floatValue()); 
        if (size.has("height"))
          data.getSize().setHeight(((Float)context.deserialize(size.get("height"), float.class)).floatValue()); 
        if (size.has("scale"))
          data.getSize().setHeight(((Float)context.deserialize(size.get("scale"), float.class)).floatValue()); 
      } 
      if (jsonObject.has("type"))
        data.setType(RPGElementType.fromString((String)context.deserialize(jsonObject.get("type"), String.class))); 
      if (jsonObject.has("experience"))
        data.setExperience(((Float)context.deserialize(jsonObject.get("experience"), float.class)).floatValue()); 
      if (jsonObject.has("stepHeight"))
        data.setStepHeight(((Float)context.deserialize(jsonObject.get("stepHeight"), float.class)).floatValue()); 
      if (jsonObject.has("bossBar"))
        data.setBossBar(((Boolean)context.deserialize(jsonObject.get("bossBar"), boolean.class)).booleanValue()); 
      if (jsonObject.has("spawnAnimation"))
        data.setSpawnAnimation((String)context.deserialize(jsonObject.get("spawnAnimation"), String.class)); 
      if (jsonObject.has("spawnAnimationDuration"))
        data.setSpawnAnimationDuration(((Long)context.deserialize(jsonObject.get("spawnAnimationDuration"), long.class)).longValue()); 
      if (jsonObject.has("canTargetPlayer"))
        data.setCanTargetPlayer(((Boolean)context.deserialize(jsonObject.get("canTargetPlayer"), boolean.class)).booleanValue()); 
      if (jsonObject.has("immuneToFire"))
        data.setImmuneToFire(((Boolean)context.deserialize(jsonObject.get("immuneToFire"), boolean.class)).booleanValue()); 
      if (jsonObject.has("revengeAttacker"))
        data.setRevengeAttacker(((Boolean)context.deserialize(jsonObject.get("revengeAttacker"), boolean.class)).booleanValue()); 
      if (jsonObject.has("fallDamage"))
        data.setFallDamage(((Boolean)context.deserialize(jsonObject.get("fallDamage"), boolean.class)).booleanValue()); 
      if (jsonObject.has("defaultStats")) {
        JsonArray defaultStats = jsonObject.getAsJsonArray("defaultStats");
        List<RPGDefaultStat> defaultStatsList = new LinkedList<>();
        defaultStats.forEach(element -> {
              JsonObject elemObject = element.getAsJsonObject();
              RPGDefaultStat stat = new RPGDefaultStat(EnumStats.fromString((String)context.deserialize(elemObject.get("stat"), String.class)), ((Float)context.deserialize(elemObject.get("value"), float.class)).floatValue());
              defaultStatsList.add(stat);
            });
        data.setDefaultStats(defaultStatsList);
      } 
      if (jsonObject.has("attacks")) {
        JsonArray attacks = jsonObject.getAsJsonArray("attacks");
        List<RPGAttack> attackList = new LinkedList<>();
        attacks.forEach(element -> {
              JsonObject elemObject = element.getAsJsonObject();
              RPGAttack attack = new RPGAttack(elemObject, context);
              attackList.add(attack);
            });
        data.setAttacks(attackList);
      } 
      if (jsonObject.has("behaviors")) {
        JsonObject behaviorsObject = jsonObject.getAsJsonObject("behaviors");
        Map<String, Map<String, JsonElement>> behaviors = new HashMap<>();
        behaviorsObject.entrySet().forEach(entry -> {
              Map<String, JsonElement> mapElem = new HashMap<>();
              ((JsonElement)entry.getValue()).getAsJsonObject().entrySet().forEach(());
              behaviors.put(entry.getKey(), mapElem);
            });
        data.setBehaviors(behaviors);
      } 
      if (jsonObject.has("instanceClass"))
        data.setInstanceClass((String)context.deserialize(jsonObject.get("instanceClass"), String.class)); 
      return data;
    } 
    RPGEntityData newEntityData = new RPGEntityData();
    if (jsonObject.has("properties")) {
      JsonObject properties = jsonObject.getAsJsonObject("properties");
      EntityProperties.EntityPropertiesBuilder epBuilder = EntityProperties.builder();
      if (properties.has("speed"))
        epBuilder.speed(((Double)context.deserialize(properties.get("speed"), double.class)).doubleValue()); 
      if (properties.has("health"))
        epBuilder.health(((Double)context.deserialize(properties.get("health"), double.class)).doubleValue()); 
      if (properties.has("followRange"))
        epBuilder.followRange(((Double)context.deserialize(properties.get("followRange"), double.class)).doubleValue()); 
      if (properties.has("knockback"))
        epBuilder.knockback(((Float)context.deserialize(properties.get("knockback"), float.class)).floatValue()); 
      if (properties.has("explosion"))
        epBuilder.explosion(((Float)context.deserialize(properties.get("explosion"), float.class)).floatValue()); 
      if (properties.has("fire"))
        epBuilder.fire(((Float)context.deserialize(properties.get("fire"), float.class)).floatValue()); 
      if (properties.has("attackDelay"))
        epBuilder.attackDelay(((Integer)context.deserialize(properties.get("attackDelay"), int.class)).intValue()); 
      newEntityData.setProperties(epBuilder.build());
    } 
    if (jsonObject.has("model"))
      newEntityData.setModel((String)context.deserialize(jsonObject.get("model"), String.class)); 
    if (jsonObject.has("spawnAnimation"))
      newEntityData.setSpawnAnimation((String)context.deserialize(jsonObject.get("spawnAnimation"), String.class)); 
    if (jsonObject.has("spawnAnimationDuration"))
      newEntityData.setSpawnAnimationDuration(((Long)context.deserialize(jsonObject.get("spawnAnimationDuration"), Long.class)).longValue()); 
    if (jsonObject.has("deathAnimationDuration"))
      newEntityData.setDeathAnimationDuration(((Long)context.deserialize(jsonObject.get("deathAnimationDuration"), Long.class)).longValue()); 
    if (jsonObject.has("size")) {
      JsonObject size = jsonObject.getAsJsonObject("size");
      RPGEntitySize entitySize = new RPGEntitySize(size.has("width") ? ((Float)context.deserialize(size.get("width"), float.class)).floatValue() : 1.0F, size.has("height") ? ((Float)context.deserialize(size.get("height"), float.class)).floatValue() : 2.0F, size.has("scale") ? ((Float)context.deserialize(size.get("scale"), float.class)).floatValue() : 1.0F);
      newEntityData.setSize(entitySize);
    } 
    if (jsonObject.has("type"))
      newEntityData.setType(RPGElementType.fromString((String)context.deserialize(jsonObject.get("type"), String.class))); 
    if (jsonObject.has("experience"))
      newEntityData.setExperience(((Float)context.deserialize(jsonObject.get("experience"), float.class)).floatValue()); 
    if (jsonObject.has("stepHeight"))
      newEntityData.setStepHeight(((Float)context.deserialize(jsonObject.get("stepHeight"), float.class)).floatValue()); 
    if (jsonObject.has("bossBar"))
      newEntityData.setBossBar(((Boolean)context.deserialize(jsonObject.get("bossBar"), boolean.class)).booleanValue()); 
    if (jsonObject.has("spawnAnimation"))
      newEntityData.setSpawnAnimation((String)context.deserialize(jsonObject.get("spawnAnimation"), String.class)); 
    if (jsonObject.has("spawnAnimationDuration"))
      newEntityData.setSpawnAnimationDuration(((Long)context.deserialize(jsonObject.get("spawnAnimationDuration"), long.class)).longValue()); 
    if (jsonObject.has("canTargetPlayer"))
      newEntityData.setCanTargetPlayer(((Boolean)context.deserialize(jsonObject.get("canTargetPlayer"), boolean.class)).booleanValue()); 
    if (jsonObject.has("immuneToFire"))
      newEntityData.setImmuneToFire(((Boolean)context.deserialize(jsonObject.get("immuneToFire"), boolean.class)).booleanValue()); 
    if (jsonObject.has("revengeAttacker"))
      newEntityData.setRevengeAttacker(((Boolean)context.deserialize(jsonObject.get("revengeAttacker"), boolean.class)).booleanValue()); 
    if (jsonObject.has("fallDamage"))
      newEntityData.setFallDamage(((Boolean)context.deserialize(jsonObject.get("fallDamage"), boolean.class)).booleanValue()); 
    if (jsonObject.has("defaultStats")) {
      JsonArray defaultStats = jsonObject.getAsJsonArray("defaultStats");
      List<RPGDefaultStat> defaultStatsList = new LinkedList<>();
      defaultStats.forEach(element -> {
            JsonObject elemObject = element.getAsJsonObject();
            RPGDefaultStat stat = new RPGDefaultStat(EnumStats.fromString((String)context.deserialize(elemObject.get("stat"), String.class)), ((Float)context.deserialize(elemObject.get("value"), float.class)).floatValue());
            defaultStatsList.add(stat);
          });
      newEntityData.setDefaultStats(defaultStatsList);
    } 
    if (jsonObject.has("attacks")) {
      JsonArray attacks = jsonObject.getAsJsonArray("attacks");
      List<RPGAttack> attackList = new LinkedList<>();
      attacks.forEach(element -> {
            JsonObject elemObject = element.getAsJsonObject();
            RPGAttack attack = new RPGAttack(elemObject, context);
            attackList.add(attack);
          });
      newEntityData.setAttacks(attackList);
    } 
    if (jsonObject.has("behaviors")) {
      JsonObject behaviorsObject = jsonObject.getAsJsonObject("behaviors");
      Map<String, Map<String, JsonElement>> behaviors = new HashMap<>();
      behaviorsObject.entrySet().forEach(entry -> {
            Map<String, JsonElement> mapElem = new HashMap<>();
            ((JsonElement)entry.getValue()).getAsJsonObject().entrySet().forEach(());
            behaviors.put(entry.getKey(), mapElem);
          });
      newEntityData.setBehaviors(behaviors);
    } 
    if (jsonObject.has("instanceClass"))
      newEntityData.setInstanceClass((String)context.deserialize(jsonObject.get("instanceClass"), String.class)); 
    newEntityData.onLoad();
    return newEntityData;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\loader\adapter\RPGEntityDataGsonTypeAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */