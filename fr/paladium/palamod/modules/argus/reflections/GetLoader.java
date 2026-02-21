package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.Loader;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetLoader {
  public static Loader a() {
    return Loader.instance();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */