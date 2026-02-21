package fr.paladium.palamod.modules.argus.fields;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import java.lang.reflect.Field;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class FieldSetAccessible2 {
  public static void a(Object o) throws Exception {
    ((Field)o).setAccessible(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\fields\FieldSetAccessible2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */