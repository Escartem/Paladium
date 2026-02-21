package fr.paladium.palamod.modules.argus.fields;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import java.util.Map;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class MapPut2 {
  public static void a(Object o, Object k, Object v) throws Exception {
    ((Map<Object, Object>)o).put(k, v);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\fields\MapPut2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */