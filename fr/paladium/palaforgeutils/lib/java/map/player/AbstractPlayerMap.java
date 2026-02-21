package fr.paladium.palaforgeutils.lib.java.map.player;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.entity.Entity;

public class AbstractPlayerMap<V> {
  private final Map<String, V> localMap = new HashMap<>();
  
  public void clear() {
    this.localMap.clear();
  }
  
  public boolean containsKey(Entity entity) {
    return containsKey(entity.func_110124_au());
  }
  
  public boolean containsKey(UUID uuid) {
    return containsKey(UUIDUtils.toString(uuid));
  }
  
  public boolean containsKey(String key) {
    return this.localMap.containsKey(key);
  }
  
  public Set<Map.Entry<String, V>> entrySet() {
    return this.localMap.entrySet();
  }
  
  public V get(Entity entity) {
    return get(entity.func_110124_au());
  }
  
  public V get(UUID uuid) {
    return get(UUIDUtils.toString(uuid));
  }
  
  public V get(String key) {
    return this.localMap.get(key);
  }
  
  public boolean isEmpty() {
    return this.localMap.isEmpty();
  }
  
  public Set<String> keySet() {
    return this.localMap.keySet();
  }
  
  public V put(Entity entity, V value) {
    return put(entity.func_110124_au(), value);
  }
  
  public V put(UUID uuid, V value) {
    return put(UUIDUtils.toString(uuid), value);
  }
  
  public V put(String key, V value) {
    return this.localMap.put(key, value);
  }
  
  public V remove(Entity entity) {
    return remove(entity.func_110124_au());
  }
  
  public V remove(UUID uuid) {
    return remove(UUIDUtils.toString(uuid));
  }
  
  public V remove(String key) {
    return this.localMap.remove(key);
  }
  
  public int size() {
    return this.localMap.size();
  }
  
  public Collection<V> values() {
    return this.localMap.values();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\java\map\player\AbstractPlayerMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */