package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import java.util.HashMap;
import java.util.Map;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ByteClassLoader extends ClassLoader {
  private final Map<String, byte[]> classes;
  
  private final Map<String, Class<?>> loaded;
  
  public ByteClassLoader(ClassLoader parent, Map<String, byte[]> classes) {
    super(parent);
    this.classes = (Map)new HashMap<>((Map)classes);
    this.loaded = new HashMap<>();
  }
  
  protected Class<?> findClass(String name) {
    byte[] bytes = this.classes.get(name);
    if (bytes != null) {
      this.classes.remove(name);
      Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
      this.loaded.put(name, clazz);
      return clazz;
    } 
    return this.loaded.get(name);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\ByteClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */