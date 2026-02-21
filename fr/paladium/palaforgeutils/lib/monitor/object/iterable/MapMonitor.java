package fr.paladium.palaforgeutils.lib.monitor.object.iterable;

import fr.paladium.palaforgeutils.lib.monitor.Monitor;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MapMonitor<K, V> extends Monitor<Map<K, V>> {
  public void clear() {
    ((Map)get()).clear();
    publish();
  }
  
  public boolean containsKey(K key) {
    return ((Map)get()).containsKey(key);
  }
  
  public Set<Map.Entry<K, V>> entrySet() {
    return ((Map<K, V>)get()).entrySet();
  }
  
  public V get(K key) {
    return (V)((Map)get()).get(key);
  }
  
  public boolean isEmpty() {
    return ((Map)get()).isEmpty();
  }
  
  public Set<K> keySet() {
    return ((Map)get()).keySet();
  }
  
  public V put(K key, V value) {
    V result = ((Map<K, V>)get()).put(key, value);
    publish();
    return result;
  }
  
  public V remove(K key) {
    V result = (V)((Map)get()).remove(key);
    publish();
    return result;
  }
  
  public int size() {
    return ((Map)get()).size();
  }
  
  public Collection<V> values() {
    return ((Map)get()).values();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\object\iterable\MapMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */