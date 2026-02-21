package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.ModContainer;
import fr.paladium.palamod.modules.argus.strings.StringMods;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;
import java.util.List;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetModList {
  public static MyHashSet<String> a(MyHashSet<String> _q) {
    try {
      Field f6 = GetLoadClass.a().getDeclaredField(StringMods._a);
      BlockSafeChest.f(f6);
      List<ModContainer> mpl = (List<ModContainer>)f6.get(GetLoader.a());
      mpl.forEach(po -> _q.c(GetModClassName.a(po)));
    } catch (Exception error) {
      return _q;
    } 
    return _q;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetModList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */