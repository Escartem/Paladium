package fr.paladium.palarpg.module.entity.server.loader.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fr.paladium.palarpg.module.entity.EntityModule;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import java.util.Set;

public class RPGEntityCache {
  public static final Cache<String, RPGEntityData> ENTITY_CACHE = CacheBuilder.newBuilder().build();
  
  public static void clear() {
    ENTITY_CACHE.invalidateAll();
    EntityModule.logger.info("Entity cache cleared", new Object[0]);
  }
  
  public static void add(RPGEntityData data) {
    EntityModule.logger.info("[+] Entity " + data.getId() + " loaded", new Object[0]);
    ENTITY_CACHE.put(data.getId(), data);
  }
  
  public static RPGEntityData get(String entityId) {
    return (RPGEntityData)ENTITY_CACHE.getIfPresent(entityId);
  }
  
  public static boolean isEmpty() {
    return (ENTITY_CACHE.size() == 0L);
  }
  
  public static Set<String> getIDs() {
    return ENTITY_CACHE.asMap().keySet();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\loader\cache\RPGEntityCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */