package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.discovery.ModCandidate;
import java.util.Set;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetModClassList {
  public static Set<String> a(ModCandidate candidate) {
    if (candidate == null)
      return null; 
    return candidate.getClassList();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetModClassList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */