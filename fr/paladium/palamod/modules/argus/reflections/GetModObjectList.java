package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.collect.BiMap;
import cpw.mods.fml.common.ModContainer;
import fr.paladium.palamod.modules.argus.strings.StringModObjectList;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetModObjectList {
  public static MyHashSet<String> a(MyHashSet<String> _q) {
    try {
      Field f2 = GetLoadControllerClass.a().getDeclaredField(StringModObjectList._a);
      BlockSafeChest.f(f2);
      BiMap<ModContainer, Object> mp = (BiMap<ModContainer, Object>)f2.get(GetLoadController.a());
      mp.keySet().forEach(pz -> _q.c(GetModClassName.a(pz)));
    } catch (Exception error) {
      return _q;
    } 
    return _q;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetModObjectList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */