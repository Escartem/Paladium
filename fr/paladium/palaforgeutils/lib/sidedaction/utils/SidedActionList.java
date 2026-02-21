package fr.paladium.palaforgeutils.lib.sidedaction.utils;

import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCache;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

public class SidedActionList {
  private final Map<String, SidedActionEntry> data = new HashMap<>();
  
  public Map<String, SidedActionEntry> getData() {
    return this.data;
  }
  
  public boolean has(@NonNull String methodName) {
    if (methodName == null)
      throw new NullPointerException("methodName is marked non-null but is null"); 
    return this.data.containsKey(methodName);
  }
  
  public SidedActionEntry get(@NonNull String methodName) {
    if (methodName == null)
      throw new NullPointerException("methodName is marked non-null but is null"); 
    return this.data.get(methodName);
  }
  
  @NonNull
  public SidedActionEntry add(@NonNull String methodName, @NonNull Method method, boolean secure, @NonNull SidedActionCache cache) {
    if (methodName == null)
      throw new NullPointerException("methodName is marked non-null but is null"); 
    if (method == null)
      throw new NullPointerException("method is marked non-null but is null"); 
    if (cache == null)
      throw new NullPointerException("cache is marked non-null but is null"); 
    if (has(methodName))
      throw new RuntimeException("[ServerAction] Method " + methodName + " is already registered."); 
    SidedActionEntry entry = SidedActionEntry.create(method, secure, cache);
    this.data.put(methodName, entry);
    return entry;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedactio\\utils\SidedActionList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */