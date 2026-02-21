package fr.paladium.palamod.modules.argus.fields;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.collect.BiMap;
import java.util.Set;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetBimapValues {
  public static Set a(Object o) throws Exception {
    return ((BiMap)o).values();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\fields\GetBimapValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */