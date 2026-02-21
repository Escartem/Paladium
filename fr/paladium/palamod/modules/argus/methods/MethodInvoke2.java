package fr.paladium.palamod.modules.argus.methods;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import java.lang.reflect.Method;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class MethodInvoke2 {
  public static void a(Object a, Object b, Object... l) throws Exception {
    ((Method)a).invoke(b, l);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\methods\MethodInvoke2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */