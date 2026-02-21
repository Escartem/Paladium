package fr.paladium.palaforgeutils.lib.java.map.cache;

import java.util.HashMap;
import java.util.Map;

public abstract class CacheHashMap<K, V> {
  private final long ttl;
  
  private final Map<K, V> cacheData;
  
  private final Map<K, Long> cacheTTL;
  
  public CacheHashMap(long ttl) {
    this.ttl = ttl;
    this.cacheData = new HashMap<>();
    this.cacheTTL = new HashMap<>();
  }
  
  public V get(K key) {
    if (!needUpdate(key))
      return getLocal(key); 
    return forceUpdate(key);
  }
  
  public V forceUpdate(K key) {
    V updated = update(key);
    if (updated == null) {
      this.cacheData.remove(key);
      this.cacheTTL.remove(key);
      return null;
    } 
    put(key, updated);
    return updated;
  }
  
  public void put(K key, V value) {
    this.cacheData.put(key, value);
    this.cacheTTL.put(key, Long.valueOf(System.currentTimeMillis()));
  }
  
  public boolean contains(K key) {
    return this.cacheData.containsKey(key);
  }
  
  public V getLocal(K key) {
    return this.cacheData.get(key);
  }
  
  private boolean needUpdate(K key) {
    if (!this.cacheTTL.containsKey(key) || !this.cacheData.containsKey(key))
      return true; 
    return (System.currentTimeMillis() - ((Long)this.cacheTTL.get(key)).longValue() > this.ttl);
  }
  
  public abstract V update(K paramK);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\java\map\cache\CacheHashMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */