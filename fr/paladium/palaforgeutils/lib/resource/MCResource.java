package fr.paladium.palaforgeutils.lib.resource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cpw.mods.fml.common.Mod;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class MCResource extends ResourceLocation {
  private static final Cache<String, MCResource> DEFAULT_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  private final String key;
  
  private final Cache<String, MCResource> cache;
  
  public String getKey() {
    return this.key;
  }
  
  public Cache<String, MCResource> getCache() {
    return this.cache;
  }
  
  private MCResource(@NonNull String location, @NonNull String key, Cache<String, MCResource> cache) {
    super(location);
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    if (key == null)
      throw new NullPointerException("key is marked non-null but is null"); 
    this.key = key;
    this.cache = cache;
  }
  
  private MCResource(@NonNull ResourceLocation location, @NonNull String key, Cache<String, MCResource> cache) {
    super(location.func_110624_b(), location.func_110623_a());
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    if (key == null)
      throw new NullPointerException("key is marked non-null but is null"); 
    this.key = key;
    this.cache = cache;
  }
  
  private MCResource(String domain, @NonNull String path, @NonNull String key, Cache<String, MCResource> cache) {
    super(domain, path);
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    if (key == null)
      throw new NullPointerException("key is marked non-null but is null"); 
    this.key = key;
    this.cache = cache;
  }
  
  @NonNull
  public static MCResource of(@NonNull Object mod, @NonNull String path) {
    if (mod == null)
      throw new NullPointerException("mod is marked non-null but is null"); 
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    return of(mod, path, DEFAULT_CACHE);
  }
  
  @NonNull
  public static MCResource of(@NonNull Object mod, @NonNull String path, Cache<String, MCResource> cache) {
    if (mod == null)
      throw new NullPointerException("mod is marked non-null but is null"); 
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    Mod annotation = mod.getClass().<Mod>getAnnotation(Mod.class);
    if (annotation == null)
      throw new IllegalArgumentException("The object is not a mod instance"); 
    String modid = annotation.modid();
    if (modid == null || modid.isEmpty())
      throw new IllegalArgumentException("The mod instance has no modid"); 
    return of(modid, path, cache);
  }
  
  @NonNull
  public static MCResource of(@NonNull ResourceLocation location) {
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    return of(location, DEFAULT_CACHE);
  }
  
  @NonNull
  public static MCResource of(@NonNull ResourceLocation location, Cache<String, MCResource> cache) {
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    return of(location.func_110624_b(), location.func_110623_a(), cache);
  }
  
  @NonNull
  public static MCResource of(String domain, @NonNull String path) {
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    return of(domain, path, DEFAULT_CACHE);
  }
  
  @NonNull
  public static MCResource of(String domain, @NonNull String path, Cache<String, MCResource> cache) {
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    return of(((domain == null || domain.isEmpty()) ? "minecraft" : domain) + ":" + path, cache);
  }
  
  @NonNull
  public static MCResource of(@NonNull String location) {
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    return of(location, DEFAULT_CACHE);
  }
  
  @NonNull
  public static MCResource of(@NonNull String location, Cache<String, MCResource> cache) {
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    if (cache != null)
      try {
        return (MCResource)cache.get(location, () -> new MCResource(location, location, cache));
      } catch (ExecutionException executionException) {} 
    return new MCResource(location, location, cache);
  }
  
  @NonNull
  public MCResource copy() {
    return new MCResource(func_110624_b(), func_110623_a(), this.key, this.cache);
  }
  
  @NonNull
  public MCResource invalidate() {
    if (this.cache != null)
      this.cache.invalidate(this.key); 
    return this;
  }
  
  public boolean exists() {
    try {
      return (Minecraft.func_71410_x().func_110442_L().func_110536_a(this) != null);
    } catch (IOException e) {
      return false;
    } 
  }
  
  @NonNull
  public Optional<IResource> getResource() {
    try {
      return Optional.ofNullable(Minecraft.func_71410_x().func_110442_L().func_110536_a(this));
    } catch (IOException e) {
      return Optional.empty();
    } 
  }
  
  @NonNull
  public Optional<InputStream> getInputStream() {
    try {
      IResource resource = Minecraft.func_71410_x().func_110442_L().func_110536_a(this);
      if (resource == null)
        return Optional.empty(); 
      return Optional.ofNullable(resource.func_110527_b());
    } catch (IOException e) {
      return Optional.empty();
    } 
  }
  
  @NonNull
  public Optional<BufferedImage> asImage() {
    Optional<InputStream> inputStream = getInputStream();
    if (!inputStream.isPresent())
      return Optional.empty(); 
    InputStream stream = inputStream.get();
    try {
      BufferedImage image = ImageIO.read(stream);
      if (image == null)
        return (Optional)Optional.empty(); 
      return Optional.of(image);
    } catch (IOException e) {
      return (Optional)Optional.empty();
    } finally {
      try {
        stream.close();
      } catch (IOException iOException) {}
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\resource\MCResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */