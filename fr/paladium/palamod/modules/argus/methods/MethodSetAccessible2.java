package fr.paladium.palamod.modules.argus.methods;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import java.lang.reflect.Method;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class MethodSetAccessible2 {
  public static void a(Object o) throws Exception {
    ((Method)o).setAccessible(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\methods\MethodSetAccessible2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */