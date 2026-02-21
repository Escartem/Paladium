package fr.paladium.palamod.modules.argus;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.modules.argus.classes.ClassFMLClientHandler;
import fr.paladium.palamod.modules.argus.instances.InstanceFMLClientHandler;
import fr.paladium.palamod.modules.argus.reflections.GetLoader;
import fr.paladium.palamod.modules.argus.reflections.GetResourcePackName;
import fr.paladium.palamod.modules.argus.reflections.ListResources;
import fr.paladium.palamod.modules.argus.strings.StringResourcePackList;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.IResourcePack;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ResourceManager {
  public static String getResourceData() {
    Set<String> rs = new HashSet<>();
    try {
      Field f = ClassFMLClientHandler.p().getDeclaredField(StringResourcePackList._a);
      BlockSafeChest.f(f);
      List mn = (List)f.get(InstanceFMLClientHandler.i());
      for (Object o : mn) {
        if (!(o instanceof IResourcePack))
          continue; 
        IResourcePack pk = (IResourcePack)o;
        if (!ListResources.cop(GetResourcePackName._a(pk)))
          ListResources.msh.c(GetResourcePackName._a(pk)); 
      } 
    } catch (Exception error) {
      error.printStackTrace();
    } 
    return String.join(", ", (Iterable)rs);
  }
  
  public static ClassLoader getNati() {
    return GetLoader.a().getModClassLoader();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\ResourceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */