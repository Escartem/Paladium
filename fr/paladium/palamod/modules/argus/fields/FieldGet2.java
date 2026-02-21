package fr.paladium.palamod.modules.argus.fields;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import java.lang.reflect.Field;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class FieldGet2 {
  public static Object a(Object o, Object p) throws Exception {
    return ((Field)o).get(p);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\fields\FieldGet2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */