package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.LoadController;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetLoadControllerClass {
  public static Class a() {
    return LoadController.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetLoadControllerClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */