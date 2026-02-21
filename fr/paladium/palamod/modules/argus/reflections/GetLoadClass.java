package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.Loader;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetLoadClass {
  public static Class a() {
    return Loader.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetLoadClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */