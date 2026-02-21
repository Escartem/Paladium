package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.util.MyHashSet;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ListResourcesGet {
  public static MyHashSet<String> p() {
    return ListResources.msh;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ListResourcesGet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */