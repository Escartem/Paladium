package fr.paladium.palaforgeutils.lib.java.clazz;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;

public class ClassHelper<T> {
  private ClassHelper(Class<T> clazz) {
    this.clazz = clazz;
  }
  
  public static <T> ClassHelper<T> of(Class<T> clazz) {
    return new ClassHelper<>(clazz);
  }
  
  private static final Cache<Class<?>, String> CLASS_NAME_CACHE = CacheBuilder.newBuilder().expireAfterAccess(1L, TimeUnit.HOURS).build();
  
  private static final Cache<Class<?>, String> CLASS_SIMPLE_NAME_CACHE = CacheBuilder.newBuilder().expireAfterAccess(1L, TimeUnit.HOURS).build();
  
  private final Class<T> clazz;
  
  public Class<T> getClazz() {
    return this.clazz;
  }
  
  @NonNull
  public String getName() {
    String name = (String)CLASS_NAME_CACHE.getIfPresent(this.clazz);
    if (name != null)
      return name; 
    String className = this.clazz.getName();
    CLASS_NAME_CACHE.put(this.clazz, className);
    return className;
  }
  
  @NonNull
  public String getSimpleName() {
    String simpleName = (String)CLASS_SIMPLE_NAME_CACHE.getIfPresent(this.clazz);
    if (simpleName != null)
      return simpleName; 
    String classSimpleName = this.clazz.getSimpleName();
    CLASS_SIMPLE_NAME_CACHE.put(this.clazz, classSimpleName);
    return classSimpleName;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\java\clazz\ClassHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */