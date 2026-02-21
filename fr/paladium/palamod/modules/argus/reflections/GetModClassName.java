package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.ModContainer;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetModClassName {
  public static String a(ModContainer container) {
    if (container == null)
      return null; 
    if (container.getMod() == null)
      return null; 
    if (container.getMod().getClass() == null)
      return null; 
    return container.getMod().getClass().getName();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetModClassName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */