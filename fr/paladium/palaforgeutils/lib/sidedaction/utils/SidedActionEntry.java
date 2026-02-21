package fr.paladium.palaforgeutils.lib.sidedaction.utils;

import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCache;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCacheResult;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import java.lang.reflect.Method;
import java.util.Objects;
import lombok.NonNull;

public class SidedActionEntry {
  private final String name;
  
  private final Method method;
  
  private final boolean voidType;
  
  private final boolean secure;
  
  private final SidedActionCache cache;
  
  private final long clientTimeout;
  
  private final long serverTimeout;
  
  private SidedActionCacheResult cacheResult;
  
  public void setCacheResult(SidedActionCacheResult cacheResult) {
    this.cacheResult = cacheResult;
  }
  
  public SidedActionEntry(String name, Method method, boolean voidType, boolean secure, SidedActionCache cache, long clientTimeout, long serverTimeout) {
    this.name = name;
    this.method = method;
    this.voidType = voidType;
    this.secure = secure;
    this.cache = cache;
    this.clientTimeout = clientTimeout;
    this.serverTimeout = serverTimeout;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Method getMethod() {
    return this.method;
  }
  
  public boolean isVoidType() {
    return this.voidType;
  }
  
  public boolean isSecure() {
    return this.secure;
  }
  
  public SidedActionCache getCache() {
    return this.cache;
  }
  
  public long getClientTimeout() {
    return this.clientTimeout;
  }
  
  public long getServerTimeout() {
    return this.serverTimeout;
  }
  
  public SidedActionCacheResult getCacheResult() {
    return this.cacheResult;
  }
  
  @NonNull
  public static SidedActionEntry create(@NonNull Method method, boolean secure, @NonNull SidedActionCache cache) {
    if (method == null)
      throw new NullPointerException("method is marked non-null but is null"); 
    if (cache == null)
      throw new NullPointerException("cache is marked non-null but is null"); 
    return new SidedActionEntry(method.getName(), method, method.getReturnType().equals(void.class), secure, cache, DurationConverter.fromStringToMillis(cache.client()), DurationConverter.fromStringToMillis(cache.server()));
  }
  
  public boolean hasClientCache() {
    return (this.clientTimeout > 0L);
  }
  
  public boolean hasServerCache() {
    return (this.serverTimeout > 0L);
  }
  
  public SidedActionCacheResult getValidCacheResult(long timeout) {
    if (this.cacheResult == null)
      return null; 
    if (this.cacheResult.isExpired(timeout)) {
      this.cacheResult = null;
      return null;
    } 
    return this.cacheResult;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.name });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof SidedActionEntry))
      return false; 
    SidedActionEntry other = (SidedActionEntry)obj;
    return Objects.equals(this.name, other.name);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedactio\\utils\SidedActionEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */