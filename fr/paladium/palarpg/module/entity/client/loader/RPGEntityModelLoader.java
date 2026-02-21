package fr.paladium.palarpg.module.entity.client.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.paladium.palarpg.module.entity.EntityModule;
import fr.paladium.palarpg.module.entity.client.loader.adapter.RPGEntityModelGsonTypeAdapter;
import fr.paladium.palarpg.module.entity.client.loader.cache.RPGEntityModelCache;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public final class RPGEntityModelLoader {
  private static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(RPGEntityModel.class, new RPGEntityModelGsonTypeAdapter()).create();
  
  public static void load(File configFile) {
    EntityModule.logger.info("Loading RPG Entities Models...", new Object[0]);
    File modelDir = new File(configFile, "models");
    if (!modelDir.exists()) {
      modelDir.mkdirs();
      EntityModule.logger.info("Unable to find models category.", new Object[0]);
      return;
    } 
    for (File subDir : modelDir.listFiles()) {
      if (subDir.isDirectory()) {
        File dataFile = new File(subDir, "data.json");
        RPGEntityModel data = null;
        if (dataFile.exists()) {
          String json;
          try {
            json = new String(Files.readAllBytes(dataFile.toPath()), StandardCharsets.UTF_8);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } 
          data = (RPGEntityModel)GSON.fromJson(json, RPGEntityModel.class);
        } else {
          data = new RPGEntityModel();
        } 
        data.setId(subDir.getName());
        data.onLoad(subDir);
        RPGEntityModelCache.add(data);
      } 
    } 
    EntityModule.logger.info(RPGEntityModelCache.MODEL_CACHE.size() + " RPG Entities Models loaded", new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\client\loader\RPGEntityModelLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */