package fr.paladium.palaforgeutils.lib.resource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cpw.mods.fml.common.Mod;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;

public class MCResourceBuilder {
  private static final Cache<String, MCResource> DEFAULT_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  private final String domain;
  
  private String path;
  
  private Cache<String, MCResource> cache;
  
  public String getDomain() {
    return this.domain;
  }
  
  public String getPath() {
    return this.path;
  }
  
  public Cache<String, MCResource> getCache() {
    return this.cache;
  }
  
  public MCResourceBuilder(@NonNull String domain, String path, Cache<String, MCResource> cache) {
    if (domain == null)
      throw new NullPointerException("domain is marked non-null but is null"); 
    this.domain = domain;
    this.path = path;
    this.cache = (cache != null) ? cache : DEFAULT_CACHE;
    if (this.path != null && this.path.endsWith("/"))
      this.path = this.path.substring(0, this.path.length() - 1); 
  }
  
  @NonNull
  public static MCResourceBuilder of() {
    return of("minecraft", (String)null);
  }
  
  @NonNull
  public static MCResourceBuilder of(@NonNull Object mod) {
    if (mod == null)
      throw new NullPointerException("mod is marked non-null but is null"); 
    return of(mod, (String)null);
  }
  
  @NonNull
  public static MCResourceBuilder of(@NonNull Object mod, String path) {
    if (mod == null)
      throw new NullPointerException("mod is marked non-null but is null"); 
    Mod annotation = mod.getClass().<Mod>getAnnotation(Mod.class);
    if (annotation == null)
      throw new IllegalArgumentException("The object is not a mod instance"); 
    String modid = annotation.modid();
    if (modid == null || modid.isEmpty())
      throw new IllegalArgumentException("The mod instance has no modid"); 
    return of(modid, path);
  }
  
  @NonNull
  public static MCResourceBuilder of(@NonNull String domain) {
    if (domain == null)
      throw new NullPointerException("domain is marked non-null but is null"); 
    return of(domain, (String)null);
  }
  
  @NonNull
  public static MCResourceBuilder of(@NonNull String domain, String path) {
    if (domain == null)
      throw new NullPointerException("domain is marked non-null but is null"); 
    return new MCResourceBuilder(domain, path, DEFAULT_CACHE);
  }
  
  @NonNull
  public MCResource create() {
    return MCResource.of(this.domain, (this.path != null) ? this.path : "", this.cache);
  }
  
  @NonNull
  public MCResource create(@NonNull String path) {
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    if (this.path != null && !this.path.isEmpty())
      path = this.path + "/" + (path.endsWith("/") ? path.substring(0, path.length() - 1) : path); 
    return MCResource.of(this.domain, path, this.cache);
  }
  
  @NonNull
  public MCResourceBuilder path(String path) {
    this.path = path;
    return this;
  }
  
  @NonNull
  public MCResourceBuilder cache(Cache<String, MCResource> cache) {
    this.cache = cache;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\resource\MCResourceBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */