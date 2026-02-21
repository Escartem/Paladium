package fr.paladium.palamod.modules.argus.methods;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import gnu.trove.map.TIntObjectMap;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class MethodPutTIntObjectMap2 {
  public static void a(Object o, int a, Object p) {
    ((TIntObjectMap)o).put(a, p);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\methods\MethodPutTIntObjectMap2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */