package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.discovery.ModDiscoverer;
import fr.paladium.palamod.modules.argus.strings.StringCandidates;
import fr.paladium.palamod.modules.argus.strings.StringDiscoverer;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;
import java.util.List;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetDiscoverer {
  public static MyHashSet<String> a(MyHashSet<String> _q) {
    try {
      Field discovererField = GetLoadClass.a().getDeclaredField(StringDiscoverer._a);
      BlockSafeChest.f(discovererField);
      ModDiscoverer discoverer = (ModDiscoverer)discovererField.get(GetLoader.a());
      Field candidateField = discoverer.getClass().getDeclaredField(StringCandidates._a);
      BlockSafeChest.f(candidateField);
      List list = (List)candidateField.get(discoverer);
    } catch (Exception error) {
      return _q;
    } 
    return _q;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetDiscoverer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */