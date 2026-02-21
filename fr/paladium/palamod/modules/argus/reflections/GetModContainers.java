package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.collect.BiMap;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.common.ModContainer;
import fr.paladium.palamod.modules.argus.strings.StringGuiFactories;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetModContainers {
  public static MyHashSet<String> a(MyHashSet<String> _q) {
    try {
      FMLClientHandler o = FMLClientHandler.instance();
      Field f8 = o.getClass().getDeclaredField(StringGuiFactories._a);
      BlockSafeChest.f(f8);
      BiMap<ModContainer, IModGuiFactory> il = (BiMap<ModContainer, IModGuiFactory>)f8.get(o);
      il.keySet().forEach(l -> {
            if (l != null) {
              if (GetModClassName.a(l) != null)
                _q.c(GetModClassName.a(l)); 
              if (GetModOwnedPackages.a(l) != null)
                _q.p(GetModOwnedPackages.a(l)); 
            } 
          });
    } catch (Exception error) {
      return _q;
    } 
    return _q;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetModContainers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */