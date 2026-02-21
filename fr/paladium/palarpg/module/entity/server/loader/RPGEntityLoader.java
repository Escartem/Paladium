package fr.paladium.palarpg.module.entity.server.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.paladium.palarpg.module.entity.EntityModule;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.adapter.RPGEntityDataGsonTypeAdapter;
import fr.paladium.palarpg.module.entity.server.loader.cache.RPGEntityCache;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import net.minecraft.world.World;

public class RPGEntityLoader {
  public static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(RPGEntityData.class, new RPGEntityDataGsonTypeAdapter()).create();
  
  public static void load(File configDir) {
    EntityModule.logger.info("Loading RPG Entities...", new Object[0]);
    File entitiesDir = new File(configDir, "entities");
    if (!entitiesDir.exists()) {
      entitiesDir.mkdirs();
      EntityModule.logger.info("Unable to find entities category.", new Object[0]);
      return;
    } 
    for (File subDir : entitiesDir.listFiles()) {
      if (subDir.isDirectory()) {
        File dataFile = new File(subDir, "data.json");
        RPGEntityData data = null;
        if (dataFile.exists()) {
          data = loadFromFile(dataFile);
        } else {
          data = new RPGEntityData();
        } 
        data.setId(subDir.getName());
        data.onLoad();
        RPGEntityCache.add(data);
      } 
    } 
    EntityModule.logger.info(RPGEntityCache.ENTITY_CACHE.size() + " RPG Entities loaded", new Object[0]);
  }
  
  public static RPGEntityData loadFromFile(File file) {
    return loadFromFile(file, GSON);
  }
  
  public static RPGEntityData loadFromFile(File file, Gson gson) {
    String json;
    try {
      json = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } 
    return loadFromJsonString(json);
  }
  
  public static RPGEntityData loadFromJson(JsonObject jsonObject) {
    return loadFromJson(jsonObject, GSON);
  }
  
  public static RPGEntityData loadFromJson(JsonObject jsonObject, Gson gson) {
    return (RPGEntityData)gson.fromJson((JsonElement)jsonObject, RPGEntityData.class);
  }
  
  public static RPGEntityData loadFromJsonString(String json) {
    return loadFromJsonString(json, GSON);
  }
  
  public static RPGEntityData loadFromJsonString(String json, Gson gson) {
    return (RPGEntityData)gson.fromJson(json, RPGEntityData.class);
  }
  
  public static RPGMobEntity loadEntityFromFile(File file, World world) {
    return loadEntityFromFile(file, world, GSON);
  }
  
  public static RPGMobEntity loadEntityFromFile(File file, World world, Gson gson) {
    return loadFromFile(file, gson).create(world);
  }
  
  public static RPGMobEntity loadEntityFromJson(JsonObject jsonObject, World world) {
    return loadEntityFromJson(jsonObject, world, GSON);
  }
  
  public static RPGMobEntity loadEntityFromJson(JsonObject jsonObject, World world, Gson gson) {
    return ((RPGEntityData)gson.fromJson((JsonElement)jsonObject, RPGEntityData.class)).create(world);
  }
  
  public static RPGMobEntity loadEntityFromJsonString(String json, World world) {
    return loadEntityFromJsonString(json, world, GSON);
  }
  
  public static RPGMobEntity loadEntityFromJsonString(String json, World world, Gson gson) {
    return ((RPGEntityData)gson.fromJson(json, RPGEntityData.class)).create(world);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\loader\RPGEntityLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */