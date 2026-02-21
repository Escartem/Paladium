package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.ModContainer;
import java.util.List;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetModOwnedPackages {
  public static List<String> a(ModContainer container) {
    if (container == null)
      return null; 
    return container.getOwnedPackages();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetModOwnedPackages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */