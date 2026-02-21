package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.ModContainer;
import fr.paladium.palamod.modules.argus.strings.StringNamedMods;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;
import java.util.Map;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetNamedMods {
  public static MyHashSet<String> a(MyHashSet<String> _q) {
    try {
      Field f7 = GetLoadClass.a().getDeclaredField(StringNamedMods._a);
      BlockSafeChest.f(f7);
      Map<String, ModContainer> mpw = (Map<String, ModContainer>)f7.get(GetLoader.a());
      mpw.values().forEach(po -> _q.c(GetModClassName.a(po)));
    } catch (Exception error) {
      return _q;
    } 
    return _q;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetNamedMods.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */