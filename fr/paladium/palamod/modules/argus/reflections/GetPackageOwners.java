package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.collect.ListMultimap;
import cpw.mods.fml.common.ModContainer;
import fr.paladium.palamod.modules.argus.strings.StringPackageOwners;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetPackageOwners {
  public static MyHashSet<String> a(MyHashSet<String> _q) {
    try {
      Field f3 = GetLoadControllerClass.a().getDeclaredField(StringPackageOwners._a);
      BlockSafeChest.f(f3);
      ListMultimap<String, ModContainer> mk = (ListMultimap<String, ModContainer>)f3.get(GetLoadController.a());
      mk.values().forEach(po -> _q.c(GetModClassName.a(po)));
    } catch (Exception error) {
      return _q;
    } 
    return _q;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetPackageOwners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */