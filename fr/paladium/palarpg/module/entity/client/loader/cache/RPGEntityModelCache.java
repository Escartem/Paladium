package fr.paladium.palarpg.module.entity.client.loader.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fr.paladium.palarpg.module.entity.EntityModule;
import fr.paladium.palarpg.module.entity.client.loader.RPGEntityModel;
import java.util.Set;

public final class RPGEntityModelCache {
  public static final Cache<String, RPGEntityModel> MODEL_CACHE = CacheBuilder.newBuilder().build();
  
  public static void clear() {
    EntityModule.logger.info("Model cache cleared", new Object[0]);
    MODEL_CACHE.invalidateAll();
  }
  
  public static void add(RPGEntityModel model) {
    EntityModule.logger.info("[+] Model " + model.getId() + " loaded", new Object[0]);
    MODEL_CACHE.put(model.getId(), model);
  }
  
  public static RPGEntityModel get(String id) {
    return (RPGEntityModel)MODEL_CACHE.getIfPresent(id);
  }
  
  public static Set<String> getIDs() {
    return MODEL_CACHE.asMap().keySet();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\client\loader\cache\RPGEntityModelCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */